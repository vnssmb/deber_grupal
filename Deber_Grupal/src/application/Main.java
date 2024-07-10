package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {

    private TableView<Constructor> constructorTableView = new TableView<>();
    private ComboBox<Integer> yearComboBox = new ComboBox<>();
    private ObservableList<Constructor> constructorData = FXCollections.observableArrayList();
    private ObservableList<Integer> allYears = FXCollections.observableArrayList(
        2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013,
        2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002,
        2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991,
        1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980,
        1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969,
        1968, 1967, 1966, 1965, 1958, 1956, 1950
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Constructors TableView");

        yearComboBox.setItems(allYears);
        yearComboBox.setValue(2023);
        yearComboBox.setOnAction(e -> filtrarDatos());

        // Configurar TableView
        TableColumn<Constructor, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("constructorId"));

        TableColumn<Constructor, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Constructor, String> nationalityColumn = new TableColumn<>("Nationality");
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));

        TableColumn<Constructor, String> urlColumn = new TableColumn<>("URL");
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

        TableColumn<Constructor, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        constructorTableView.getColumns().addAll(idColumn, nameColumn, nationalityColumn, urlColumn, yearColumn);

        // Cargar datos en la tabla
        cargarDatosDesdeCSV("path/to/your/constructors.csv");
        constructorTableView.setItems(constructorData);

        // Filtrar datos inicialmente
        filtrarDatos();

        // Configurar Layout
        VBox vbox = new VBox(yearComboBox, constructorTableView);

        // Configurar la escena
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cargarDatosDesdeCSV(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String nationality = values[2];
                String url = values[3];
                int year = Integer.parseInt(values[4]);
                constructorData.add(new Constructor(id, name, nationality, url, year));
                if (!allYears.contains(year)) {
                    allYears.add(year);  // Añadir año al ComboBox si no está ya presente
                }
            }
            allYears.sort(Integer::compareTo);  // Asegurarse de que los años estén ordenados
            yearComboBox.setItems(allYears);  // Actualizar ComboBox con años ordenados
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filtrarDatos() {
        Integer selectedYear = yearComboBox.getValue();
        if (selectedYear != null) {
            ObservableList<Constructor> filteredData = FXCollections.observableArrayList();
            for (Constructor constructor : constructorData) {
                if (constructor.getYear() == selectedYear) {
                    filteredData.add(constructor);
                }
            }
            constructorTableView.setItems(filteredData);
        }
    }

    public static class Constructor {
        private int constructorId;
        private String name;
        private String nationality;
        private String url;
        private int year;

        public Constructor(int constructorId, String name, String nationality, String url, int year) {
            this.constructorId = constructorId;
            this.name = name;
            this.nationality = nationality;
            this.url = url;
            this.year = year;
        }

        public int getConstructorId() {
            return constructorId;
        }

        public void setConstructorId(int constructorId) {
            this.constructorId = constructorId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
