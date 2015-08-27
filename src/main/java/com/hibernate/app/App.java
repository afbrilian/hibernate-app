package com.hibernate.app;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.hibernate.dao.ContactDao;
import com.hibernate.model.Contact;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();
        
        ContactDao contactDao = ctx.getBean("ContactDao", ContactDao.class);
        
        listContacts(contactDao.findAll());
    }
    
    private static void listContacts(List<Contact> contacts) {
    	System.out.println();
    	System.out.println("Listing contacts without details:");
    	for (Contact contact: contacts) {
    		System.out.println(contact);
    		System.out.println();
    	}
    }
}
