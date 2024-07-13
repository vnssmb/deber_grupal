package application;
	
import java.util.List;

import Models.ConstructorResult;
import Models.DriverResult;
import Models.Season;
import Repositories.ConstructorRepository;
import Repositories.DriverResultRepository;
import Repositories.SeasonRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("     C  O  N  S  U  L  T  A      D  E      D  A  T  O  S");

        // Crear botón para conductores
        Button driverButton = new Button("D A T O S     C O N D U C T O R E S");
        driverButton.getStyleClass().add("button-driver"); // Aplicar clase CSS para conductores

        // Crear botón para constructores
        Button constructorButton = new Button("D A T O S     C O N S T R U C T O R E S");
        constructorButton.getStyleClass().add("button-constructor"); // Aplicar clase CSS para constructores

        
        // Configurar acciones de los botones
        driverButton.setOnAction(e -> {
            DriverResultsWindow driverResultsWindow = new DriverResultsWindow();
            driverResultsWindow.show();
        });

        constructorButton.setOnAction(e -> {
            ConstructorResultsWindow constructorResultsWindow = new ConstructorResultsWindow();
            constructorResultsWindow.show();
        });

        // Colocar los botones en un contenedor VBox centrado
        VBox vbox = new VBox(10); // Espacio entre los elementos
        vbox.setAlignment(Pos.CENTER); // Centrar los elementos
        vbox.getChildren().addAll(driverButton, constructorButton);

        // Configurar la escena con un BorderPane para centrar los elementos
        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        // Establecer color de fondo naranja para la escena
        Scene scene = new Scene(root, 500, 100);
        scene.setFill(Color.ORANGE); // Color de fondo naranja
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Cargar archivo CSS
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	
	public class DriverResultsWindow extends Stage {

	    private TableView<DriverResult> table;
	    private ComboBox<Season> yearComboBox;
	    private DriverResultRepository driverResultRepository;
	    private SeasonRepository seasonRepository;

	    public DriverResultsWindow() {
	        setTitle("Resultados Conductores");

	        driverResultRepository = new DriverResultRepository();
	        seasonRepository = new SeasonRepository();

	        // Crear columnas
	        TableColumn<DriverResult, String> driverNameColumn = new TableColumn<>("DriverName");
	        driverNameColumn.setMinWidth(200);
	        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));

	        TableColumn<DriverResult, Integer> winsColumn = new TableColumn<>("Wins");
	        winsColumn.setMinWidth(100);
	        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

	        TableColumn<DriverResult, Integer> totalPointsColumn = new TableColumn<>("TotalPoints");
	        totalPointsColumn.setMinWidth(100);
	        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

	        TableColumn<DriverResult, Integer> rankColumn = new TableColumn<>("Rank");
	        rankColumn.setMinWidth(100);
	        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

	        // Crear TableView
	        table = new TableView<>();
	        table.getColumns().addAll(driverNameColumn, winsColumn, totalPointsColumn, rankColumn);

	        // Crear ComboBox
	        yearComboBox = new ComboBox<>();
	        List<Season> seasons = seasonRepository.getSeasons();
	        ObservableList<Season> observableSeasons = FXCollections.observableArrayList(seasons);
	        yearComboBox.setItems(observableSeasons);
	        yearComboBox.setConverter(new StringConverter<Season>() {
	            @Override
	            public String toString(Season season) {
	                return season != null ? String.valueOf(season.getYear()) : "";
	            }

	            @Override
	            public Season fromString(String string) {
	                return null;
	            }
	        });
	        yearComboBox.setOnAction(e -> {
	            if (yearComboBox.getValue() != null) {
	                updateTable(yearComboBox.getValue().getYear());
	            }
	        });

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(yearComboBox, table);

	        Scene scene = new Scene(vbox);
	        setScene(scene);
	    }

	    private void updateTable(int year) {
	        List<DriverResult> results = driverResultRepository.getResultByYear(year);
	        ObservableList<DriverResult> observableResults = FXCollections.observableArrayList(results);
	        table.setItems(observableResults);
	    }
	}
	
    
	public class ConstructorResultsWindow extends Stage {

	    private TableView<ConstructorResult> table;
	    private ComboBox<Season> yearComboBox;
	    private ConstructorRepository constructorRepository;
	    private SeasonRepository seasonRepository;

	    public ConstructorResultsWindow() {
	        setTitle("Resultados Constructores");

	        constructorRepository = new ConstructorRepository();
	        seasonRepository = new SeasonRepository();

	        // Crear columnas
	        TableColumn<ConstructorResult, String> constructorNameColumn = new TableColumn<>("ConstructorName");
	        constructorNameColumn.setMinWidth(200);
	        constructorNameColumn.setCellValueFactory(new PropertyValueFactory<>("constructorName"));

	        TableColumn<ConstructorResult, Integer> winsColumn = new TableColumn<>("Wins");
	        winsColumn.setMinWidth(100);
	        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

	        TableColumn<ConstructorResult, Integer> totalPointsColumn = new TableColumn<>("TotalPoints");
	        totalPointsColumn.setMinWidth(100);
	        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

	        TableColumn<ConstructorResult, Integer> rankColumn = new TableColumn<>("Rank");
	        rankColumn.setMinWidth(100);
	        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

	        // Crear TableView
	        table = new TableView<>();
	        table.getColumns().addAll(constructorNameColumn, winsColumn, totalPointsColumn, rankColumn);

	        // Crear ComboBox
	        yearComboBox = new ComboBox<>();
	        List<Season> seasons = seasonRepository.getSeasons();
	        ObservableList<Season> observableSeasons = FXCollections.observableArrayList(seasons);
	        yearComboBox.setItems(observableSeasons);
	        yearComboBox.setConverter(new StringConverter<Season>() {
	            @Override
	            public String toString(Season season) {
	                return season != null ? String.valueOf(season.getYear()) : "";
	            }

	            @Override
	            public Season fromString(String string) {
	                return null;
	            }
	        });
	        yearComboBox.setOnAction(e -> {
	            if (yearComboBox.getValue() != null) {
	                updateTable(yearComboBox.getValue().getYear());
	            }
	        });

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(yearComboBox, table);

	        Scene scene = new Scene(vbox);
	        setScene(scene);
	    }

	    private void updateTable(int year) {
	        List<ConstructorResult> results = constructorRepository.getResultByYear(year);
	        ObservableList<ConstructorResult> observableResults = FXCollections.observableArrayList(results);
	        table.setItems(observableResults);
	    }
	}
   
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
