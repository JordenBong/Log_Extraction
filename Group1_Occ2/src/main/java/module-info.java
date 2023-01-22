module assignment.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens Javafx to javafx.fxml;
    exports Javafx;
}