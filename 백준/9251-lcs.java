// 같이 알고리즘 공부하는 친구 덕분에 풀이를 알게 된 문제
// 처음부터 두 문자열을 다 비교해야 한다
// 하나의 문자열을 기준으로 잡고 다른 문자열의 한 문자씩 비교하면서 
// 같을 경우 dp의 대각선 이전의 값에 1을 더한 것이 최대 값이 된다
// 다를 경우 내 이전의 dp 값과 한 행 이전 중 최대값이 된다
// 왜냐하면 처음부터 비교해 나가고 매칭이 되지 않을 경우 나 이전의 문자를 수열에 사용했을 때 매칭이되어 긴 수열이 있을 수 있기 때문이다
// 또는 현재 매칭 이전에 매칭이 된 문자가 있어서 그것을 사용해야할 수도 있기 때문이다. 그래야 중간에 건너띄고 나중에 큰 길이의 수열이 되는 경우를 확인할 수 있다

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[1001][1001];

        for (int i = 1; i <= str1.length(); ++i) {
            char c = str1.charAt(i - 1);

            for (int j = 1; j <= str2.length(); ++j) {
                if (str2.charAt(j - 1) == c) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
    }
}
