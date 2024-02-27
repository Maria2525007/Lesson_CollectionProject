import java.rmi.StubNotFoundException;
import java.util.*;

public class StudentCommandHandler {
    private final StudentSurnameStorage studentSurnameStorage = new StudentSurnameStorage();
    private final StudentStorage studentStorage = new StudentStorage(studentSurnameStorage);

    public void processCreateCommand(Command command) {
        try {
            String[] dataArray = AdditionalDataProcessing.processDataForCreate(command);
            Student student = new Student();
            student.setSurname(dataArray[0]);
            student.setName(dataArray[1]);
            student.setCourse(dataArray[2]);
            student.setCity(dataArray[3]);
            student.setAge(Integer.valueOf(dataArray[4]));
            studentStorage.createStudent(student);
            AdditionalDataProcessing.printMessage(command);
            studentStorage.printAll();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

    }

    private void processUpdateCommand(Command command) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        Long id = null;
        try {
            String[] dataArray = AdditionalDataProcessing.processDataForUpdate(command);
            id = Long.valueOf(dataArray[0]);
            if (!studentStorage.containsStudent(id)) {
                throw new StudentNotFoundException();
            }
            Student student = new Student();
            student.setSurname(dataArray[1]);
            student.setName(dataArray[2]);
            student.setCourse(dataArray[3]);
            student.setCity(dataArray[4]);
            student.setAge(Integer.valueOf(dataArray[5]));
            studentStorage.updateStudent(id, student);
            AdditionalDataProcessing.printMessage(command);
            studentStorage.printAll();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage(id));
        }
    }

    private void processDeleteCommand(Command command) {
        String data = command.getData();
        Long id = Long.valueOf(data);
        studentStorage.deleteStudent(id);
        AdditionalDataProcessing.printMessage(command);
        studentStorage.printAll();
    }

    public void processCommand(Command command) {
        Action action = command.getAction();
        switch (action) {
            case CREATE: {
                processCreateCommand(command);
                break;
            }
            case UPDATE: {
                processUpdateCommand(command);
                break;
            }
            case DELETE: {
                processDeleteCommand(command);
                break;
            }
            case STATS_BY_STUDENTS: {
                processStatsByCoursesCommand();
                break;
            }
            case STATS_BY_CITIES: {
                processStatsByCitiesCommand();
                break;
            }
            case SEARCH: {
                processSearchByCommand(command);
                break;
            }
            default: {
                System.out.println("Unknown action: " + action);
            }
        }

    }

    private void processStatsByCoursesCommand() {
        Map<String, Long> data = studentStorage.getCountByCourse();
        studentStorage.printMap(data);
    }

    private void processStatsByCitiesCommand() {
        Map<String, Long> data = studentStorage.getCountByCities();
        studentStorage.printMap(data);
    }

    private void processSearchByCommand(Command command) {
        String data = command.getData();
        if (data == null || data.isEmpty()) {
            studentSurnameStorage.printAllSurnames();
        } else if (data.contains(",")) {
            String[] surnames = data.replaceAll(" ", "").split(",");
            if (surnames.length == 2) {
                Set<String> foundSurnames = studentSurnameStorage.getSearchByCommand(surnames);
                if (!foundSurnames.isEmpty()) {
                    System.out.println(foundSurnames);
                } else {
                    System.out.println("No students with entered surnames were found");
                }
            }
        } else {
            Set<String> foundIds = studentSurnameStorage.getSurnamesLessOrEqualThan(data);
            if (foundIds != null) {
                System.out.println(foundIds);
            }
        }
    }
}
