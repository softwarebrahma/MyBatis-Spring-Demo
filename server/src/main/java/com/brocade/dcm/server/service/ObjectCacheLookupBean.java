package com.brocade.dcm.server.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectCacheLookupBean {
	
	@Autowired
	private Ignite ignite;
	
	@PostConstruct
	public void setupViewCache() {
		System.out.println("*******************TESTING******************* FROM ObjectCacheLookupBean : " + ignite.name());
	}
}
