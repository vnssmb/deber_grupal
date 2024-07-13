module PELACHO_GRUPAL {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.base;

    opens Models to javafx.base; // Abrir el paquete Models para permitir acceso reflexivo a las clases
    opens application to javafx.graphics, javafx.fxml;
}
