package zaliczenie.javafx.desktopapp.models;

import java.io.*;
import java.util.ArrayList;

public class Student implements Serializable {
    private String name;
    private String surname;
    private String studentNumber;
    private double averageGrade;

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
        this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void setSurname(String surname) {
        this.surname = surname.substring(0,1).toUpperCase() + surname.substring(1).toLowerCase();
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public static boolean saveFile(ArrayList<Student> studentsList) {
        boolean saved = false;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
            oos.writeObject(studentsList);
            oos.close();
            saved = true;
        } catch (IOException e) {
            System.out.println("save file error");
        }
        return saved;
    }

    public static ArrayList<Student> readFile() {
        ArrayList<Student> newStudentsList = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            newStudentsList = (ArrayList<Student>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            System.out.println("read file error");
        }
        return newStudentsList;
    }
}
