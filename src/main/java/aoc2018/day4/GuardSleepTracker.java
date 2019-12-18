package aoc2018.day4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardSleepTracker {

    private static final String recordRegex = "\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})\\] ([\\w# ]+)";
    private static final String guardIdRegex = "Guard #([\\d]+)";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Map<Integer, Map<Integer, Integer>> processRecords(String[] records) {

        List<SleepRecord> sleepRecords = parseAndSortRecords(records);

        Map<Integer, Map<Integer, Integer>> guards = new HashMap<>();
        int currentGuardId = 0;

        for (int i = 0; i < sleepRecords.size(); i++) {
            SleepRecord record = sleepRecords.get(i);

            if (record.action.contains("Guard")) {
                currentGuardId = getGuardID(record.action);
            } else if (record.action.contains("falls asleep")) {

                SleepRecord wakeupRecord = sleepRecords.get(i + 1);
                Map<Integer, Integer> sleepMap = guards.getOrDefault(currentGuardId, new HashMap<Integer, Integer>());

                for (int j = record.timestamp.getMinute(); j < wakeupRecord.timestamp.getMinute(); j++) {
                    sleepMap.put(j, sleepMap.getOrDefault(j, 0) + 1);
                }

                guards.put(currentGuardId, sleepMap);
            }
        }

        return guards;
    }

    private int getGuardID(String action) {

        Pattern guardIdPattern = Pattern.compile(guardIdRegex);
        Matcher m = guardIdPattern.matcher(action);

        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    private List<SleepRecord> parseAndSortRecords(String[] records) {
        List<SleepRecord> sleepRecords = new ArrayList<>();
        Pattern recordPattern = Pattern.compile(recordRegex);

        for (String recordString : records) {

            Matcher m = recordPattern.matcher(recordString);

            if (m.find()) {
                SleepRecord record = new SleepRecord();
                record.timestamp = LocalDateTime.parse(m.group(1), formatter);
                record.action = m.group(2);
                sleepRecords.add(record);
            }
        }

        Collections.sort(sleepRecords);
        return sleepRecords;
    }

    public class SleepRecord implements Comparable<SleepRecord> {
        LocalDateTime timestamp;
        String action;

        @Override
        public int compareTo(SleepRecord o) {
            return timestamp.compareTo(o.timestamp);
        }
    }
}
