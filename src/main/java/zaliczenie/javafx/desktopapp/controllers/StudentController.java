package zaliczenie.javafx.desktopapp.controllers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import zaliczenie.javafx.desktopapp.DataValidation;
import zaliczenie.javafx.desktopapp.models.Student;
import java.io.*;
import java.util.ArrayList;


public class StudentController {
    @FXML
    private ListView listView;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField studentNumberField;
    @FXML
    private TextField averageGradeField;
    @FXML
    private HBox buttonGroupField;

    private final String ADD_BUTTON = "Dodaj nowy obiekt";
    private final String EDIT_BUTTON = "Zapisz";
    private final String EXIT_BUTTON = "Anuluj";
    private final String DELETE_BUTON = "Usuń";

    private ArrayList<Student> studentsList = new ArrayList<>();
    private int indexEditingElement;


    @FXML
    private void initialize() {
        this.createViewButton(ADD_BUTTON);
        this.readFiles();
        this.updateListViewState();
    }

    private void updateListViewState() {
        ArrayList<String> studentsStrings = new ArrayList<>();
        for (Student student : this.studentsList) {
            studentsStrings.add(student.getName() + " | " + student.getSurname()
                    + " | " + student.getStudentNumber() + " | " + student.getAverageGrade());
        }
        this.listView.setItems(FXCollections.observableArrayList(studentsStrings));
    }

    @FXML
    private void addElement(ActionEvent event) {
        Student student = createStudent();
        if (student != null) {
            this.clearInputFields();
            studentsList.add(student);
            this.updateListViewState();
        }
    }

    @FXML
    private void editElement(ActionEvent event) {
        Student student = createStudent();
        if (student != null) {
            this.studentsList.set(indexEditingElement, student);
            this.updateListViewState();
            this.exitEditing(event);
        }
    }

    @FXML
    private void deleteElement(ActionEvent event) {
        this.studentsList.remove(indexEditingElement);
        this.updateListViewState();
        exitEditing(event);
    }

    private Student createStudent() {
        if (checkInputData()) {
            Student student = new Student();
            student.setName(this.nameField.getText().trim());
            student.setSurname(this.surnameField.getText().trim());
            student.setStudentNumber(this.studentNumberField.getText().trim());
            student.setAverageGrade(this.averageGradeField.getText().trim());
            return student;
        }
        return null;
    }

    private boolean checkInputData() {
        boolean nameValid = DataValidation.isName(this.nameField.getText());
        boolean surnameValid = DataValidation.isSurname(this.surnameField.getText());
        boolean studentNumberValid = DataValidation.isStudentNumber(this.studentNumberField.getText());
        boolean averageGradeValid = DataValidation.isAverageGrade(this.averageGradeField.getText());

        changeBorderColor(this.nameField, nameValid);
        changeBorderColor(this.surnameField, surnameValid);
        changeBorderColor(this.studentNumberField, studentNumberValid);
        changeBorderColor(this.averageGradeField, averageGradeValid);

        return nameValid && surnameValid && studentNumberValid && averageGradeValid;
    }

    private void changeBorderColor(TextField textField, boolean valid) {
        if (valid) {
            textField.setStyle("-fx-border-color: #1E90FF");
        } else {
            textField.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void editForm(MouseEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index == -1) return;

        this.nameField.setText(this.studentsList.get(index).getName());
        this.surnameField.setText(this.studentsList.get(index).getSurname());
        this.studentNumberField.setText(this.studentsList.get(index).getStudentNumber());
        this.averageGradeField.setText(this.studentsList.get(index).getAverageGrade());

        // [!] remove red border if there were validation errors on another element of the list
        checkInputData();

        this.indexEditingElement = index;

        this.deleteViewButtons();
        this.createViewButton(EDIT_BUTTON);
        this.createViewButton(DELETE_BUTON);
        this.createViewButton(EXIT_BUTTON);
    }

    private void clearInputFields() {
        this.nameField.setText("");
        this.surnameField.setText("");
        this.studentNumberField.setText("");
        this.averageGradeField.setText("");
    }

    @FXML
    private void exitEditing(ActionEvent event) {
        listView.getSelectionModel().clearSelection(indexEditingElement);
        this.clearInputFields();

        this.deleteViewButtons();
        this.createViewButton(ADD_BUTTON);
    }

    @FXML
    private void readFiles(ActionEvent event) {
        this.readFiles();
    }

    @FXML
    private void saveFiles(ActionEvent event) {
        this.saveFile();
    }

    private void createViewButton(String buttonName) {
        Button button = new Button();
        button.setText(buttonName);

        switch (buttonName) {
            case ADD_BUTTON:
                button.setOnAction(event -> addElement(event));
                break;
            case EDIT_BUTTON:
                button.setOnAction(event -> editElement(event));
                break;
            case DELETE_BUTON:
                button.setOnAction(event -> deleteElement(event));
                break;
            case EXIT_BUTTON:
                button.setOnAction(event -> exitEditing(event));
                break;
            default:
                return;
        }
        this.buttonGroupField.getChildren().add(button);
    }

    private void deleteViewButtons() {
        this.buttonGroupField.getChildren().clear();
    }


    //--------------------- refactor-----------------------------------------------

    private void readFiles() {
        try {
            ArrayList<Student> newStudentsList;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            newStudentsList = (ArrayList<Student>) ois.readObject();
            ois.close();

            this.studentsList.clear();
            this.studentsList.addAll(newStudentsList);
            this.updateListViewState();

        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
            System.out.println("ERRRRRROOOOOOOOORRRRRRRR");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
            oos.writeObject(this.studentsList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}