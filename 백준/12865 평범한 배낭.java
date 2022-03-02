// 0-1 배낭 문제이다
// 스스롶 풀어볼 때는 풀지 못했고
// 다른 블로거분의 설명을 듣고 코드로 구현했다
// 아직 잘 이해가 되지는 않는다


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int count = Integer.parseInt(st.nextToken());
        final int maxWeight = Integer.parseInt(st.nextToken());

        int[] weights = new int[count + 1];
        int[] values = new int[count + 1];

        for (int i = 1; i <= count; ++i) {
            st = new StringTokenizer(br.readLine());

            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[count + 1][maxWeight + 1];
        for (int i = 1; i <= count; ++i) {
            for (int weight = 1; weight <= maxWeight; ++weight) {
                if (weights[i] <= weight) {
                    if (dp[i - 1][weight - weights[i]] + values[i] > dp[i - 1][weight]) {
                        dp[i][weight] = dp[i - 1][weight - weights[i]] + values[i];
                    } else {
                        dp[i][weight] = dp[i - 1][weight];
                    }
                } else {
                    dp[i][weight] = dp[i - 1][weight];
                }
            }
        }

        System.out.println(dp[count][maxWeight]);
    }
}
