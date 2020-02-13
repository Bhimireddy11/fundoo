package com.bridgelabz.fundoonotes.Sevice;

import java.util.List;

import com.bridgelabz.fundoonotes.DTO.LabelDto;
import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.customexceptions.LabelAlreadyExistException;

public interface LabelService {

	boolean createlabel(LabelDto labelDto, String token) throws LabelAlreadyExistException;

	boolean createOrMapWithNote(LabelDto labelDto, long noteId, String token) throws LabelAlreadyExistException;

	boolean removeLabels(String token, long noteId, long labelId);

	boolean deletepermanently(String token, long labelId);

	boolean updateLabel(String token, long labelId, LabelDto labelDto);

	List<Label> getAllLabels(String token);

	List<Note> getAllNotes(String token, long labelId);

	boolean addLabels(String token, long noteId, long labelId);

}


