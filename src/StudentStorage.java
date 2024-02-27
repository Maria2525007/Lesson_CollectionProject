import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentStorage {
    private Map<Long, Student> studentStorageMap = new HashMap<>();
    private StudentSurnameStorage studentSurnameStorage;
    public StudentStorage(StudentSurnameStorage studentSurnameStorage) {
        this.studentSurnameStorage = studentSurnameStorage;
    }
    private Long currentId = 0L;

    /**
     * Создание данных о студенте
     * @param student данные о студенте
     */
    public void createStudent(Student student) {
        Long id = getNextId();
        studentStorageMap.put(id, student);
        studentSurnameStorage.studentCreated(id, student.getSurname());
    }

    /**
     * Обновление данных студента
     * @param id идентификатор студента
     * @param student новые данные о студенте
     */
    public void updateStudent(Long id, Student student) {
        if (studentStorageMap.containsKey(id)) {
            studentSurnameStorage.studentUpdated(id, studentStorageMap.get(id).getSurname(), student.getSurname());
            studentStorageMap.put(id, student);
        }
    }

    /**
     * Удаление данных о студенте
     * @param id идентификатор студента
     */
    public void deleteStudent(Long id) {
        Student removed = studentStorageMap.remove(id);
        if (removed != null) {
            studentSurnameStorage.studentDeleted(id, removed.getSurname());
        }
    }

    public Long getNextId() {
        currentId = currentId + 1;
        return currentId;
    }

    public void printAll() {
        System.out.println(studentStorageMap);
    }

    public void printMap(Map<String, Long> data) {
       data.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public Map<String, Long> getCountByCourse() {
        return studentStorageMap.values().stream()
                .collect(Collectors.toMap(
                        Student::getCourse,
                        student -> 1L,
                        Long::sum
                ));
    }
    public Map<String, Long> getCountByCities() {
        Map<String, Long> stats = new HashMap<>();
        for (Student student : studentStorageMap.values()) {
            stats.put(student.getCity(), stats.getOrDefault(student.getCity(), 0L) + 1);
        }
        return stats;
    }

    public boolean containsStudent(Long id) {
        return studentStorageMap.containsKey(id);
    }

}
