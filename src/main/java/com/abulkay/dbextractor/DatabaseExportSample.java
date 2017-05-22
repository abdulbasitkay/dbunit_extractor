package com.abulkay.dbextractor;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DatabaseExportSample {

    public static void main(String[] args) throws DataSetException {
        try {
            // database connection
            Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
            Connection jdbcConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/safsms_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "***");
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

            // partial database export
            QueryDataSet partialDataSet = new QueryDataSet(connection);
//            partialDataSet.addTable("country", "SELECT * FROM country ");
            partialDataSet.addTable("user");
            FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));
        } catch (Exception ex) {
            Logger.getLogger(DatabaseExportSample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
