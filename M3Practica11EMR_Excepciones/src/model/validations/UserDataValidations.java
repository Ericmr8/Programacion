package model.validations;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import exceptions.CheckDNIException;
import exceptions.CheckEmailException;

public class UserDataValidations {

    public static boolean checkId(int typeDoc, String id) throws CheckDNIException {
        boolean idOK = false;
        if (typeDoc == 1) {
            if (id.length() == 9) {
                String nums = id.substring(0, 8);
                char letra = id.charAt(8);
                for (int i = 0; i < nums.length(); i++) {
                    if (Character.isDigit(nums.charAt(i))) {
                        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
                        int numeros = Integer.parseInt(nums);
                        int resto = numeros % 23;
                        if (letra == letras[resto]) {
                            return idOK = true;
                        }
                    }
                }
            }
        }
        throw new CheckDNIException("Wrong id");
    }

    public static boolean checkFormatDate(String Date) {
        boolean dateOK = false;
        if (Date.length() == 10) {
            if (Date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                String[] fecha = Date.split("/");

                int day = Integer.parseInt(fecha[0]);
                int month = Integer.parseInt(fecha[1]);

                int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                if (day < 1 || day > 31) {
                    return false;
                }

                if (month < 1 || month > 12) {
                    return false;
                }

                if (day < monthDays[month] - 1) {
                    dateOK = true;
                }

            }
        }
        return dateOK;
    }

    public static double checkAge(String birth) throws ParseException {
        int ageOK = -1;

        if (birth.length() == 10) {
            if (birth.matches("\\d{2}/\\d{2}/\\d{4}")) {
                String[] fecha = birth.split("/");

                int day = Integer.parseInt(fecha[0]);
                int month = Integer.parseInt(fecha[1]);

                int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                if (day < 1 || day > 31) {
                    ageOK = -1;
                }

                if (month < 1 || month > 12) {
                    ageOK = -1;
                }

                if (day < monthDays[month] - 1) {
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    numberFormat.setMaximumFractionDigits(2);

                    Date currentDate = new Date(System.currentTimeMillis());
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    Date birthdate = date.parse(birth);

                    int milisecondsByDay = 86400000;
                    int dias = (int) ((currentDate.getTime() - birthdate.getTime()) / milisecondsByDay);

                    int años = dias / 365;
                    ageOK = años;
                }
            }
        }
        return ageOK;
    }

    public static boolean checkPostalCode(String zip) {
        boolean cpOK = false;
        if (zip.matches("\\d{5}")) {
            cpOK = true;
        }
        return cpOK;
    }

    public static boolean isNumeric(String num) {
        boolean numOK = false;
        for (int i = 0; i < num.length(); i++) {
            if (Character.isDigit(num.charAt(i))) {
                numOK = true;
            } else {
                numOK = false;
                return numOK;
            }
        }
        return numOK;
    }

    public static boolean isAlphabetic(String character) {
        boolean charOK = false;
        for (int i = 0; i < character.length(); i++) {
            if (Character.isLetter(character.charAt(i))) {
                charOK = true;
            } else {
                charOK = false;
                return charOK;
            }
        }
        return charOK;
    }

    public static boolean checkEmail(String email) throws CheckEmailException {
        boolean emailOK = false;
        String domains[] = {"gmail", "hotmail", "outlook", "monlau", "spacemail"};
        String type[] = {".org", ".cat", ".es", ".com", ".net"};

        if (email.contains("@")) {
            int atPosition = email.indexOf("@");
            int dotPosition = email.indexOf(".");
            String domain = email.substring(atPosition + 1, dotPosition);
            for (int i = 0; i < 5; i++) {
                if (domain.equals(domains[i])) {
                    for (int j = 0; j < 5; j++) {
                        if (email.endsWith(type[j])) {
                            emailOK = true;
                        }
                    }
                }
            }

        }

        throw new CheckEmailException("Wrong Email");
    }

    public static boolean checkName(String name) {
        boolean nameOK = false;
        if (name.length() > 30) {
            nameOK = false;
            return nameOK;
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isLetter(name.charAt(i))) {
                nameOK = true;
            } else {
                nameOK = false;
                return nameOK;
            }
        }
        return nameOK;
    }
}
