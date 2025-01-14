module com.example.boothalgocalc {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.boothalgocalc to javafx.fxml;
    exports com.example.boothalgocalc;
}