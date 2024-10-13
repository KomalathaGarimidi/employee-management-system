package com.emplye_mangemnt_system.EMS.controller;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import com.emplye_mangemnt_system.EMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ems/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> creatingEmployee(@RequestBody Employee employee){
        //check whether the employee is already  present or not before creating
        if(findEmployeById(employee.getEmpId())){
            System.out.println("Employee is already exists");
            return new ResponseEntity<>(employee,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{empid}")
    public boolean findEmployeById(@PathVariable int empid){
        return employeeService.findEmployeById(empid);
    }

    @PostMapping("/list")
    public void batchInsert(@RequestBody List<Employee> employeeList){
        employeeService.batchInsert(employeeList);
    }

    @PostMapping("/id")
    public Employee updateEmp(@RequestBody Employee employee){
        System.out.println("updateemp() from controller");
        employeeService.updateEmp(employee);
        return employee;
    }


    
    @GetMapping("/delete/{rollno}")
    public boolean deleteEmp(@PathVariable int rollno){
        System.out.println("delete() from controller");
        return employeeService.deleteEmp(rollno);
    }

    //getting all the employees list
    @GetMapping("/all")
    public List<Map<String, Object>> readEmp(){
        return new ResponseEntity<>(employeeService.readEmp(),HttpStatus.OK).getBody();
    }
}
