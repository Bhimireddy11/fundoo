package com.bridgelabz.fundoonotes.ServiceImplementation;


import java.time.LocalDateTime;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.ReminderDto;
import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.Model.UserDemo;
import com.bridgelabz.fundoonotes.Repository.NoteRepository;
import com.bridgelabz.fundoonotes.Repository.UserRepository;
import com.bridgelabz.fundoonotes.Sevice.ElasticSearchService;
import com.bridgelabz.fundoonotes.Sevice.NoteService;
import com.bridgelabz.fundoonotes.customexceptions.NoteIdNotFoundException;
import com.bridgelabz.fundoonotes.utility.JwtGenerator;


import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

	@Autowired
	JwtGenerator jwtGenerator;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Note note;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	ElasticSearchService elasticSearchService;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	

	@Override
	public Note createNote(NoteDto noteDto, String token) {

//		long userId = getRedisCacheId(token);
		 long userId = jwtGenerator.parseJWT(token);
	//	log.info("Id is :" + userId + " ,Description :" + noteDto.getDescription());

		Optional<UserDemo> user = userRepository.findById(userId);
		if (user.isPresent()) {
			note = modelMapper.map(noteDto, Note.class);
			note.setUserNotes(user.get());
			note.setCreatedAt(LocalDateTime.now());
			note.setArchieved(false);
			note.setPinned(false);
			note.setTrashed(false);
			note.setColour("blue");

			Note noteInfo = noteRepository.save(note);
		}
		return note;
	
}

@Override
public boolean deleteOneNote(long id, String token) throws NoteIdNotFoundException {
	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
	if (isUserAvailable.isPresent()) {
		Optional<Note> isNoteIdAvailable = noteRepository.findById(id);
		if (isNoteIdAvailable.isPresent()) {
			isNoteIdAvailable.get().setTrashed(!isNoteIdAvailable.get().isTrashed());
			noteRepository.save(isNoteIdAvailable.get());
			return true;
		} else {
			throw new NoteIdNotFoundException("The note you are trying to delete is not available");
		}
	}
	return false;
}


@Override
public boolean isArchived(long noteId, String token) {
	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> user = userRepository.findById(userId);
	if (user.isPresent()) {
		Optional<Note> isNoteAvailable = noteRepository.findById(noteId);
		if (isNoteAvailable.isPresent()) {
			isNoteAvailable.get().setPinned(false);
			isNoteAvailable.get().setArchieved(!isNoteAvailable.get().isArchieved());
			noteRepository.save(isNoteAvailable.get());
			return true;
		}
	}
	return false;
}
/*@Override
public boolean isExist(long noteId, String token) {
	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> user = userRepository.findById(userId);
	if (user.isPresent()) {
		Optional<Note> isNoteAvailable = noteRepository.findById(noteId);
		if (isNoteAvailable.isPresent()) {
			isNoteAvailable.get().setPin(false);
			isNoteAvailable.get().setArchiev(!isNoteAvailable.get().isArchiev());
			noteRepository.save(isNoteAvailable.get());
			return true;
		}
	}
	return false;
}
*/
//@Override
//public List<Note> getAllNotes(String token) {
//	long userId = jwtGenerator.parseJWT(token);
////	long userId = getRedisCacheId(token);
//	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
////	List<Note> getAllNotes = null;
//	if (isUserAvailable.isPresent()) {
//		List<Note> getAllNotes	= noteRepository.getAllNotes(userId);
//	}
//	return getAllNotes;
//}
@Override
@Transactional
public List<Note> getallnotes(){
	List<Note> notes = new ArrayList<>();
	noteRepository.findAll().forEach(notes::add);
	return notes;
}
//api to get notes by id


@Override
@Transactional
public Note getnoteById(String token) {
	Long id = (long) jwtGenerator.parseJWT(token);
	Note notes = noteRepository.findById(id)
			.orElseThrow(() -> new  NoteIdNotFoundException("notes not found"));
	return notes;

}

