module project.com.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.com.demo1 to javafx.fxml;
    exports project.com.demo1;
}