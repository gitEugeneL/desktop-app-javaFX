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

    public double getAverageGrade() {
        return averageGrade;
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

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
