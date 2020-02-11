package com.bridgelabz.fundoonotes.Sevice;

import java.util.List;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.Model.Collaborator;

public interface CollaboratorService {

	Collaborator addCollaborator(CollaboratorDto colabDto,long noteId);

	List<Collaborator> getCollaboratorList(long noteId);


}
