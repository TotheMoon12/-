import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            final int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] prices = new int[N];
            for (int n = 0; n < N; ++n) {
                prices[n] = Integer.parseInt(st.nextToken());
            }

            long answer = 0L;
            for (int n = N - 1; n >= 0; --n) {
                int count = 0;
                long sum = 0L;
                for (int prev = n - 1; prev >= 0; --prev) {
                    if (prices[prev] > prices[n]) {
                        break;
                    }

                    sum += prices[prev];
                    ++count;
                }

                answer += count * (long)prices[n] - sum;
                n -= count;
            }

            builder.append(answer);
            builder.append(System.lineSeparator());
        }

        System.out.print(builder.toString());
        br.close();
    }
}
