package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("keyService")
public class KeyServiceImpl implements KeyService {

    @Autowired
    private Environment environment;

    @Override
    public String giveMekey() {
        return environment.getRequiredProperty("MY_KEY");
    }
}
