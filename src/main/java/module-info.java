module com.alphagnfss.etr3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.apache.logging.log4j;
	requires static lombok;
	requires jdk.compiler;

	exports com.alphagnfss.etr3;
	exports com.alphagnfss.etr3.ui.v1;


	opens com.alphagnfss.etr3 to javafx.fxml;
	opens com.alphagnfss.etr3.ui.v1 to javafx.fxml;
	opens com.alphagnfss.etr3.ui.v1.controllers to javafx.fxml;
}