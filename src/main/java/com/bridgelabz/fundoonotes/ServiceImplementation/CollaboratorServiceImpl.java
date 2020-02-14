package com.bridgelabz.fundoonotes.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.DTO.UpdateCollaboreatorDto;
import com.bridgelabz.fundoonotes.Model.Collaborator;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.Repository.CollaboratorRepository;
import com.bridgelabz.fundoonotes.Repository.NoteRepository;
import com.bridgelabz.fundoonotes.Repository.UserRepository;
import com.bridgelabz.fundoonotes.Sevice.CollaboratorService;
import com.bridgelabz.fundoonotes.customexceptions.NoteIdNotFoundException;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	CollaboratorRepository collaboratorRepository;
	@Autowired
	NoteRepository noteRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public Collaborator addCollaborator(CollaboratorDto colabDto, long noteId) {
		Optional<Note> noteInfo = noteRepository.findById(noteId);
		Collaborator colabInfo = null;

		if (noteInfo.isPresent()) {
			colabInfo = modelMapper.map(colabDto, Collaborator.class);
			colabInfo.setNoteColab(noteInfo.get());

			return collaboratorRepository.save(colabInfo);
		}
		throw new NoteIdNotFoundException("Note Id not Found with " + noteId);
	}

	@Override
	public List<Collaborator> getCollaboratorList(long noteId) {
		Optional<Note> noteInfo = noteRepository.findById(noteId);
		if (noteInfo.isPresent()) {
			return collaboratorRepository.getAllColab(noteId);
		}
		throw new NoteIdNotFoundException("Note Id not Found with " + noteId);
	}

	@Override
	public void updateCollaborator(UpdateCollaboreatorDto updateColabDto) {
		Optional<Note> note = noteRepository.findById(updateColabDto.getNoteId());
		if (note.isPresent()) {
			List<Collaborator> collaborators=updateColabDto.getNewEmail().stream().map(email -> {
				Collaborator collaborator= new Collaborator();
				collaborator.setEmail(email);
				collaborator.setNoteColab(note.get());
				return collaborator;
			}).collect(Collectors.toList());

			collaboratorRepository.saveAll(collaborators);
			List<Collaborator> collaborators2 = updateColabDto.getRemoveEmail().stream().map(email -> collaboratorRepository.findByEmailAndNoteColab(email, note.get())).collect(Collectors.toList());
			collaboratorRepository.deleteAll(collaborators2);
		} else {
			throw new NoteIdNotFoundException("Note Id not Found with " + updateColabDto.getNoteId());
		}

	}

}
	


