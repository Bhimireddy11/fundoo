package com.bridgelabz.fundoonotes.ServiceImplementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.DTO.LabelDto;
import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.Repository.LabelRepository;
import com.bridgelabz.fundoonotes.Repository.NoteRepository;
import com.bridgelabz.fundoonotes.Repository.UserRepository;
import com.bridgelabz.fundoonotes.Sevice.LabelService;
import com.bridgelabz.fundoonotes.customexceptions.LabelAlreadyExistException;
import com.bridgelabz.fundoonotes.utility.JwtGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LabelServiceImpl  implements LabelService{

	@Autowired
	JwtGenerator jwtGenerator;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	LabelRepository labelRepository;

	@Autowired
	NoteRepository noterepository;

	@Autowired
	Label label;

	@Override
	public boolean createlabel(LabelDto labelDto, String token) throws LabelAlreadyExistException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createOrMapWithNote(LabelDto labelDto, long noteId, String token) throws LabelAlreadyExistException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLabels(String token, long noteId, long labelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletepermanently(String token, long labelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLabel(String token, long labelId, LabelDto labelDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Label> getAllLabels(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> getAllNotes(String token, long labelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addLabels(String token, long noteId, long labelId) {
		// TODO Auto-generated method stub
		return false;
	}


}
