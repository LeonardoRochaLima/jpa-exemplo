package com.agenda.springcloudmysql.repository;

import com.agenda.springcloudmysql.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Groups_Repository extends JpaRepository<Groups, Long> {
}
