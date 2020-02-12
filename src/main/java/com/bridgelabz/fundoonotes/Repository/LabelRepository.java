package com.bridgelabz.fundoonotes.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoonotes.DTO.LabelDto;
import com.bridgelabz.fundoonotes.Model.Label;

public interface LabelRepository extends CrudRepository<Label, Long>{
	LabelDto findOneByName(String labelName);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM label_note where label_id=?", nativeQuery = true)
	void deleteMapping(long labelId);

}

