import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Time {
        int in;
        int out;

        public Time(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        PriorityQueue<Time> pq = new PriorityQueue<>(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.in == o2.in) {
                    return o1.out - o2.out;
                }

                return o1.in - o2.in;
            }
        });

        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(st.nextToken());
            int out = Integer.parseInt(st.nextToken());

            pq.offer(new Time(in, out));
        }

        PriorityQueue<Time> pqByOutTime = new PriorityQueue<>(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.out - o2.out;
            }
        });

        int max = 0;
        int startTimeByMax = 0;
        int endTimeByMax = 0;
        int startTime = 0;
        int endTime = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Time time = pq.poll();

            int prevCount = count;
            Time timeByLastOut = null;
            while (!pqByOutTime.isEmpty() && pqByOutTime.peek().out <= time.in) {
                timeByLastOut = pqByOutTime.peek();
                pqByOutTime.poll();
                --count;
            }

            ++count;
            pqByOutTime.offer(time);
            if (count != prevCount || (timeByLastOut != null && timeByLastOut.out < time.in)) {
                startTime = time.in;
            }
            endTime = pqByOutTime.peek().out;

            if (count > max) {
                max = count;
                startTimeByMax = startTime;
                endTimeByMax = endTime;
            } else if (count == max && startTimeByMax == startTime) {
                endTimeByMax = endTime;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(max)
                .append(System.lineSeparator())
                .append(startTimeByMax)
                .append(' ')
                .append(endTimeByMax);
        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
