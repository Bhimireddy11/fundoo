package com.bridgelabz.fundoonotes.Model;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data

public class Note {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long noteId;

		private String title;

		private String description;

		@Column(columnDefinition = "boolean default false")
		private boolean isArchieved;

		@Column(columnDefinition = "boolean default false")
		private boolean isPinned;

		@Column(columnDefinition = "boolean default false")
		private boolean isTrashed;

		@JsonIgnore
		private LocalDateTime createdAt;
		@JsonIgnore
		private LocalDateTime updatedAt;
		private String colour;
		private LocalDateTime reminder;
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "userId")
		private UserDemo userNotes;
		@JsonIgnore
		@ManyToMany
		@JoinTable(name = "Label_Note", joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "noteId"), inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "labelId"))
		private List<Label> labels;
		public long getNoteId() {
			return noteId;
		}
		public void setNoteId(long noteId) {
			this.noteId = noteId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public boolean isArchieved() {
			return isArchieved;
		}
		public void setArchieved(boolean isArchieved) {
			this.isArchieved = isArchieved;
		}
		public boolean isPinned() {
			return isPinned;
		}
		public void setPinned(boolean isPinned) {
			this.isPinned = isPinned;
		}
		public boolean isTrashed() {
			return isTrashed;
		}
		public void setTrashed(boolean isTrashed) {
			this.isTrashed= isTrashed;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getColour() {
			return colour;
		}
		public void setColour(String colour) {
			this.colour = colour;
		}
		public LocalDateTime getReminder() {
			return reminder;
		}
		public void setReminder(LocalDateTime reminder) {
			this.reminder = reminder;
		}
		public UserDemo getUserNotes() {
			return userNotes;
		}
		public void setUserNotes(UserDemo userNotes) {
			this.userNotes = userNotes;
		}
		public List<Label> getLabels() {
			return labels;
		}
		public void setLabels(List<Label> labels) {
			this.labels = labels;
		}
		
		@Override
		public String toString() {
			return "Note [noteId=" + noteId + ", title=" + title + ", desrition=" + description + ",  isArchiev=" +  isArchieved+ ", isPin="
					+ isPinned + ", isTrash=" + isTrashed + ", createdAt=" + createdAt + ", colour=" +colour
					+ ", reminder=" + reminder + ",userNotes=" +userNotes +",Labels="+labels+"]";
		}
}
