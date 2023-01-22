module assignment.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment.application to javafx.fxml;
    exports assignment.application;
}