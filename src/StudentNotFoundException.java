public class StudentNotFoundException extends Exception {

    public String getMessage(Long id) {
        return "There is no student with id = " + id + " in the storage";
    }
}