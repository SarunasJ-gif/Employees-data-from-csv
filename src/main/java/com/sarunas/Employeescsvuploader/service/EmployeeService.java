package com.sarunas.Employeescsvuploader.service;

import com.sarunas.Employeescsvuploader.model.Employee;
import com.sarunas.Employeescsvuploader.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void uploadCSV(InputStream csvFile) throws FileNotFoundException {
        ZipInputStream zipInputStream = new ZipInputStream(csvFile);
        try {
            zipInputStream.getNextEntry();
            InputStreamReader inputStreamReader = new InputStreamReader(zipInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().skip(1).map(Employee::scan).forEach(employeeRepository::save);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
