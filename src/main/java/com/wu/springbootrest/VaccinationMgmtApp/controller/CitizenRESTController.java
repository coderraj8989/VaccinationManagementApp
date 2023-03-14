package com.wu.springbootrest.VaccinationMgmtApp.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.springbootrest.VaccinationMgmtApp.dao.CitizenDAO;
import com.wu.springbootrest.VaccinationMgmtApp.entity.Citizen;

@RestController
@RequestMapping("/api")
public class CitizenRESTController {
	
	private CitizenDAO citizenDAO;

	public CitizenRESTController(CitizenDAO theCitizenDAO) {
		this.citizenDAO = theCitizenDAO;
	}
	
	@GetMapping("/citizens")
	public List<Citizen> findAll(){
		
		return citizenDAO.findAll();
		
	}
	
	@GetMapping("/citizens/{citizenId}")
	public Citizen findById(@PathVariable int citizenId) {
		
		return citizenDAO.findById(citizenId);
		
	}
	
	@PostMapping("/citizens")
	public String addCitizen(@RequestBody Citizen theCitizen)	{
		if(theCitizen.getAge()>=18)
		{
			theCitizen.setCitizenAadhar(0);
			theCitizen.setVaccineName("none");
			theCitizen.setDosesGiven(0);
			theCitizen.setLastVaccinated(new Date());
			citizenDAO.save(theCitizen);	
			return "Registration Successful!";
		}
		else {
			return "Age must be greater than or equal to 18 for registration.";
		}
	}
	
	@PutMapping("/citizens")
	public String updateCitizen(@RequestBody Citizen theCitizen) {
		
		int prevDoseNumber = citizenDAO.findById(theCitizen.getCitizenAadhar()).getDosesGiven();
		int currDosesNumber=theCitizen.getDosesGiven();
		
		if(prevDoseNumber==0 && currDosesNumber==1) {
			citizenDAO.update(theCitizen);
			return theCitizen.getName()+" vaccinated with dose "+
					theCitizen.getDosesGiven()+" of "+theCitizen.getVaccineName();
		}
		else if((prevDoseNumber==1 && currDosesNumber==2) || (prevDoseNumber==2 && currDosesNumber==3))
		{
			String prevVaccineName = citizenDAO.findById(theCitizen.getCitizenAadhar()).getVaccineName();
			String currVaccineName=theCitizen.getVaccineName();
			
			Date prevVaccineDate = citizenDAO.findById(theCitizen.getCitizenAadhar()).getLastVaccinated();	
			Date currDate=theCitizen.getLastVaccinated();
			
			long diffInMillies = Math.abs(currDate.getTime() - prevVaccineDate.getTime());
		    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    
		    if(diffInDays<=120)
		    	return "There should be a gap of 120 days between two consecutive vaccine doses";
			if(!(prevVaccineName.equalsIgnoreCase(currVaccineName)))
					return "Enter same vaccine name as the previous vaccine.";
		    if(prevVaccineName.equalsIgnoreCase(currVaccineName) && diffInDays>120)
		    {
		    	citizenDAO.update(theCitizen);
				return theCitizen.getName()+" vaccinated with dose "+
						theCitizen.getDosesGiven()+" of "+theCitizen.getVaccineName();
		    }
		}
		else {    
			if(prevDoseNumber+1!=currDosesNumber)
				return "Cannot give dose "+currDosesNumber+" for the second time.";
		}
		return "";
	}
	
	@DeleteMapping("/citizens/{citizenId}")
	public String deleteCitizen(@PathVariable int citizenId) {
		int prevDoseNumber = citizenDAO.findById(citizenId).getDosesGiven();
		if(prevDoseNumber==3)
		{
			citizenDAO.deleteCitizen(citizenId);
			return "Vaccination completed. Clearing the record!"; 
		}
		return "You are still not fully vaccinated.";
	}
	
}
