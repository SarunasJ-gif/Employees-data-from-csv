package com.sarunas.Employeescsvuploader;

import com.sarunas.Employeescsvuploader.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeesCsvUploaderApplicationTests {

	@Test
	public void canScan() {
		String csvRow = "John Smith, john.smith@gmail.com, 8-621-22147";
		Employee employee = Employee.scan(csvRow);
		assertThat(employee.getEmail().equals("john.smith@gmail.com"));
	}
}
