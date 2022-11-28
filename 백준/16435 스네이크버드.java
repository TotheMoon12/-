import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        StringTokenizer heightST = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n = 0; n < N; ++n) {
            pq.offer(Integer.parseInt(heightST.nextToken()));
        }

        while (!pq.isEmpty()) {
            int height = pq.poll();

            if (L >= height) {
                ++L;
            } else {
                break;
            }
        }

        bw.write(Integer.toString(L));
        br.close();
        bw.close();
    }
}

