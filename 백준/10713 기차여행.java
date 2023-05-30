import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[] count = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        for (int m = 1; m < M; ++m) {
            int dest = Integer.parseInt(st.nextToken());

            if (src < dest) {
                ++count[src];
                --count[dest];
            } else {
                --count[src];
                ++count[dest];
            }

            src = dest;
        }

        long sum = 0;
        for (int n = 1; n < N; ++n) {
            count[n] += count[n - 1];

            st = new StringTokenizer(br.readLine());

            int ticket = Integer.parseInt(st.nextToken());
            int costByCard = Integer.parseInt(st.nextToken());
            int card = Integer.parseInt(st.nextToken());

            long totalCostByTicket = (long) ticket * count[n];
            long totalCostByCard = card + (long) costByCard * count[n];

            sum += Math.min(totalCostByTicket, totalCostByCard);
        }

        bw.write(String.valueOf(sum));
        br.close();
        bw.close();
    }
}
