package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {


  public DemoServiceImpl() {
  }
 
  public String save() {
	  return "uuid";
  }
 
  public void getPdf(String uuid) {

  }
}
