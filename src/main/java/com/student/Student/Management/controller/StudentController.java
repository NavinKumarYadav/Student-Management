package com.student.Student.Management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.Student.Management.entity.Student;
import com.student.Student.Management.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository repo;

	@GetMapping("/students")
	public List<Student> getAllStudents() {

		return repo.findAll();


	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		Optional<Student> student = repo.findById(id);
		return student.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/students")
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		return repo.save(student);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
		Optional<Student> optionalStudent = repo.findById(id);
		if (optionalStudent.isPresent()) {
			Student existing = optionalStudent.get();
			existing.setName(updatedStudent.getName());
			existing.setEmail(updatedStudent.getEmail());
			existing.setAge(updatedStudent.getAge());
			existing.setBranch(updatedStudent.getBranch());
			return ResponseEntity.ok(repo.save(existing));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
		Optional<Student> student = repo.findById(id);
		if (student.isPresent()) {
			repo.delete(student.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
