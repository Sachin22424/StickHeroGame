module com.example.ui_main {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.ui_main to javafx.fxml;
    exports com.example.ui_main;
}