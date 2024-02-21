public class Validator {

    public static boolean isValidName(String name) {
        return name != null && isString(name);
    }

    public static boolean isValidSurname(String surname) {
        return surname != null && isString(surname);
    }

    public static boolean isValidCourse(String course) {
        return course != null && isString(course);
    }

    public static boolean isValidCity(String city) {
        return city != null && isString(city);
    }

    public static boolean isValidAge(String age) throws NumberFormatException {
            for (char c : age.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            int ageValue = Integer.parseInt(age);
            if (ageValue <= 0 || ageValue >= 100) {
                throw new NumberFormatException();
            }
            return true;
    }

    private static boolean isString(String str) {
        return str.matches("^[a-zA-Zа-яА-Я\\s]*$");
    }

    public static boolean isValidId(String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static boolean validateData(String[] res) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        switch (res.length) {
            case 5 -> {
                if (!Validator.isValidName(res[1]) || !Validator.isValidSurname(res[0]) ||
                        !Validator.isValidCourse(res[2]) || !Validator.isValidCity(res[3]) ||
                        !Validator.isValidAge(res[4])) {
                    throw new NumberFormatException();
                } else {
                    return true;
                }
            }
            case 6 -> {
                if (!Validator.isValidId(res[0]) || !Validator.isValidName(res[2]) || !Validator.isValidSurname(res[1]) ||
                        !Validator.isValidCourse(res[3]) || !Validator.isValidCity(res[4]) ||
                        !Validator.isValidAge(res[5])) {
                    throw new NumberFormatException();
                } else {
                    return true;
                }
            }
            default -> throw new ArrayIndexOutOfBoundsException();
        }
    }
}
