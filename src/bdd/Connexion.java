package bdd;

import classes.Wine;
import java.sql.*;

public class Connexion {

    public static Connection connexionBDD() {
        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://10.0.29.51:5432/cavevins", "postgres",
                    "Rammstein1998");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            return connection;
        } else {
            System.out.println("Failed to make connection!");
        }
        return null;
    }


    private static Connection con;
    public Connexion(){
        con = connexionBDD();
    }

    public static void insertWine(Wine vin) throws SQLException {
        executeStatement(vin.insertQuery());
    }

    public static ResultSet executeStatement(String sql) throws SQLException {
        System.out.println(sql);
        PreparedStatement pst = con.prepareStatement(sql);
        System.out.println(pst);
        return pst.executeQuery();
    }

}
