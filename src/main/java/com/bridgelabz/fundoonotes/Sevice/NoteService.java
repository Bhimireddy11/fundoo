package com.bridgelabz.fundoonotes.Sevice;


import java.util.List;


import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.ReminderDto;
import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.customexceptions.NoteIdNotFoundException;

public interface NoteService {

	Note createNote(NoteDto noteDto, String token);

	boolean deleteOneNote(long id, String token) throws NoteIdNotFoundException;

	boolean isArchived(long id, String token);

	List<Note> getAllNotes(String token);

	boolean addColor(String color, String token, long id);

	boolean pinnedNotes(long id, String token);

	boolean setReminder(long noteId, String token, ReminderDto reminderDto);

	boolean permanentDelete(long noteId, String token);

	List<Note> searchByTitle(String title);

	List<Label> getAllLabelsOfOneNote(String token, long noteId);

	Note updateNoteDetails(long noteId, String token, NoteDto noteDto) throws NoteIdNotFoundException;

	


	//boolean isExist(long noteId, String token);

}


