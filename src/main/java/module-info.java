module com.alphagnfss.etr3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires static lombok;
    requires jdk.compiler;
    requires jdk.jfr;
    requires org.dizitart.no2;
    requires org.dizitart.no2.mvstore;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;

    exports com.alphagnfss.etr3;
}