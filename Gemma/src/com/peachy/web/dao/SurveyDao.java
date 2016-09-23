package com.peachy.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Component("surveyDao")
public class SurveyDao  {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(Survey survey) {
		session().save(survey);
		session().disconnect();
	}
	
	@SuppressWarnings("unchecked")
	public List<Survey> retrieveAll() {
		Criteria crit = session().createCriteria(Survey.class);
		List<Survey> allSurveys = crit.list();
		session().disconnect();
		
		return allSurveys;
	}
	
	public Survey retrieve(int userID) {
		Criteria crit = session().createCriteria(Survey.class);
		crit.add(Restrictions.eq("userID",userID));		
		Survey survey = (Survey) crit.uniqueResult();
		session().disconnect();
		
		return survey;
	}
	
	

}
