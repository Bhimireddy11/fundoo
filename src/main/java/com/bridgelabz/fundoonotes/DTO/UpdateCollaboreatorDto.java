package com.bridgelabz.fundoonotes.DTO;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UpdateCollaboreatorDto {
	private long noteId;
	private List<String> newEmail;
	private List<String> removeEmail;
	public long getNoteId() {
		return noteId;
	}
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}
	public List<String> getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(List<String> newEmail) {
		this.newEmail = newEmail;
	}
	public List<String> getRemoveEmail() {
		return removeEmail;
	}
	public void setRemoveEmail(List<String> removeEmail) {
		this.removeEmail = removeEmail;
	}
	
	

}
