package com.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="contact")
@NamedQueries({
	@NamedQuery(name="Contact.findAllWithDetail", query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")
})
public class Contact implements Serializable {
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Set<ContactTelDetail> contactTelDetails = new HashSet<ContactTelDetail>();
	private Set<Hobby> hobbies = new HashSet<Hobby>();
	
	public Contact() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name="VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<ContactTelDetail> getContactTelDetails() {
		return contactTelDetails;
	}

	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		this.contactTelDetails = contactTelDetails;
	}
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}
	
	@ManyToMany
	@JoinTable(name="contact_hobby_detail", 
		joinColumns=@JoinColumn(name="CONTACT_ID"),
		inverseJoinColumns=@JoinColumn(name="HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public String toString() {
		return "Contact - Id: " + id + ", First name: " + firstName
		+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}
}
