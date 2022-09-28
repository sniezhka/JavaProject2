package com.example.student.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.student.dto.input.student.CreateStudentDTO;
import com.example.student.dto.input.student.UpdateStudentDTO;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	@Autowired
	private final StudentService service;

	@GetMapping("/{id}")
	public Student get(@PathVariable String id) {
		Optional<Student> student = service.get(id);

		if (student.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
		return student.get();
	}

	@PostMapping()
	public Student create(@RequestBody CreateStudentDTO input) {
		return service.create(input);
	}

	@PutMapping("/{id}")
	public Student update(@PathVariable String id, @RequestBody UpdateStudentDTO input) {
		return service.update(id, input);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.remove(id);
	}
}
