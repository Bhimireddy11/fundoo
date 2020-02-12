package com.bridgelabz.fundoonotes.DTO;

import lombok.Data;
import java.util.List;

@Data
public class UpdateCollaboratorDto {
	private long noteId;
	private List<String>newEmail;
	private List<String> removeEmail;

}
