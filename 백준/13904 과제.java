import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        final int N = Integer.parseInt(br.readLine());
        final int MAX_DAY = 1000;
        int maxDDay = 0;
        PriorityQueue<Integer>[] tasksByDay = new PriorityQueue[MAX_DAY + 1];
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dDay = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            maxDDay = Math.max(maxDDay, dDay);
            if (tasksByDay[dDay] == null) {
                tasksByDay[dDay] = new PriorityQueue<>(Comparator.reverseOrder());
                tasksByDay[dDay].add(weight);
            } else {
                tasksByDay[dDay].add(weight);
            }
        }

        int day = maxDDay;
        int answer = 0;
        while (day >= 1) {
            int targetDDay = 0;
            int targetWeight = 0;
            for (int dDay = day; dDay <= maxDDay; ++dDay) {
                if (tasksByDay[dDay] != null && tasksByDay[dDay].size() > 0) {
                    int weight = tasksByDay[dDay].peek();
                    if (weight > targetWeight) {
                        targetWeight = weight;
                        targetDDay = dDay;
                    }
                }
            }

            if (targetDDay != 0) {
                tasksByDay[targetDDay].poll();
                answer += targetWeight;
            }

            --day;
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}
