package rest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import rest.dto.StudentDto;
import rest.persistence.entity.Student;
import rest.persistence.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
// прописать семетеры и гетеры из студентДто
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository userRepository) {
        this.studentRepository = userRepository;
    }

    public ModelAndView createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setId(UUID.randomUUID());
        student.setNumber(studentDto.getNumber());
        student.setName(studentDto.getName());
        student.setBirthDate(studentDto.getBirthDate());

        studentRepository.save(student);

        return getAllStudents();
    }

    public ModelAndView getAllStudents() {
        List<Student> students = studentRepository.findAllStudents();
        List<StudentDto> resultList = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            studentDto.setTitle(student.getId().toString());
            studentDto.setAddress(student.getNumber());
            studentDto.setBerth_tiny(student.getName());
            studentDto.setRent(student.getBirthDate());
            studentDto.setDescription(student.getBirthDate());
            resultList.add(studentDto);
        }

        return createAndFillModel(resultList);
    }

    private ModelAndView createAndFillModel(List<StudentDto> studentDtos) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        modelAndView.getModel().put("listStudents", studentDtos);
        modelAndView.setViewName("studets-page");
        return modelAndView;
    }

    public void removeStudentById(UUID id) {
        studentRepository.deleteById(id);
    }
}
