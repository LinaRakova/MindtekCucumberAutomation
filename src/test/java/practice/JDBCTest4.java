package practice;

import utilities.JDBCutils;
import java.util.Map;
import java.util.List;

public class JDBCTest4 {
    public static void main(String[] args){

        /** write a code which will return info from DB in format: "Monday we have Math class with Sara Connors"
         * where: Monday -> day_of_week, Math -> subject_name, "Sara Connors" -> teacher_name
         */

        JDBCutils.establishConnection("Gymnasium_number_2");

        List<Map<String, Object>> dataTable = JDBCutils.runQuery("SELECT * FROM subjects " +
                "INNER JOIN teacher ON subjects.teacher_id=teacher.teacher_id " +
                "INNER JOIN week_days ON subjects.day_id=week_days.day_id");

        for(Map<String, Object> map : dataTable){
            System.out.println();
        }
    }
}
