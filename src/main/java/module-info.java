module com.rodasfiti {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.rodasfiti to javafx.fxml;
    opens com.rodasfiti.controllers to javafx.fxml;
    exports com.rodasfiti;
}
