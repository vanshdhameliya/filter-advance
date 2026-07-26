package tech.logicforge.filterDemo.service;

import tech.logicforge.filterDemo.dto.Student;
import tech.logicforge.filterDemo.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public StudentResponseDto createStudent(Student student) {
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setName(student.getName());
        responseDto.setMessage("Student is saved successfully");

        return responseDto;
    }
}
