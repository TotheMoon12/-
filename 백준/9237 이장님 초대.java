import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n = 0; n < N; ++n) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        int day = 1;
        int answer = 0;
        while (!pq.isEmpty()) {
            int time = pq.poll();
            answer = Math.max(answer, day + time + 1);
            ++day;
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}
