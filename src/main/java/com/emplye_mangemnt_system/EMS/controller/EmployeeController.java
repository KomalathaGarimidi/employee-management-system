package com.emplye_mangemnt_system.EMS.controller;
import com.emplye_mangemnt_system.EMS.dto.EmployeeDTO;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import com.emplye_mangemnt_system.EMS.service.EmployeeService;
import org.modelmapper.ModelMapper;
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


    @Autowired
    ModelMapper modelMapper;
    //creating new employee
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> creatingEmployee(@RequestBody EmployeeDTO employeeDTO){
        //check whether the employee is already  present or not before

        Employee employee =modelMapper.map(employeeDTO, Employee.class);
        employee=employeeService.saveEmployee(employee);
        employeeDTO  = modelMapper.map(employee, EmployeeDTO.class);

        return new ResponseEntity<>(employeeDTO,HttpStatus.OK);
    }

    //find employee using empid
    @GetMapping("/{empid}")
    public ResponseEntity<Employee> findEmployeById(@PathVariable int empid){

         employeeService.findEmployeById(empid);
         return new ResponseEntity<>(employeeService.findEmployeById(empid),HttpStatus.OK);
    }

    //inserting batch of files
    @PostMapping("/list")
    public void batchInsert(@RequestBody List<Employee> employeeList){
        employeeService.batchInsert(employeeList);
    }


    //updating the existing employess
    @PostMapping("/id")
    public Employee updateEmp(@RequestBody Employee employee){
        System.out.println("updateemp() from controller");
        employeeService.updateEmp(employee);
        return employee;
    }


    //delete the existing employes
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
