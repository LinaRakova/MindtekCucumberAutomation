package practice;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest2 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/HR_production",
                "postgres",
                "Admin123"
        );

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
//
//        List<Map<String,Object>> tableData = new ArrayList<>();
//
//        ResultSetMetaData metaData = resultSet.getMetaData();
//
//        while (resultSet.next()){
//            Map<String, Object> rowData = new HashMap<>();
//            for(int columnIndex=1; columnIndex<=metaData.getColumnCount();columnIndex++){
//                rowData.put(metaData.getColumnName(columnIndex),resultSet.getString(metaData.getColumnName(columnIndex)));
//            }
//            tableData.add(rowData);
//        }
//        for(Map columnData : tableData){
//            System.out.println(columnData.get("first_name"));
//        }


        /**
         * Get min and max salary for Sales Manager
         */

//        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM jobs");
//
//        List<Map<String, Object>> tableData2 = new ArrayList<>();
//
//        ResultSetMetaData metaData = resultSet2.getMetaData();
//
//        while (resultSet2.next()){
//            Map<String,Object> rowData = new HashMap<>();
//            for(int i=1; i<=metaData.getColumnCount(); i++){
//                rowData.put(metaData.getColumnName(i), resultSet2.getString(metaData.getColumnName(i)));
//            }
//            tableData2.add(rowData);
//        }
//        for(Map<String, Object> table : tableData2){
//            if(table.get("job_title").toString().equalsIgnoreCase("sales manager")){
//                System.out.println(table.get("job_title").toString() +
//                        ": min salary: "+
//                        table.get("min_salary")+
//                        ", max salary: "+
//                        table.get("max_salary"));
//                System.out.println("--------------------");
//
//            }
//        }

        System.out.println(getMinMaxSalary("SELECT * FROM jobs", "sales manager"));
    }

    public static String getMinMaxSalary(String query, String jobTitle) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/HR_production",
                "postgres",
                "Admin123"
        );

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM jobs");

        List<Map<String, Object>> tableData2 = new ArrayList<>();

        ResultSetMetaData metaData = resultSet2.getMetaData();

        while (resultSet2.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowData.put(metaData.getColumnName(i), resultSet2.getString(metaData.getColumnName(i)));
            }
            tableData2.add(rowData);
        }
        String output = "";
        for (Map<String, Object> table : tableData2) {
            if (table.get("job_title").toString().equalsIgnoreCase(jobTitle)) {
                output = table.get("job_title").toString() +
                        ": min salary: " +
                        table.get("min_salary") +
                        ", max salary: " +
                        table.get("max_salary");
                break;
            }
        }
        connection.close();
        statement.close();
        resultSet2.close();
        return output;
    }
}
