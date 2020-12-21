package com.cleancode.payroll.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.cleancode.payroll.model.Employee;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.util.ResourceUtils;
import org.springframework.stereotype.Service;

@Service
public class ReadFileServiceImpl implements ReadFileService {

  @Override
  public String getDataFromCSV() {
    try {
      File file = ResourceUtils.getFile("classpath:data/employee.csv");

      CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
      ObjectReader oReader = mapper.readerFor(Employee.class).with(schema);

      StringBuilder sb = new StringBuilder(""); // sb dùng để cộng các chuỗi toString của đối tượng Employee

      Reader reader = new FileReader(file);
      MappingIterator<Employee> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
      while (mi.hasNext()) {
        Employee employee = mi.next();
        sb.append(employee.toString() + "<br>");
      }
      return sb.toString(); // trả về trình duyệt
    } catch (FileNotFoundException e) {
      return "File not found";
    } catch (IOException e) {
      return "IO Exception Error";
    }
  }
}
