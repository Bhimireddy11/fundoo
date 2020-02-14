package com.bridgelabz.fundoonotes.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.Model.Collaborator;
import com.bridgelabz.fundoonotes.Model.Note;
@Repository
public interface CollaboratorRepository extends CrudRepository<Collaborator, Long> {
	
	@Query(value = "select * from collaborator where note_id=?",nativeQuery = true)
	List<Collaborator> getAllColab(long noteId);
	Collaborator findByEmail(String email);
	Collaborator findByEmailAndNoteColab(String email, Note note);

}
	


