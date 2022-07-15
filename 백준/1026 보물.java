import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> A = new PriorityQueue<>();
        PriorityQueue<Integer> B = new PriorityQueue<>(Collections.reverseOrder());

        StringTokenizer stA = new StringTokenizer(br.readLine());
        StringTokenizer stB = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; ++n) {
            A.offer(Integer.parseInt(stA.nextToken()));
            B.offer(Integer.parseInt(stB.nextToken()));
        }

        int answer = 0;
        while (!A.isEmpty()) {
            answer += A.poll() * B.poll();
        }

        System.out.println(answer);
    }
}
