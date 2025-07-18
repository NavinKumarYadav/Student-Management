package com.student.Student.Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.student.Student.Management.entity.Student;
import com.student.Student.Management.repository.StudentRepository;

@RestController
@RequestMapping
public class StudentController {

	@Autowired
	StudentRepository repo;

	@GetMapping("/students")
	public List<Student> getAllStudents() {

		List<Student> students = repo.findAll();

		return null;
	}

	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();

		return student;
	}

	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createStudent(@RequestBody Student student) {
		repo.save(student);

	}

	@PutMapping("/student/update/{id}")
	public Student updateStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();

		return student;
	}

	@DeleteMapping("/student/delete/{id}")
	public void deleteStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}
}
