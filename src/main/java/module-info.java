module prod.mysupercw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens prod.mysupercw to javafx.fxml;
    exports prod.mysupercw;
    exports prod.mysupercw.controllers;
    opens prod.mysupercw.controllers to javafx.fxml;
}