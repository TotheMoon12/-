import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        while (n-- > 0) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        while (m-- > 0) {
            long number1 = pq.poll();
            long number2 = pq.poll();

            long newNumber = number1 + number2;
            pq.offer(newNumber);
            pq.offer(newNumber);
        }

        long answer = 0L;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.print(answer);
    }
}
