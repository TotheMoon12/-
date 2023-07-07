import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Pair implements Comparable<Pair> {
        long startTime;
        int number;

        public Pair(long startTime, int number) {
            this.startTime = startTime;
            this.number = number;
        }

        @Override
        public int compareTo(Pair o) {
            if (o.startTime == this.startTime) {
                return Long.compare(o.number, this.number);
            }

            return Long.compare(o.startTime, this.startTime);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] rides = new int[M];
        for (int m = 0; m < M; ++m) {
            rides[m] = Integer.parseInt(st.nextToken());
        }

        long minTime = Long.MAX_VALUE;
        long left = 0;
        long right = N * 30L;

        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (int m = 0; m < M; ++m) {
                count += mid / rides[m];
            }

            if (count >= N) {
                minTime = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        long total = 0L;
        for (int m = 0; m < M; ++m) {
            long count = minTime / rides[m];
            if (minTime % rides[m] != 0) {
                ++count;
            }

            total += count;
            long lastStartTime = count * rides[m] - rides[m];
            pq.offer(new Pair(lastStartTime, m));
        }

        while (total > N) {
            Pair pair = pq.poll();
            pair.startTime -= rides[pair.number];
            pq.offer(pair);
            --total;
        }

        bw.write(String.valueOf(pq.poll().number + 1));
        br.close();
        bw.close();
    }
}
