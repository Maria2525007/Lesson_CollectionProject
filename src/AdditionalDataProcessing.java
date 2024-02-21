public class AdditionalDataProcessing {

    public static String[] processDataForCreate(Command command)
                                        throws ArrayIndexOutOfBoundsException, NumberFormatException{
            String[] res = command.getData().replace(" ", "").split(",");
            Validator.validateData(res);
            return res;
    }

    public static String[] processDataForUpdate(Command command) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String[] res = command.getData().replace(" ", "").split(",");
        Validator.validateData(res);
        return res;
    }

    public static void printMessage(Command command) {
        System.out.println("Обработка команды "
                + command.getAction().name() + " с данными "
                + command.getData());
    }
}

