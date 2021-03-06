package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.SetUpDao;

@Service("setupService")
@Transactional
public class SetUpServiceImpl implements SetUpService {

	@Autowired
	private SetUpDao setUpDao;
	
	@Override
	public void setup(String fileName) {
		setUpDao.setup(fileName);

	}

}
