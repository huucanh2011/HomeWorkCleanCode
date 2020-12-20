package com.cleancode.payroll.controller;

import com.cleancode.payroll.service.ReadFileService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  ReadFileService readFileService = new ReadFileService();

  @ResponseBody
  @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
  public String getEmployees() {
    return readFileService.getDataFromCSV();
  }
}
