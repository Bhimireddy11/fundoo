package com.bridgelabz.fundoonotes.Controller;

import java.util.List;


import org.elasticsearch.cli.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.ReminderDto;
import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.Sevice.NoteService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	NoteService noteService;

	/*
	 * API to create notes
	 */

//
	@PostMapping("/create")
	@ApiOperation(value = "Api to create new note", response = Response.class)
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto,
			@RequestHeader( value = "token") String token, BindingResult bindingResult)
			throws Exception {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response(bindingResult.getAllErrors()));
		}
		Note noteInfo = noteService.createNote(noteDto, token);
		return noteInfo != null
				? ResponseEntity.status(HttpStatus.OK).body(new Response("Note is created successfully",200))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong"));
	}
	
	
	
	
//	@PostMapping("/notes/create")
//	@ApiOperation(value = "Api to update a note for user in Fundoonotes", response = Response.class)
//	public ResponseEntity<Response> addNote(@RequestBody NoteDto noteDto,
//			@RequestHeader("token") String token) throws UserNotFoundException, NoteCreationException, IOException {
//		System.out.println("notes");
//		Note createNote = noteService.createNote(noteDto, token);
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created"));
//
//	}
	/*
	 * API map the creation of existing note
	 * 
	 * 
	 * @GetMapping("notes/create/{noteId}")
	 * 
	 * @ApiOperation(value = "Api to check existing note", response =
	 * Response.class) public ResponseEntity<Response>
	 * exisnote(@PathVariable("noteId") long noteId,
	 * 
	 * @RequestHeader("token") String token) throws Exception {
	 * 
	 * boolean result = noteService.isExist(noteId, token); return (result) ?
	 * ResponseEntity.status(HttpStatus.OK) .body(new
	 * Response("note created  sucessfully", 200)) :
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
	 * Response("note id is already exist", 400)); }
	 * 
	 */

	/*
	 * Api to add notes into trash
	 */
	@DeleteMapping("notes/delete/{noteId}")
	@ApiOperation(value = "Api to add notes into trash", response = Response.class)
	public ResponseEntity<Response> deleteOneNote(@PathVariable("noteId") long noteId,
			@RequestHeader("token") String token) throws Exception {

		boolean result = noteService.deleteOneNote(noteId, token);
		return (result)
				? ResponseEntity.status(HttpStatus.OK).body(new Response("Note is successfully added to the trashed"))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Note ID is not Available"));
	}

	/*
	 * API to get all the notes of one user
	 */
	// @JsonIgnore
//	@GetMapping("/notes/notes/{token}")
//	@ApiOperation(value = "Api to get notes list", response = Response.class)
//	public ResponseEntity<Response> notes(@PathVariable("token") String token)throws UserException {
//		// log.info("GET TOKEN :" + token);
//	List<Note> notes = noteService.getAllNotes(token);
//		System.out.println("--------------------------------"+notes);
//		return ResponseEntity.status(HttpStatus.OK).body(new Response("Notes are",200,notes));
//	}
	@GetMapping(value = "/notes")
	public List<Note> getnotes() {
		return noteService.getallnotes();
	}

	
	
	@GetMapping(value = "/note/{token}")
	public ResponseEntity<Response> getnotesById(@PathVariable("token") String token) {
	Note notes = noteService.getnoteById(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("notes are",200,notes));
	}
	
	/*
	 * API to make note Archive
	 */
	@PutMapping("notes/archive/{noteId}")
	@ApiOperation(value = "Api to make note archive", response = Response.class)
	public ResponseEntity<Response> makeArchive(@PathVariable("noteId") long noteId,
			@RequestHeader("token") String token) {
		boolean result = noteService.isArchived(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note is Archived Successfully"))
				: ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new Response("Note doesn't Archived"));
	}

	/*
	 * API to add color
	 */
	@PutMapping("notes/color/{noteId}")
	@ApiOperation(value = "Api to add color", response = Response.class)
	public ResponseEntity<Response> addColor(@RequestParam("color") String color, @RequestHeader("token") String token,
			@PathVariable("noteId") long noteId) {
		boolean result = noteService.addColor(color, token, noteId);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Color is added successfully"))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Something went wrong"));
	}

	/*
	 * API to make note pinned
	 */
	@PutMapping("notes/pin/{noteId}")
	@ApiOperation(value = "Api to make note pinned", response = Response.class)
	public ResponseEntity<Response> pinned(@RequestParam("noteId") long noteId, @RequestHeader("token") String token) {
		boolean result = noteService.pinnedNotes(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note is pinned"))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Something went wrong"));
	}

	/*
	 * API to set reminder
	 */
	@PutMapping("notes/reminder")
	@ApiOperation(value = "Api to set remainder", response = Response.class)
	public ResponseEntity<Response> reminder(@RequestParam("noteId") long noteId, @RequestHeader("token") String token,
			@RequestBody ReminderDto reminderDto) throws Exception {
		boolean result = noteService.setReminder(noteId, token, reminderDto);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Reminder is Added Successfully"))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Something went wrong"));
	}

	/*
	 * API to update note
	 */
	@PutMapping("notes/update")
	@ApiOperation(value = "Api to update note", response = Response.class)
	public ResponseEntity<Response> updateNote(@RequestParam("noteId") long noteId,
			@RequestHeader("token") String token, @RequestBody NoteDto noteDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Note is updated successfully"));
	}

	/*
	 * API to delete notes permanently
	 */
	@DeleteMapping("notes/permanentDelete/{noteId}")
	@ApiOperation(value = "Api to delete note permanently", response = Response.class)
	public ResponseEntity<Response> permanentDelete(@PathVariable("noteId") long noteId,
			@RequestHeader("token") String token) throws Exception {
		boolean result = noteService.permanentDelete(noteId, token);
		return (result) ? ResponseEntity.status(HttpStatus.OK).body(new Response("Note is deleted permanently"))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new Response("The note you are trying to delete is not available"));
	}

	/*
	 * API to search note
	 */
	@GetMapping("notes/title")
	@ApiOperation(value = "Api to search note based on title", response = Response.class)
	public ResponseEntity<Response> searchByTitle(@RequestParam("title") String title,
			@RequestHeader("token") String token) {
		List<Note> noteList = noteService.searchByTitle(title);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("List of Notes are",200,noteList));

	}

	/*
	 * API to get all labels of one Note
	 */
	@GetMapping("notes/labelsofnote")
	@ApiOperation(value = "Api to get all labels of one note", response = Response.class)
	public ResponseEntity<Response> getAllLabels(@RequestParam("noteId") long noteId,
			@RequestHeader("token") String token) {
		List<Label> noteList = noteService.getAllLabelsOfOneNote(token, noteId);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Labels releated to current Note are",200,noteList));
	}
}
