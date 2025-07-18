package com.student.Student.Management.service;

import java.util.List;

import com.student.Student.Management.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student getStudentById(int id);

	Student saveStudent(Student student);

	Student updateStudent(int id, Student student);

	void deleteStudent(int id);

}
