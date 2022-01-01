package zaliczenie.javafx.desktopapp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import zaliczenie.javafx.desktopapp.models.Student;
import java.io.*;
import java.util.ArrayList;


public class PrimaryController {
    @FXML
    private ListView list;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField studentNumber;
    @FXML
    private TextField averageGrade;
    @FXML
    private HBox buttonGroup;

    private final String ADD_BUTTON = "Dodaj nowy obiekt";
    private final String EDIT_BUTTON = "Zapisz";
    private final String EXIT_BUTTON = "Anuluj";
    private final String DELETE_BUTON = "Usuń";

    private ArrayList<Student> studentsList = new ArrayList<>();
    private int indexEditingElement;


    @FXML
    private void initialize() {
        this.readFiles();
        this.createViewButton(ADD_BUTTON);
        this.updateListViewState();
    }


    private void updateListViewState() {
        ArrayList<String> studentsStrings = new ArrayList<>();
        String separator = " | ";

        for (int i = 0; i < studentsList.size(); i++) {
            studentsStrings.add(i, studentsList.get(i).getName() + " " + studentsList.get(i).getSurname()
                    + separator + studentsList.get(i).getStudentNumber()
                    + separator + studentsList.get(i).getAverageGrade());
        }
        this.list.setItems(FXCollections.observableArrayList(studentsStrings));
    }


    @FXML
    private void addElement(ActionEvent event) {
        Student student = new Student();

        student.setName(this.name.getText());
        student.setSurname(this.surname.getText());
        student.setStudentNumber(this.studentNumber.getText());
        student.setAverageGrade(this.averageGrade.getText());

        this.clearInputForm();
        studentsList.add(student);
        this.updateListViewState();
    }


    @FXML
    private void editForm(MouseEvent event) {
        int index = list.getSelectionModel().getSelectedIndex();
        if (index == -1) return;

        this.name.setText(this.studentsList.get(index).getName());
        this.surname.setText(this.studentsList.get(index).getSurname());
        this.studentNumber.setText(this.studentsList.get(index).getStudentNumber());
        this.averageGrade.setText(this.studentsList.get(index).getAverageGrade());

        this.indexEditingElement = index;

        // buttons
        this.deleteViewButtons();
        this.createViewButton(EDIT_BUTTON);
        this.createViewButton(DELETE_BUTON);
        this.createViewButton(EXIT_BUTTON);
    }


    @FXML
    private void editElement(ActionEvent event) {
        Student student = this.studentsList.get(indexEditingElement);

        student.setName(this.name.getText());
        student.setSurname(this.surname.getText());
        student.setStudentNumber(this.studentNumber.getText());
        student.setAverageGrade(this.averageGrade.getText());

        this.updateListViewState();
        this.exitEditing(event);
    }


    @FXML
    private void deleteElement(ActionEvent event) {
        this.studentsList.remove(indexEditingElement);
        this.updateListViewState();
        exitEditing(event);
    }


    @FXML
    private void exitEditing(ActionEvent event) {
        list.getSelectionModel().clearSelection(indexEditingElement);
        this.clearInputForm();

        // buttons
        this.deleteViewButtons();
        this.createViewButton(ADD_BUTTON);
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
        this.buttonGroup.getChildren().add(button);
    }


    private void deleteViewButtons() {
        this.buttonGroup.getChildren().clear();
    }


    private void clearInputForm() {
        this.name.setText("");
        this.surname.setText("");
        this.studentNumber.setText("");
        this.averageGrade.setText("");
    }


    @FXML
    private void saveFile(ActionEvent event) {
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


    @FXML
    private void clickReadButton(ActionEvent event) {
        this.readFiles();
    }


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
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}