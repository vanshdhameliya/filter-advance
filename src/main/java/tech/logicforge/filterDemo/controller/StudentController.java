package tech.logicforge.filterDemo.controller;

import tech.logicforge.filterDemo.dto.Student;
import tech.logicforge.filterDemo.dto.StudentResponseDto;
import tech.logicforge.filterDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody Student student) {
        StudentResponseDto responseDto =
                studentService.createStudent(student);
        return ResponseEntity.ok(responseDto);
    }
}
