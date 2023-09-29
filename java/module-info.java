module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}