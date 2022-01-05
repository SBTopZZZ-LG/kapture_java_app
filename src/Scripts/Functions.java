package Scripts;

import Models.CallLog;
import Models.HourRange;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Calendar;

public class Functions {
    // O(n)
    public static HourRange getHourOfDayWhenCallVolHigh(@NotNull List<CallLog> logs) {
        HashMap<Integer, Integer> record = new HashMap<>();

        int hour = -1, frequency = 0;
        for (CallLog log : logs) {
            int start = getHour(log.getStartTime()), end = getHour(log.getEndTime());

            for (int i = start; i <= end; i++) {
                if (record.containsKey(i)) {
                    record.put(i, record.get(i) + 1);
                } else {
                    record.put(i, 1);
                }

                if (record.get(i) > frequency || hour == -1) {
                    hour = i;
                    frequency = record.get(i);
                }
            }
        }

        return new HourRange(hour, (hour + (hour + 1 > 23 ? -23 : 1)));
    }
    // O(n)
    public static HourRange getHourOfDayWhenCallLong(@NotNull List<CallLog> logs) {
        HashMap<Integer, Long> record = new HashMap<>();

        int hour = -1;
        long duration = 0;
        for (CallLog log : logs) {
            int start = getHour(log.getStartTime()), end = getHour(log.getEndTime());

            for (int i = start; i <= end; i++) {
                if (record.containsKey(i)) {
                    record.put(i, record.get(i) + log.getDuration());
                } else {
                    record.put(i, (long) log.getDuration());
                }

                if (record.get(i) > duration || hour == -1) {
                    hour = i;
                    duration = record.get(i);
                }
            }
        }

        return new HourRange(hour, (hour + (hour + 1 > 23 ? -23 : 1)));
    }
    // O(n)
    public static String getDayOfWeekWhenCallVolHigh(@NotNull List<CallLog> logs) {
        HashMap<Integer, Integer> record = new HashMap<>();

        int day = -1;
        int frequency = 0;
        for (CallLog log : logs) {
            int start = getDay(log.getStartDate()), end = getDay(log.getEndDate());

            for (int i = start; i <= end; i++) {
                if (record.containsKey(i)) {
                    record.put(i, record.get(i) + 1);
                } else {
                    record.put(i, 1);
                }

                if (record.get(i) > frequency || day == -1) {
                    day = i;
                    frequency = record.get(i);
                }
            }
        }

        return new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}[day];
    }
    // O(n)
    public static String getDayOfWeekWhenCallLong(@NotNull List<CallLog> logs) {
        HashMap<Integer, Long> record = new HashMap<>();

        int day = -1;
        long duration = 0;
        for (CallLog log : logs) {
            int start = getDay(log.getStartDate()), end = getDay(log.getEndDate());

            for (int i = start; i <= end; i++) {
                if (record.containsKey(i)) {
                    record.put(i, record.get(i) + log.getDuration());
                } else {
                    record.put(i, (long) log.getDuration());
                }

                if (record.get(i) > duration || day == -1) {
                    day = i;
                    duration = record.get(i);
                }
            }
        }

        return new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}[day];
    }

    private static int getHour(Date date) {
        Calendar temp = Calendar.getInstance();
        temp.setTime(date);

        return temp.get(Calendar.HOUR_OF_DAY);
    }
    private static int getDay(Date date) {
        Calendar temp = Calendar.getInstance();
        temp.setTime(date);

        return temp.get(Calendar.DAY_OF_WEEK);
    }
}
