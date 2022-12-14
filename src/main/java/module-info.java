module com.example.vectoreditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.vectoreditor to javafx.fxml;
    exports com.example.vectoreditor.model;
    opens com.example.vectoreditor.model to javafx.fxml;
    exports com.example.vectoreditor.controller;
    opens com.example.vectoreditor.controller to javafx.fxml;
}