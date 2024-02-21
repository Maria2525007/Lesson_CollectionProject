public class AdditionalDataProcessing {

    public static String[] processDataForCreate(Command command)
                                        throws ArrayIndexOutOfBoundsException, NumberFormatException{
            String[] res = command.getData().replaceAll(" ", "").split(",");
            if (res.length != 5) {
                throw new ArrayIndexOutOfBoundsException();
            }
            else {
                Validator.validateData(res);
            }
            return res;
    }

    public static String[] processDataForUpdate(Command command) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String[] res = command.getData().replaceAll(" ", "").split(",");
        if (res.length != 6) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            Validator.validateData(res);
        }
        return res;
    }

    public static void printMessage(Command command) {
        System.out.println("Обработка команды "
                + command.getAction().name() + " с данными "
                + command.getData());
    }
}

