import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> tetrahedrons = new ArrayList<>();
        int count = 1;
        int plus = 2;
        int sum = 0;
        while (true) {
            sum += count;
            if (sum > N) {
                break;
            }
            tetrahedrons.add(sum);
            count += plus;
            ++plus;
        }

        int[] dp = new int[N + 1];
        for (int tetrahedron : tetrahedrons) {
            dp[tetrahedron] = 1;
        }

        for (int n = 1; n <= N; ++n) {
            for (int tetrahedron : tetrahedrons) {
                int next = n + tetrahedron;
                if (next > N) {
                    break;
                }

                if (dp[next] != 0) {
                    dp[next] = Math.min(dp[next], dp[n] + 1);
                } else {
                    dp[next] = dp[n] + 1;
                }
            }
        }

        bw.write(Integer.toString(dp[N]));
        br.close();
        bw.close();
    }
}
