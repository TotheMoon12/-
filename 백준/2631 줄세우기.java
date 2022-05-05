// 가장 긴 부분 수열 구하고 그 나머지 수가 최소 이동횟수가 된다
// 가장 덜 움직이게 하는 방법이 가장 길게 증가하게 서 있는 애들을 가만히 두고 나머지를 움직이는 것이므로 가장 긴 부분 수열을 구하는 문제와 같다

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int childNum = Integer.parseInt(br.readLine());

        int[] line = new int[childNum];
        for (int i = 0; i < childNum; ++i) {
            line[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        int[] dp = new int[childNum];
        for (int i = 0; i < childNum; ++i) {
            int number = line[i];
            int max = 0;
            for (int j = i - 1; j >= 0; --j) {
                if (line[j] < number) {
                    max = Math.max(dp[j], max);
                }
            }

            dp[i] = max + 1;
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(childNum - answer);
    }
}
