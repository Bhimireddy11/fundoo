package com.bridgelabz.fundoonotes.Repository;

import java.util.List;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.bridgelabz.fundoonotes.Model.Label;
import com.bridgelabz.fundoonotes.Model.Note;



@Transactional
public interface NoteRepository extends JpaRepository<Note, Long> {

	@Query(value = "SELECT * FROM note where user_id=?", nativeQuery = true)
	List<Note> getAllNotes(long id);   //TOKEN TEESUKUNTUNDENTII   ...?EMO TAMMUDU WAI

	@Modifying
	@Query(value = "DELETE FROM note where note_id=? AND is_trash=true", nativeQuery = true)
	void deleteNotesPermanently(long noteId);

	@Query(name="tofindlabelfornote", nativeQuery = true)
	List<Label> getLabelsByNoteId(long noteId);

	
	List<Note> findAll();
	
	
} 




