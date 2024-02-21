import java.util.Scanner;
public class Main {
    private static final StudentCommandHandler STUDENT_COMMAND_HANDLER
            = new StudentCommandHandler();
    public static void main(String[] args) {
          while (true) {
              printMessage();
              Command command = readCommand();
              if (command.getAction() == Action.EXIT) {
                  System.out.println("Goodbye!");
                  break;
              } else if (command.getAction() == Action.ERROR) {
                  break;
              }
              else {
                  STUDENT_COMMAND_HANDLER.processCommand(command);
              }
          }
    }
    private static Command readCommand() {
        Scanner scanner = new Scanner(System.in);
        try {
            String code = scanner.nextLine();
            Integer actionCode = Integer.valueOf(code);
            Action action = Action.fromCode(actionCode);
            if (action.getIsRequireAdditionalData()) {
                String data = scanner.nextLine();
                return new Command(action, data);
            }
            else {
                return new Command(action);
            }
        }
        catch (Exception ex) {
            System.out.println("Unknown input: " + ex.getMessage());
            return new Command(Action.ERROR);
        }
    }

    private static void printMessage() {
        System.out.println("Choose an action:");
        System.out.println("-------------------------");
        System.out.println("0 - exit");
        System.out.println("1 - create");
        System.out.println("2 - update");
        System.out.println("3 - delete");
        System.out.println("4 - stats by courses");
        System.out.println("5 - stats by cities");
        System.out.println("6 - search");
        System.out.println("-------------------------");
    }
}