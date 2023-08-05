package practice;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {

        /**
         *We need 3 things to connect to Data Base
         *
         * 1.Connection object
         * 2. Statement object
         * 3. ResultSet object
         */
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/HR_production",
                "postgres",
                "Admin123"
        );

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM jobs");

        resultSet.next();

        System.out.println(resultSet.getString(2));

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        /**
         * ResultMetaData --> It holds a data about Data;
         */

        ResultSetMetaData metaData = resultSet.getMetaData();

        System.out.println(metaData.getColumnCount());

        System.out.println("Table name: "+metaData.getTableName(1));

        for(int i=1; i<metaData.getColumnCount(); i++){
            System.out.println("Column name: "+metaData.getColumnName(i));
        }
    }
}
