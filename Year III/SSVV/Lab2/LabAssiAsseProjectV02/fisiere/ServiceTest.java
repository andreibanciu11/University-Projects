import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.StudentXMLRepo;
import validation.StudentValidator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestAddStudent {
    private Service service;
    private StudentXMLRepo studentRepo;
    private StudentValidator studentValidator;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        studentRepo = Mockito.mock(StudentXMLRepo.class);
        studentValidator = Mockito.mock(StudentValidator.class);

        // Instantiate the Service with mocked dependencies
        service = new Service(studentRepo, studentValidator, null, null, null, null);
    }

    @Test
    void addStudentSuccessfully() {
        // Given
        Student newStudent = new Student("1", "John Doe", 933, "john.doe@email.com");
        when(studentRepo.save(any(Student.class))).thenReturn(null); // Simulate successful save

        // When
        Student result = service.addStudent(newStudent);

        // Then
        assertNull(result); // Expecting null as per the Service implementation on successful add
        verify(studentRepo).save(newStudent); // Verify save was called with the new student
    }

    @Test
    void addExistingStudent() {
        // Given
        Student existingStudent = new Student("1", "John Doe", 933, "john.doe@email.com");
        when(studentRepo.save(any(Student.class))).thenReturn(existingStudent); // Simulate the student already exists

        // When
        Student result = service.addStudent(existingStudent);

        // Then
        assertNotNull(result); // Expecting the existing student as per the Service implementation
        assertEquals("1", result.getID()); // Verify that the returned student is the one we tried to add
        verify(studentRepo).save(existingStudent); // Verify save was called with the existing student
    }
}