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
    exports com.example.vectoreditor.model.unused;
    opens com.example.vectoreditor.model.unused to javafx.fxml;
    exports com.example.vectoreditor.controller.unused;
    opens com.example.vectoreditor.controller.unused to javafx.fxml;
    exports com.example.vectoreditor.model.drawers;
    opens com.example.vectoreditor.model.drawers to javafx.fxml;
    exports com.example.vectoreditor.controller.figureTools;
    opens com.example.vectoreditor.controller.figureTools to javafx.fxml;
    exports com.example.vectoreditor.model.figures;
    opens com.example.vectoreditor.model.figures to javafx.fxml;
}