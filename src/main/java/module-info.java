module com.example.tp_ {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.tp_ to javafx.fxml;
    exports com.example.tp_;
}