package com.wu.springbootrest.VaccinationMgmtApp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wu.springbootrest.VaccinationMgmtApp.entity.Citizen;

@Repository
public class CitizenDAOImplementation implements CitizenDAO {
	
	private EntityManager entityManager;
	
	public CitizenDAOImplementation(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	
	@Override
	@Transactional
	public List<Citizen> findAll() {
		Session currentSession=entityManager.unwrap(Session.class);
		Query<Citizen> theQuery=currentSession.createQuery("from Citizen", Citizen.class);
		
		List<Citizen> citizens=theQuery.getResultList();
		
		return citizens;
	}

	@Override
	@Transactional
	public Citizen findById(int theAadhar) {
		Session currentSession=entityManager.unwrap(Session.class);
		Citizen theCitizen=currentSession.get(Citizen.class, theAadhar);
		return theCitizen;
	}

	@Override
	@Transactional
	public void save(Citizen theCitizen) {
		Session currentSession=entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theCitizen);
	}
	
	@Override
	@Transactional
	public void update(Citizen theCitizen) {
		Session currentSession=entityManager.unwrap(Session.class);
		currentSession.merge(theCitizen);
	}

	@Override
	@Transactional
	public void deleteCitizen(int theAadhar) {
		Session currentSession=entityManager.unwrap(Session.class);
		Query theQuery=currentSession.createQuery("delete from Citizen where citizenAadhar=:id and dosesGiven=:num");
		theQuery.setParameter("id", theAadhar);
		theQuery.setParameter("num", 3);
		theQuery.executeUpdate();
	}

}
