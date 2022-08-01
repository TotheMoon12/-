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
        while (pq.size() >= 2) {
            int A = pq.poll();
            int B = pq.poll();

            int compareCount = A + B;
            pq.offer(compareCount);
            answer += compareCount;
        }

        System.out.print(answer);
    }
}
