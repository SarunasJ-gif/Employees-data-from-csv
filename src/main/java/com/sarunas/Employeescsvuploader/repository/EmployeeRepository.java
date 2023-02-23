package com.sarunas.Employeescsvuploader.repository;

import com.sarunas.Employeescsvuploader.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
