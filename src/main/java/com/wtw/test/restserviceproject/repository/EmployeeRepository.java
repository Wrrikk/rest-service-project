package com.wtw.test.restserviceproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wtw.test.restserviceproject.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
