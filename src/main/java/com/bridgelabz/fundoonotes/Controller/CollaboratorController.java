package com.bridgelabz.fundoonotes.Controller;

import java. util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.DTO.UpdateCollaboreatorDto;
import com.bridgelabz.fundoonotes.Model.Collaborator;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.Sevice.CollaboratorService;
@RestController

@RequestMapping("/collaborator")
public class CollaboratorController {
	
	@Autowired
	CollaboratorService collaboratorService;
	
	/* 
	 * Api to add Collaborator 
   */
	@PostMapping("/addcollaborator/{noteId}")
	public ResponseEntity<Response> addCollaborator(@RequestBody CollaboratorDto colabDto,
			@PathVariable("noteId") long noteId) {
		Collaborator colabInfo = collaboratorService.addCollaborator(colabDto, noteId);
		return colabInfo != null
				? ResponseEntity.status(HttpStatus.OK)
						.body(new Response("Collaborator Added to the Note"))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("BAD REQUEST"));

	}

	/*
	 *  Api to get all the collaborator of a note
   */
	@GetMapping("/collaboratorlist")
	public ResponseEntity<Response> getCollaborator(@RequestParam("noteId") long noteId) {
		List<Collaborator> colabList = collaboratorService.getCollaboratorList(noteId);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Collaborator List"));
	}
	
	@PutMapping
	public ResponseEntity<Response> updateCollaborator(@RequestBody UpdateCollaboreatorDto updateColabDto){
		collaboratorService.updateCollaborator(updateColabDto);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated"));
	}

}
	
	

