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

        StringBuilder builder = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int N = Integer.parseInt(st.nextToken());
            final int M = (int) ((Double.parseDouble(st.nextToken())) * 100 + 0.05);

            if (N == 0 && M == 0) {
                break;
            }

            int[] dp = new int[M + 1];
            for (int n = 1; n <= N; ++n) {
                st = new StringTokenizer(br.readLine());
                int calorie = Integer.parseInt(st.nextToken());
                int price = (int) ((Double.parseDouble(st.nextToken())) * 100 + 0.05);

                for (int money = price; money <= M; ++money) {
                    dp[money] = Math.max(dp[money], dp[money - price] + calorie);
                }
            }

            builder.append(dp[M]);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
