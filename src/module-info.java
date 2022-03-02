module rummikubG07 {
	exports persistentie;
	exports cui;
	exports gui;
	exports main;
	exports domein;
	exports testen;
	exports exceptions;
	exports taal;

    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
	requires org.junit.jupiter.params;
	requires java.desktop;

    opens main to javafx.graphics,javafx.fxml;
    opens gui to javafx.graphics,javafx.fxml;
}