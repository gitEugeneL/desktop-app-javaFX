package zaliczenie.javafx.desktopapp.models;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String surname;
    private double averageGrade;
    private String studentNumber;

    public Student(){};

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAverageGrade() {
        return Double.toString(this.averageGrade);
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = Double.parseDouble(averageGrade);
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
