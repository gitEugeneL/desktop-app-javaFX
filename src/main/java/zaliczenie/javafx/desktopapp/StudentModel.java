package zaliczenie.javafx.desktopapp;

public class StudentModel {
    private String name;
    private String surname;
    private double averageGrade;
    private int studentNumber;

    public StudentModel(){};

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getPesel() {
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

    public void setPesel(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}
