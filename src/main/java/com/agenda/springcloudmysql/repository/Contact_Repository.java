package com.agenda.springcloudmysql.repository;

import com.agenda.springcloudmysql.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Contact_Repository extends JpaRepository<Contact, Long> {}