@Override
public boolean addColor(String color, String token, long id) {

	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
	if (isUserAvailable.isPresent()) {
		Optional<Note> isNoteAvailable = noteRepository.findById(id);
		if (isNoteAvailable.isPresent()) {
			isNoteAvailable.get().setColour(color);
			noteRepository.save(isNoteAvailable.get());
			return true;
		}
	}
	return false;
}

@Override
public boolean pinnedNotes(long id, String token) {
	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
	if (isUserAvailable.isPresent()) {
		Optional<Note> isNoteAvailable = noteRepository.findById(id);
		if (isNoteAvailable.isPresent()) {
			isNoteAvailable.get().setArchieved(false);
			isNoteAvailable.get().setPinned(!isNoteAvailable.get().isPinned());
			noteRepository.save(isNoteAvailable.get());
			return true;
		}
	}
	return false;
}

@Override
public boolean setReminder(long noteId, String token, ReminderDto reminderDto) {
	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
	if (isUserAvailable.isPresent()) {
		Optional<Note> isNoteAvailable = noteRepository.findById(noteId);
		if (isNoteAvailable.isPresent()) {
			String str =reminderDto.getDatetime();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			isNoteAvailable.get().setReminder(dateTime);
			isNoteAvailable.get().setUpdatedAt(LocalDateTime.now());
			noteRepository.save(isNoteAvailable.get());
			return true;
		}
	}
	return false;
}

@Override
public boolean permanentDelete(long noteId, String token) {
	// long userId = jwtGenerator.parseJWT(token);
	long userId = getRedisCacheId(token);
	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
	if (isUserAvailable.isPresent()) {
		Optional<Note> isNoteAvailable = noteRepository.findById(noteId);
		if (isNoteAvailable.isPresent()) {
			noteRepository.deleteNotesPermanently(noteId);
			return true;
		}
	}
	return false;
}

@Override
public List<Note> searchByTitle(String title) {
	List<Note> notes = elasticSearchService.searchByTitle(title);
	return notes;
}

/*
 * Add data to the redis cache
 */
private long getRedisCacheId(String token) {
	
	//log.info("TOKEN :"+token);
	String[] splitedToken = token.split("\\.");
Object redisTokenKey = null;
	//	String redisTokenKey = splitedToken[1] + splitedToken[2];
	if (redisTemplate.opsForValue().get(redisTokenKey) == null) {
		long idForRedis = jwtGenerator.parseJWT(token);
	//	log.info("idForRedis is :" + idForRedis);
		redisTemplate.opsForValue().set((String) redisTokenKey, idForRedis, 3 * 60, TimeUnit.SECONDS);
	}
	return (Long) redisTemplate.opsForValue().get(redisTokenKey);
}

@Override
public List<Label> getAllLabelsOfOneNote(String token, long noteId){
	long userId = getRedisCacheId(token);
	Optional<UserDemo> isUserAvailable = userRepository.findById(userId);
	List<Label> labels = null;
	if (isUserAvailable.isPresent()) {
		Optional<Note> noteInfo = noteRepository.findById(noteId);
		if (noteInfo.isPresent()) {
			labels = noteRepository.getLabelsByNoteId(noteId);
			return labels;
		}
	}
	return labels;
}

@Override
public Note updateNoteDetails(long noteId, String token, NoteDto noteDto) throws NoteIdNotFoundException {
	Optional<UserDemo> isUserAvailable = userRepository.findById( getRedisCacheId(token));
	if(isUserAvailable.isPresent()) {
		Optional<Note> noteInfo = noteRepository.findById(noteId);
		if (noteInfo.isPresent()) {
			noteInfo.get().setTitle(noteDto.getTitle());
			noteInfo.get().setDescription(noteDto.getDescription());
			noteInfo.get().setUpdatedAt(LocalDateTime.now());
			return noteRepository.save(noteInfo.get());
		}
		else
			throw new NoteIdNotFoundException("The note you are trying to update is not available");
	}
	return null;
}




}