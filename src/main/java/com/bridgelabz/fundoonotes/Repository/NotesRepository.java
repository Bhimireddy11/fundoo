//package com.bridgelabz.fundoonotes.Repository;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.bridgelabz.fundoonotes.Model.Note;
//
//import lombok.extern.slf4j.Slf4j;
//
//
//@Repository
//@Slf4j
//public class NotesRepository {
//	@Autowired
//	private EntityManager entityManager;
//
//	@Transactional
//	public Note  createNote(Note note) {
//		Session session = entityManager.unwrap(Session.class);
//		session.saveOrUpdate(note);
//		return note;
//	}
//
//
//	@Transactional
//	public List<Note> getAllNotes(long userId) {
//		Session session = entityManager.unwrap(Session.class);
//		List list = session.createQuery("from Note where user_id='" + userId + "'").getResultList();
//		return list;
//	}
//
//
//
//	public List<Note> getPinned(Long userId) {
//		Session session=entityManager.unwrap(Session.class);
//		List list =  session.createQuery("from Note where user_id='" + userId + "'"+" and  is_pinned=true").getResultList();
//		return list;
//	}
//
//	
//	
//
//
//	
//	
///*	@Transactional
//	public List<NotesEntity> getAllNotes(long userId) {
//		Session session = entityManager.unwrap(Session.class);
//    		/*Query<?> q = session.createQuery(" from NotesEntity where notesId='"+userId+"'"+"and  is_trashed=false and  is_archieved=false ORDER BY notes_id DESC");
//		return (List<NotesEntity>) q.getResultList();*/
//	//	return session.createQuery(" from NotesEntity where notesId='"+userId+"'"+"and  is_trashed=false and  is_archieved=false ORDER BY notes_id DESC").getResultList();
//	//}*/
//
//
//
//
//
//}