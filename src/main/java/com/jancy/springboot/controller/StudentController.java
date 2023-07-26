package com.jancy.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jancy.springboot.exception.ResourceNotFoundException;
import com.jancy.springboot.model.Student;
import com.jancy.springboot.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepositoty;
	
	//get all students
	@GetMapping("/student")
	public List<Student> getAllStudents(){
		return studentRepositoty.findAll();
	}
	//create student rest api
	
	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		return studentRepositoty.save(student);
	}
	
	//Get Student by Id rest Api
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student=studentRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Studnet not exists with id:" +id));
		return ResponseEntity.ok(student);
	}
	
	//update student rest api
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student studentDetails){
		
		Student student=studentRepositoty.findById(id).get();
		student.setName(studentDetails.getName());
	    student.setAge(studentDetails.getAge());
		student.setAddress(studentDetails.getAddress());
		
		Student updatedStudent=studentRepositoty.save(student);
		return ResponseEntity.ok(updatedStudent);
		
	}
	
	//delete student rest api
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
		Student student=studentRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Studnet not exists with id:" +id));
		
		studentRepositoty.delete(student);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
	
	 
	 //get student by name
	  
	  @GetMapping("/studentbyname/{name}") 
	    public ResponseEntity<Student> getStudentByName(@PathVariable String name) { 
	    Student student=studentRepositoty.findByName(name);
		return ResponseEntity.ok(student); }

	
	  
	  //get studentbyid
	  @GetMapping("studentbyid/{id}")
	  public ResponseEntity<Student > getStudentByIdAll(@PathVariable Long id){
		  Student student=studentRepositoty.findByIdAll(id);
		  return ResponseEntity.ok(student);
	  }
}
