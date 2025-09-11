package com.alphag947.v2.file;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.enumeration.EntertainmentField;
import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class DataBaseHandler {

    private final Logger LOGGER = LogManager.getLogger(DataBaseHandler.class);
    private final String absolutePath;
    private Connection connection;

    public DataBaseHandler() throws SQLException {
        absolutePath = getAbsolutePath();
        connectDB();
        createTable("commonData");
    }

    public void connectDB() {

        String url = "jdbc:sqlite:%s/database/entertainment_tracker.db".formatted(absolutePath);
        LOGGER.info("DataBase URL: " + url);

        try {
            Connection connection = DriverManager.getConnection(url);
            LOGGER.info("Connection is established.");
            if (connection != null) {
                LOGGER.info("New database created.");
                this.connection = connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: convert to private
    private String getAbsolutePath() {
        String localAppData = System.getenv("LOCALAPPDATA").replace("\\", "/");
        LOGGER.info("Local: " + localAppData);
        String appData = localAppData + "/AlphaGeNStudios/EntertainmentTracker";
        LOGGER.info("Project Base URL: " + appData);
        return appData;
        // userHome + "\\AppData\\Local" or use the LOCALAPPDATA env variable
    }

    public void createTable(String tableName) throws SQLException {

        LOGGER.info("Creating table: " + tableName);

        // Creates a table with the tableName.
        String tableGenerator = """
                CREATE TABLE IF NOT EXISTS %s (
                   entertainment_id INTEGER PRIMARY KEY,
                   entertainment_type VARCHAR(7) NOT NULL,
                   entertainment_franchise VARCHAR(255) NOT NULL,
                   entertainment_title VARCHAR(255),
                   entertainment_date DATE NOT NULL
                )
                """.formatted(tableName);

        Statement statement = this.connection.createStatement();
        statement.execute(tableGenerator);
        LOGGER.info("Created table: " + tableName);
    }

    public void eraseTableData(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate("DELETE FROM " + tableName);
        LOGGER.info("rows deleted: " + rows);
    }

    public void deleteTable(String tableName) throws SQLException {

        LOGGER.info("Deleting table: " + tableName);

        // Deletes a table with the tableName.
        String tableDestroyer = "DROP TABLE " + tableName;

        Statement statement = this.connection.createStatement();
        statement.execute(tableDestroyer);
        LOGGER.info("Deleted table: " + tableName);
    }

    public void addEntertainment(Entertainment entertainment) throws SQLException {
        String insertData = """
                INSERT INTO common_data(
                   entertainment_type,
                   entertainment_franchise,
                   entertainment_title,
                   entertainment_date
                ) VALUES(?,?,?,?)
                """;

        PreparedStatement iDPS = this.connection.prepareStatement(insertData);
        iDPS.setString(1, entertainment.getType().toString());
        iDPS.setString(2, entertainment.getFranchise());
        iDPS.setString(3, entertainment.getTitle());
        iDPS.setDate(4, Date.valueOf(entertainment.getDate()));
        iDPS.executeUpdate();
    }

    public void editEntertainment(int entertainment_id, EntertainmentField entertainmentField, String value) throws SQLException {

        switch (entertainmentField) {
            case FRANCHISE:
                String updateFranchise = """
                        UPDATE common_data
                        SET entertainment_franchise = ?
                        WHERE entertainment_id = ?
                        """;
                PreparedStatement fPS = connection.prepareStatement(updateFranchise);
                fPS.setString(1, value);
                fPS.setInt(2, entertainment_id);
                fPS.executeUpdate();
                break;

            case TITLE:
                System.out.println("t");
                break;
            case DATE:
                System.out.println("d");
                break;
            case SEASON_ID:
                System.out.println("sid");
                break;
            case SEASON_NUM:
                System.out.println("sn");
                break;
            case DURATION:
                System.out.println("du");
                break;
            case EPISODE_NUM:
                System.out.println("epn");
                break;

            default:
                LOGGER.error(new Exception("EntertainmentField does not exist: " + entertainmentField));
        }
    }

    public Entertainment getEntertainment(int entertainment_id) throws SQLException {

        String getData = """
                SELECT * FROM common_data
                WHERE entertainment_id = ?
                """;

        PreparedStatement gDFS = connection.prepareStatement(getData);
        gDFS.setInt(1, entertainment_id);

        ResultSet rs = gDFS.executeQuery();

        /*
         * for multiple results, do:
         *       while (rs.next())
         *           get data.
         *           print data
         */

        LOGGER.info("ID: " + rs.getInt("entertainment_id"));
        LOGGER.info("Franchise: " + rs.getString("entertainment_franchise"));
        LOGGER.info("Title: " + rs.getString("entertainment_title"));
        LOGGER.info("Release Date: " + rs.getDate("entertainment_date"));

        return new Entertainment(
                rs.getInt("entertainment_id"),
                EntertainmentType.valueOf(rs.getString("entertainment_type")),
                rs.getString("entertainment_franchise"),
                rs.getString("entertainment_title"),
                new String[0],
                new String[0],
                rs.getDate("entertainment_date").toLocalDate()
        );
    }

    public void deleteEntertainment(int entertainment_id) throws SQLException {

        String deleteData = "DELETE FROM common_data WHERE entertainment_id = ?";
        PreparedStatement dDPS = connection.prepareStatement(deleteData);
        dDPS.setInt(1, entertainment_id);
        dDPS.executeUpdate();
    }
}

// Test Code for DataBase

// DataBase Test Code
//        try {
//            DataBaseHandler dataBaseHandler = new DataBaseHandler();
//
// Connect to database
//            dataBaseHandler.connectDB();
//
// delete table
//            dataBaseHandler.deleteTable("common_data");
//
// TODO: erase all table data
//            dataBaseHandler.eraseTableData("common_data");
//
//            Thread.sleep(7500);
//
// create table
//            dataBaseHandler.createTable("common_data");
//
// Create Data
//            dataBaseHandler.addEntertainment(
//                    new Entertainment(
//                            1,
//                            EntertainmentType.MOVIE,
//                            "Miraculous World**",
//                            "Paris: Tales of Shadybug and Claw Noir",
//                            new String[]{"1", "4"},
//                            new String[]{"miraculous", "alternate reality"},
//                            LocalDate.of(2024, 12, 15)
//                    )
//            );
//            dataBaseHandler.addEntertainment(
//                    new Entertainment(
//                            2,
//                            EntertainmentType.TVSHOW,
//                            "Miraculous",
//                            "Tales of Ladybug and Cat Noir", // season 5
//                            new String[]{"1"},
//                            new String[]{"miraculous"},
//                            LocalDate.of(2024, 12, 15)
//                    )
//            );
//            dataBaseHandler.addEntertainment(
//                    new Entertainment(
//                            3,
//                            EntertainmentType.MOVIE,
//                            "Miraculous World",
//                            "Tokyo - Stellar Force",
//                            new String[]{"3", "4", "5"},
//                            new String[]{"miraculous world", "stellar force"},
//                            LocalDate.of(2024, 12, 15)
//                    )
//            );
//            dataBaseHandler.addEntertainment(
//                    new Entertainment(
//                            4,
//                            EntertainmentType.TVSHOW,
//                            "Miraculous",
//                            "Stellar Force",
//                            new String[]{"3"},
//                            new String[]{"miraculous", "stellar force"},
//                            LocalDate.of(2024, 12, 15)
//                    )
//            );
//
//            Thread.sleep(7500);
//
// Edit Data
//            dataBaseHandler.editEntertainment(4, EntertainmentField.FRANCHISE, "Miraculous**");
//
// Get Data
//            LOGGER.info(dataBaseHandler.getEntertainment(4));
//
//
// Delete Data
//            dataBaseHandler.deleteEntertainment(1);
//
//        } catch (SQLException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }