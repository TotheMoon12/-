// 더 나은 풀이법으로 피보나치가 있다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int prev = 0;
        int vip = 0;
        int[] memo = new int[N + 1];
        memo[1] = 1;
        int totalCount = 1;
        for (int idx = 0; idx < M; ++idx) {
            vip = Integer.parseInt(br.readLine());
            int count = vip - prev - 1;
            if (count > 0) {
                if (memo[count] != 0) {
                    totalCount *= memo[count];
                } else {
                    int maxBundle = count / 2;
                    int[][] dp = new int[count][maxBundle + 1];
                    int caseCount = 1;

                    for (int pos = 0; pos < count - 1; ++pos) {
                        dp[pos][1] = 1;
                        ++caseCount;
                    }

                    for (int bundleCount = 2; bundleCount <= maxBundle; ++bundleCount) {
                        for (int pos = 0; pos < count; ++pos) {
                            for (int next = pos + 2; next < count; ++next) {
                                dp[pos][bundleCount] += dp[next][bundleCount - 1];
                            }

                            caseCount += dp[pos][bundleCount];
                        }
                    }

                    memo[count] = caseCount;
                    totalCount *= caseCount;
                }
            }

            prev = vip;
        }

        if (vip < N) {
            int count = N - vip;
            if (count > 0) {
                if (memo[count] != 0) {
                    totalCount *= memo[count];
                } else {
                    int maxBundle = count / 2;
                    int[][] dp = new int[count][maxBundle + 1];
                    int caseCount = 1;

                    for (int pos = 0; pos < count - 1; ++pos) {
                        dp[pos][1] = 1;
                        ++caseCount;
                    }

                    for (int bundleCount = 2; bundleCount <= maxBundle; ++bundleCount) {
                        for (int pos = 0; pos < count; ++pos) {
                            for (int next = pos + 2; next < count; ++next) {
                                dp[pos][bundleCount] += dp[next][bundleCount - 1];
                            }

                            caseCount += dp[pos][bundleCount];
                        }
                    }

                    memo[count] = caseCount;
                    totalCount *= caseCount;
                }
            }
        }

        System.out.println(totalCount);
    }
}
