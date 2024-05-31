package com.web.p5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface memberRep extends JpaRepository<member, String> {
	
	@Query("select count(id) from member")
	int memberCount();
}
