package zaliczenie.javafx.desktopapp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataValidation {

    public static boolean isName(String str) {
        int strLength = str.length();
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(str);

        return strLength > 3 && strLength < 10 && matcher.matches();
    }

    public static boolean isSurname(String str) {
        int strLength = str.length();
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(str);

        return strLength > 3 && strLength < 20 && matcher.matches();
    }

    public static boolean isStudentNumber(String str) {
        int strLength = str.length();
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(str);

        return strLength > 4 && strLength < 10 && matcher.matches();

    }

    public static boolean isAverageGrade(String str) {
       try {
           double grade = Double.parseDouble(str);
           return grade > 0 && grade <= 6;
       } catch (NumberFormatException e) {
           return false;
       }
    }
}
