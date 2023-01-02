import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int S = Integer.parseInt(st.nextToken());

        int maxHeight = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            final int H = Integer.parseInt(st.nextToken());
            final int C = Integer.parseInt(st.nextToken());

            maxHeight = Math.max(maxHeight, H);
            if (hashMap.containsKey(H)) {
                hashMap.put(H, Math.max(hashMap.get(H), C));
            } else {
                hashMap.put(H, C);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int height : hashMap.keySet()) {
            pq.offer(height);
        }

        int[] dp = new int[maxHeight + 1];
        int firstHeight = pq.poll();
        dp[firstHeight] = hashMap.get(firstHeight);

        int prevHeight = firstHeight;
        while (!pq.isEmpty()) {
            int currentHeight = pq.poll();

            for (int height = prevHeight + 1; height < currentHeight; ++height) {
                dp[height] = dp[prevHeight];
            }

            dp[currentHeight] = Math.max(hashMap.get(currentHeight) + dp[currentHeight - S], dp[prevHeight]);
            prevHeight = currentHeight;
        }

        bw.write(String.valueOf(dp[maxHeight]));
        br.close();
        bw.close();
    }
}
