package com.wu.springbootrest.VaccinationMgmtApp.dao;

import java.util.List;

import com.wu.springbootrest.VaccinationMgmtApp.entity.Citizen;

public interface CitizenDAO {
	
	public List<Citizen> findAll();
	
	public Citizen findById(int theAadhar);
	
	public void save(Citizen theCitizen);

	public void update(Citizen theCitizen);
	
	public void deleteCitizen(int theAadhar);

	
}
