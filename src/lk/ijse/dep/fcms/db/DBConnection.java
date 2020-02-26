package lk.ijse.dep.fcms.db;

import javafx.scene.control.Alert;
import lk.ijse.crypto.DEPCrypt;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConnection {

    public static String host;
    public static String port;
    public static String db;
    public static String username;
    public static String password;
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties properties = new Properties();
            File file = new File("resources/application.properties");
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();

            String ip = properties.getProperty("pos.ip");
            DBConnection.host = ip;
            String port = properties.getProperty("pos.port");
            DBConnection.port = port;
            String db = properties.getProperty("pos.db");
            DBConnection.db = db;
            String user = DEPCrypt.decode(properties.getProperty("pos.user"), "Locked");
            DBConnection.username = user;
            String password = DEPCrypt.decode(properties.getProperty("pos.password"), "Locked");
            DBConnection.password = password;

            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?createDatabaseIfNotExist=true&allowMultiQueries=true", user, password);
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()) {
                File dbScriptFile = new File("FCMS.sql");
                if (!dbScriptFile.exists()) {
                    new Alert(Alert.AlertType.ERROR, "Please Contact the Developer");
                    throw new RuntimeException("DB Script Not Found");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return (dbConnection == null) ? (dbConnection = new DBConnection()) : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

}
