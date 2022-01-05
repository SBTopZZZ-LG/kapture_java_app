import Models.CallLog;
import Models.HourRange;
import Scripts.Connection;
import Scripts.Functions;

import java.util.List;

public class main {
    public static void main(String... args) {
        System.out.print("Loading database... ");
        List<CallLog> logs = Connection.getCallLogs();
        if (logs == null) {
            System.out.println("(Fail!)");
            return;
        }
        System.out.println("(OK! Record count = " + logs.size() + ")\n");

        HourRange range1 = Functions.getHourOfDayWhenCallVolHigh(logs);
        System.out.println("Hour of Day when call volume is high: " + range1.getStart() + " -> " + range1.getEnd());

        HourRange range2 = Functions.getHourOfDayWhenCallLong(logs);
        System.out.println("Hour of Day when calls are long: " + range2.getStart() + " -> " + range2.getEnd());

        System.out.println("Day of Week when call volume is high: " + Functions.getDayOfWeekWhenCallVolHigh(logs));

        System.out.println("Day of Week when calls are long: " + Functions.getDayOfWeekWhenCallLong(logs));
    }
}
