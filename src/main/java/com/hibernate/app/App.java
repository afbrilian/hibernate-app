package com.hibernate.app;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
        
        System.out.println();
        System.out.println("contact with id 1 = " + contactDao.findById(1l));
        System.out.println();
        
        //saveContact(contactDao);
        //updateContact(contactDao);
        deleteContact(contactDao);
    }
    
    private static void deleteContact(ContactDao contactDao){
    	Contact contact = contactDao.findById(4l);
    	contactDao.delete(contact);
    	
    	listContactsWithDetail(contactDao.findAllWithDetail());
    }
    
    private static void updateContact(ContactDao contactDao){
    	System.out.println();
    	Contact contact= contactDao.findById(1l);
    	contact.setFirstName("Kim Fung");

		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();

		ContactTelDetail toDeleteContactTel = null;

		for (ContactTelDetail contactTel : contactTels) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contact.removeContactTelDetail(toDeleteContactTel);
		contactDao.save(contact);
		listContactsWithDetail(contactDao.findAllWithDetail());

	}
    
    private static void saveContact(ContactDao contactDao){
    	System.out.println();
    	Contact contact = new Contact();
    	contact.setFirstName("Michael");
    	contact.setLastName("Jackson");
    	contact.setBirthDate(new Date());
    	
    	ContactTelDetail contactTelDetail =	new ContactTelDetail();
    	contactTelDetail.setTelType("Home");
    	contactTelDetail.setTelNumber("1111111111");
    	contact.addContactTelDetail(contactTelDetail);
    	
    	contactTelDetail = new ContactTelDetail();
    	contactTelDetail.setTelType("Mobile");
    	contactTelDetail.setTelNumber("2222222222");
    	contact.addContactTelDetail(contactTelDetail);
    	
    	contactDao.save(contact);
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
