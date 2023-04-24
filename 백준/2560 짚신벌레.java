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
        final int a = Integer.parseInt(st.nextToken());
        final int b = Integer.parseInt(st.nextToken());
        final int d = Integer.parseInt(st.nextToken());
        final int N = Integer.parseInt(st.nextToken());

        int[][] sum = new int[N + 1][2];
        sum[0][0] = 1;
        if (a <= N) {
            sum[a][1] = 1;
        }

        if (b <= N) {
            sum[b][1] = -1;
        }

        if (d <= N) {
            sum[d][0] = -1;
        }

        final int MOD = 1000;
        for (int day = 1; day <= N; ++day) {
            sum[day][1] = (sum[day][1] + sum[day - 1][1]) % MOD;
            sum[day][0] = (sum[day][0] + sum[day - 1][0] + sum[day][1]) % MOD;

            int createStartDay = day + a;
            int notCreateDay = day + b;
            int deathDay = day + d;

            int newCount = sum[day][1];
            if (createStartDay <= N) {
                sum[createStartDay][1] = (sum[createStartDay][1] + newCount) % MOD;
            }

            if (notCreateDay <= N) {
                sum[notCreateDay][1] = (sum[notCreateDay][1] - newCount + MOD) % MOD;
            }

            if (deathDay <= N) {
                sum[deathDay][0] = (sum[deathDay][0] - newCount + MOD) % MOD;
            }
        }

        bw.write(String.valueOf(sum[N][0]));
        br.close();
        bw.close();
    }
}
