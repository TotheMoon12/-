import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    static final int FIRST_TIME_MINUTE = 540;

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] times = new int[timetable.length];

        StringTokenizer st;
        for (int i = 0; i < timetable.length; ++i) {
            st = new StringTokenizer(timetable[i], ":");

            int time = Integer.parseInt(st.nextToken()) * 60;
            time += Integer.parseInt(st.nextToken());
            times[i] = time;
        }

        Arrays.sort(times);
        ArrayList<Integer>[] timesBySchedule = new ArrayList[n];

        int crewIndex = 0;
        for (int i = 0; i < n; ++i) {
            timesBySchedule[i] = new ArrayList<>();

            final int busTime = FIRST_TIME_MINUTE + t * i;
            int count = 0;
            while (count < m && crewIndex < times.length && times[crewIndex] <= busTime) {
                timesBySchedule[i].add(times[crewIndex]);
                ++count;
                ++crewIndex;
            }
        }


        int crewNumber = timesBySchedule[n - 1].size();
        final int busTime = FIRST_TIME_MINUTE + t * (n - 1);

        if (crewNumber < m) {
            return convertMinuteToStringTime(busTime);
        } else {
            return convertMinuteToStringTime(timesBySchedule[n - 1].get(crewNumber - 1) - 1);
        }
    }

    public static String convertMinuteToStringTime(final int time) {
        int hour = time / 60;
        int minute = time % 60;

        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append("0");
        }

        sb.append(hour);
        sb.append(":");

        if (minute < 10) {
            sb.append("0");
        }

        sb.append(minute);
        return sb.toString();
    }
}
