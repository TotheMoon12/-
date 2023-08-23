import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int N = Integer.parseInt(st.nextToken());
            final int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] stocks = new int[N];
            for (int n = 0; n < N; ++n) {
                stocks[n] = Integer.parseInt(st.nextToken());
            }

            int[] purchasedStocks = new int[N];
            int count = 1;
            purchasedStocks[0] = stocks[0];

            for (int n = 1; n < N; ++n) {
                int stock = stocks[n];
                int find = Arrays.binarySearch(purchasedStocks, 0, count, stock);
                if (find < 0) {
                    find = -find - 1;
                }

                purchasedStocks[find] = stock;
                if (find == count) {
                    ++count;
                }
            }

            builder.append("Case #" + t);
            builder.append(System.lineSeparator());
            if (count >= K) {
                builder.append(1);
            } else {
                builder.append(0);
            }

            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
