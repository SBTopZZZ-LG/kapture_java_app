package Scripts;

import Models.CallLog;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    @Nullable
    public static List<CallLog> getCallLogs() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            java.sql.Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/kapture_db", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from callcenter_logs");

            List<CallLog> logs = new ArrayList<>();
            while (rs.next()) {
                CallLog log = new CallLog();

                log.setId(rs.getInt(1));
                log.setPhoneNumber(rs.getString(2));
                log.setStartTime(rs.getTime(3));
                log.setEndTime(rs.getTime(4));
                log.setStartDate(rs.getDate(3));
                log.setEndDate(rs.getDate(4));
                log.setDuration(rs.getInt(5));

                logs.add(log);
            }
            con.close();

            return logs;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
