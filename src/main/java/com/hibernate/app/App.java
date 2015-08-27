package com.hibernate.app;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.hibernate.dao.ContactDao;
import com.hibernate.model.Contact;
import com.hibernate.model.ContactTelDetail;
import com.hibernate.model.Hobby;

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
        listContactsWithDetail(contactDao.findAllWithDetail());
    }
    
    private static void listContacts(List<Contact> contacts) {
    	System.out.println();
    	System.out.println("Listing contacts without details:");
    	for (Contact contact : contacts) {
    		System.out.println(contact);
    		System.out.println();
    	}
    }
    
    private static void listContactsWithDetail(List<Contact> contacts) {
    	System.out.println();
    	System.out.println("Listing contacts with details:");
    	for (Contact contact : contacts) {
    		System.out.println(contact);
    		
    		if (contact.getContactTelDetails() != null) {
    			for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
    				System.out.println(contactTelDetail);
    			}
    		}
    		
    		if (contact.getHobbies() != null) {
    			for (Hobby hobby : contact.getHobbies()) {
    				System.out.println(hobby);
    			}
    		}
    		
    		System.out.println();
    	}
    }
}
