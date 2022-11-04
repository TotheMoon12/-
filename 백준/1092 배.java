import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cranes = new int[N];
        for (int n = 0; n < N; ++n) {
            cranes[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cranes);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int m = 0; m < M; ++m) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        int[] count = new int[N];
        if (pq.peek() > cranes[N - 1]) {
            System.out.print(-1);
            return;
        } 
        
        while (!pq.isEmpty()) {
            int box = pq.poll();

            int minCount = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int idx = N - 1; idx >= 0; --idx) {
                if (cranes[idx] < box) {
                    break;
                }

                if (count[idx] < minCount) {
                    minCount = count[idx];
                    minIndex = idx;
                }
            }

            ++count[minIndex];
            
            if (pq.isEmpty()) {
                break;
            }
        }

        int answer = 0;
        for (int n = 0; n < N; ++n) {
            answer = Math.max(answer, count[n]);
        }

        System.out.print(answer);
        br.close();
    }
}
