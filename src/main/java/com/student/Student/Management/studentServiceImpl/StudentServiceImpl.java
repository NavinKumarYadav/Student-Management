package com.student.Student.Management.studentServiceImpl;

import com.student.Student.Management.entity.Student;
import com.student.Student.Management.repository.StudentRepository;
import com.student.Student.Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> optionalStudent = repo.findById(id);
        return optionalStudent.orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    @Override
    public Student updateStudent(int id, Student updatedStudent) {
        Optional<Student> optionalStudent = repo.findById(id);
        if (optionalStudent.isPresent()) {
            Student existing = optionalStudent.get();
            existing.setName(updatedStudent.getName());
            existing.setEmail(updatedStudent.getEmail());
            existing.setAge(updatedStudent.getAge());
            existing.setBranch(updatedStudent.getBranch());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}
