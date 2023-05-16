module com.example.workshopgaragevrlab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.workshopgaragevrlab to javafx.fxml;
    exports com.example.workshopgaragevrlab;
}