module com.robertminkler.csd405javafx.csd405javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.robertminkler.csd405javafx.csd405javafx to javafx.fxml;
    exports com.robertminkler.csd405javafx.csd405javafx;
}