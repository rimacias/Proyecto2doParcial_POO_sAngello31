module com.mycompany.proyecto2doparcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyecto2doparcial to javafx.fxml;
    opens clases to javafx.fxml;
    opens clases.personas to javafx.fxml;
    exports com.mycompany.proyecto2doparcial;
    exports clases;
    exports clases.personas;

}
