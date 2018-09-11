package telran.students.service;

import org.springframework.stereotype.Service;

import telran.students.dao.IStudentRepository;
import telran.students.dto.ScoreDto;
import telran.students.dto.StudentDto;
import telran.students.dto.StudentEditDto;
import telran.students.dto.StudentResponseDto;
import telran.students.entity.Student;

@Service
public class StudentServiceImpl implements IStudentService {
	IStudentRepository studentRepository;
	

	@Override
	public boolean addStudent(StudentDto student) {
		Student newStudent = new Student(student.getId(), student.getName(), student.getPassword());
		return studentRepository.addStudent(newStudent);
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		Student student = studentRepository.removeStudent(id);
		StudentResponseDto studentResponse  = new StudentResponseDto();
		studentResponse.setId(student.getId());
		studentResponse.setName(student.getName());
		studentResponse.setScores(student.getScores());
		
		return studentResponse ;
	}

	@Override
	public StudentDto editStudent(int id, StudentEditDto student) {
		Student studentEntity = studentRepository.getStudentById(id);
		
		studentEntity.setName(student.getName());
		studentEntity.setPassword(student.getPassword());
		
		Student editStudent = studentRepository.editStudent(studentEntity);
		StudentDto studentRes = new StudentDto();
		studentRes.setName(editStudent.getName());
		studentRes.setPassword(editStudent.getPassword());
		
		return studentRes;
	}

	@Override
	public StudentResponseDto getStudent(int id) {
		Student student = studentRepository.getStudentById(id);
		StudentResponseDto studentResponse = new StudentResponseDto();
		studentResponse.setId(student.getId());
		studentResponse.setName(student.getName());
		studentResponse.setScores(student.getScores());
		return studentResponse;
	}

	@Override
	public boolean addScore(int id, ScoreDto score) {
	
		return studentRepository.getStudentById(id)
				.addScore(score.getExamName(), score.getScore()); 
	}

}
