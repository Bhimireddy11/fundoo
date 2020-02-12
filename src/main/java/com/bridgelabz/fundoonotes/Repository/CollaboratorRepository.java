package com.bridgelabz.fundoonotes.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.bridgelabz.fundoonotes.Model.Collaborator;
import com.bridgelabz.fundoonotes.Model.Note;

public interface CollaboratorRepository  extends CrudRepository<Collaborator, Long>{
	@Query(value="Select * from  collabotator where note_id=?",nativeQuery=true)
	List<Collaborator>getAllColab(long noteId);
	Collaborator findByEmail(String email);
	Collaborator findByEmailAndNoteColab(String email,Note note);
	

}
