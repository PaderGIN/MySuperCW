module prod.mysupercw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens prod.mysupercw to javafx.fxml;
    exports prod.mysupercw;
    opens Models to javafx.fxml;
    exports Models;
}