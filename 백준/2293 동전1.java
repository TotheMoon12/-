// 알고리즘 스터디원의 풀이를 듣고 따라 풀었다
// 첫 동전을 0개부터 최대한 사용할 수 있을만큼 사용할 때 모든 가능한 값을 주어진 목표값까지 만들어내고 
// 두 번째 타입의 동전부터도 똑같이 하는데 이전 동전으로 만들어낼 수 있는 가치의 경우의 수를 활용하여 dp방식으로 푼다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int coinTypeNum = Integer.parseInt(st.nextToken());
        int targetValue = Integer.parseInt(st.nextToken());

        int[][] dp = new int[coinTypeNum + 1][targetValue + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coinTypeNum; ++i) {
            int coin = Integer.parseInt(br.readLine());
            dp[i][0] = 1;

            for (int value = 1; value <= targetValue; ++value) {
                for (int count = 0; count * coin <= value; ++count) {
                    dp[i][value] += dp[i - 1][value - count * coin];
                }
            }
        }


        System.out.println(dp[coinTypeNum][targetValue]);
    }
}
