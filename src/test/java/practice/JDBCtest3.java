package practice;

import utilities.JDBCutils;
import java.util.List;
import java.util.Map;

public class JDBCtest3 {
/*    public static void main(String[] args) {

        JDBCutils.establishConnection("HR_production");
        List<Map<String, Object>> tableData = JDBCutils.runQuery("SELECT * FROM employees ORDER BY job_id DESC");

        for (Map<String, Object> data : tableData) {
            System.out.println(data.get("job_id"));
        }

        JDBCutils.closeDataBase();

        // Bellow query will not be executed, because DataBase already closed
        List<Map<String, Object>> tableData2 = JDBCutils.runQuery("SELECT * FROM employees ORDER BY job_id DESC");

        for (Map<String, Object> data : tableData2) {
            System.out.println(data.get("job_id"));
        }

    }*/

    public static void main(String[] args) {

        JDBCutils.establishConnection("HR_production");
        List<Map<String, Object>> tableData = JDBCutils.runQuery("SELECT * FROM employees ORDER BY job_id DESC");

        for (Map<String, Object> data : tableData) {
            System.out.println(data.get("job_id"));
        }

        JDBCutils.closeDataBase();

        // Bellow query will not be executed, because DataBase already closed
        List<Map<String, Object>> tableData2 = JDBCutils.runQuery("SELECT * FROM employees ORDER BY job_id DESC");

        for (Map<String, Object> data : tableData2) {
            System.out.println(data.get("job_id"));
        }

    }
}
