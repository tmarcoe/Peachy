package com.peachy.web.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.peachy.web.dao.Survey;
import com.peachy.web.dao.SurveyDao;

@Service("surveyService")
public class SurveyService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public void create(Survey survey) {
		surveyDao.create(survey);
	}
	
	public PagedListHolder<Survey> retrieveAll() {
		
		return  new PagedListHolder<Survey>(surveyDao.retrieveAll());
	}
	
	public Survey retrieve(int userID) {
		return surveyDao.retrieve(userID);
	}
}
