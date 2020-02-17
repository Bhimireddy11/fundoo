package com.bridgelabz.fundoonotes.Sevice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.DTO.UpdateCollaboreatorDto;
import com.bridgelabz.fundoonotes.Model.Collaborator;

public interface CollaboratorService {

	Collaborator addCollaborator(CollaboratorDto colabDto,long noteId);

	List<Collaborator> getCollaboratorList(long noteId);

	void updateCollaborator(UpdateCollaboreatorDto updateColabDto);

}



