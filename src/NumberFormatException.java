public class NumberFormatException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The entered data is of the wrong type";
    }
}
