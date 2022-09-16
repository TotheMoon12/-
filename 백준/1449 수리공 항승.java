import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> locationPQ = new PriorityQueue<>();
        for (int n = 0; n < N; ++n) {
            locationPQ.offer(Integer.parseInt(st.nextToken()));
        }

        final int BOUNDARY = L - 1;

        int answer = 1;
        int start = locationPQ.poll();
        while (!locationPQ.isEmpty()) {
            int location = locationPQ.poll();

            if (location - start > BOUNDARY) {
                ++answer;
                start = location;
            }
        }

        System.out.print(answer);
    }
}
