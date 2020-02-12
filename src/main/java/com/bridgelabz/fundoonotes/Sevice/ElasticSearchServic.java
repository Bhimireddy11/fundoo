package com.bridgelabz.fundoonotes.Sevice;

import java.util.List;


import com.bridgelabz.fundoonotes.Model.Note;

public class ElasticSearchServic {

public interface ElasticSearchService {

	String createNote(Note note);

	List<Note> searchByTitle(String title);
}
	

}
