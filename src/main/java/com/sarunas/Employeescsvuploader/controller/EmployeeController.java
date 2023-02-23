package com.sarunas.Employeescsvuploader.controller;


import com.sarunas.Employeescsvuploader.model.Employee;
import com.sarunas.Employeescsvuploader.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employees = new ArrayList<>(employeeService.findAllEmployees());
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam @Valid MultipartFile csvFile) throws FileNotFoundException {
        try {
            employeeService.uploadCSV(csvFile.getInputStream());
        } catch (IOException e) {
            throw new
                    FileNotFoundException("File not found");
        }
        return "File successfully uploaded";
    }
}
