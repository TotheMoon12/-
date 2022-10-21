import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            final int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int n = 0; n < N; ++n) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }

            int first = pq.poll();
            int last = pq.poll();
            int max = 0;
            while (!pq.isEmpty()) {
                int number = pq.poll();

                max = Math.max(max, first - number);
                first = number;

                if (!pq.isEmpty()) {
                    number = pq.poll();
                    max = Math.max(max, last - number);
                    last = number;
                }
            }

            max = Math.max(max, first - last);
            builder.append(max);
            builder.append(System.lineSeparator());
        }

        System.out.print(builder);
        br.close();
    }
}
