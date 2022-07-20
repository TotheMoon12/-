import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n = 0; n < N; ++n) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        // w / n = weight,  w = weight * n;
        while (!pq.isEmpty()) {
            int weight = pq.poll();
            answer = Math.max(answer, weight * N);
            --N;
        }

        System.out.print(answer);
    }
}
