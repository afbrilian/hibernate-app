package com.hibernate.dao;

import java.util.List;

import com.hibernate.model.Contact;

public interface ContactDao {
	List<Contact> findAll();
	List<Contact> findAllWithDetail();
	Contact findById(Long id);
	Contact save(Contact contact);
	void delete(Contact contact);
}