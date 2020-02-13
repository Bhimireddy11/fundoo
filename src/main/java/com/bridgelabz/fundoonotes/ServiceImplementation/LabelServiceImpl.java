package com.bridgelabz.fundoonotes.ServiceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Repository.LabelRepository;
import com.bridgelabz.fundoonotes.Repository.NoteRepository;
import com.bridgelabz.fundoonotes.Repository.UserRepository;
import com.bridgelabz.fundoonotes.Sevice.LabelService;
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


}
