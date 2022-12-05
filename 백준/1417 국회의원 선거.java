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

        int N = Integer.parseInt(br.readLine());
        int dasom = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n = 1; n < N; ++n) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        if (N > 1) {
            while (dasom <= pq.peek()) {
                ++dasom;
                ++answer;

                int other = pq.poll() - 1;
                pq.offer(other);
            }
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
