package com.wu.springbootrest.VaccinationMgmtApp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="citizens")
@SequenceGenerator(name="MySequence", sequenceName="citizen_seq", initialValue = 1, allocationSize = 1)
public class Citizen {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "MySequence")
	@Column(name="citizen_aadhar")
	private int citizenAadhar;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private int age;
	
	@Column(name="email")
	private String email;
	
	@Column(name="vaccine_name")
	private String vaccineName;
	
	@Column(name="doses_given")
	private int dosesGiven;
	
	@Column(name="last_vaccinated")
	private Date lastVaccinated;
	
	public Citizen() {}

	public Citizen(int citizenAadhar, String name, int age, String email, String vaccineName, int dosesGiven,
			Date lastVaccinated) {
		super();
		this.citizenAadhar = citizenAadhar;
		this.name = name;
		this.age = age;
		this.email = email;
		this.vaccineName = vaccineName;
		this.dosesGiven = dosesGiven;
		this.lastVaccinated = lastVaccinated;
	}

	public int getCitizenAadhar() {
		return citizenAadhar;
	}

	public void setCitizenAadhar(int citizenAadhar) {
		this.citizenAadhar = citizenAadhar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public int getDosesGiven() {
		return dosesGiven;
	}

	public void setDosesGiven(int dosesGiven) {
		this.dosesGiven = dosesGiven;
	}

	public Date getLastVaccinated() {
		return lastVaccinated;
	}

	public void setLastVaccinated(Date lastVaccinated) {
		this.lastVaccinated = lastVaccinated;
	}

	@Override
	public String toString() {
		return "Citizen [citizenAadhar=" + citizenAadhar + ", name=" + name + ", age=" + age + ", email=" + email
				+ ", vaccineName=" + vaccineName + ", dosesGiven=" + dosesGiven + ", lastVaccinated=" + lastVaccinated
				+ "]";
	}
	
	
}
