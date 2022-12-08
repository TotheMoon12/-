import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n = 0; n < N; ++n) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        long answer = 0;
        int order = 0;
        while (!pq.isEmpty()) {
            int tip = pq.poll();
            tip = Math.max(0, tip - order++);
            answer += tip;

            if (tip <= 0) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
