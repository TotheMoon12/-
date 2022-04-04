import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][2];

        int max = 0;
        int numberIndex = 0;
        for (int i = 0; i < N; ++i) {
            dp[i][0] = 1;
            dp[i][1] = -1;

            int maxLength = 0;
            int index = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (seq[j] < seq[i]) {
                    if (dp[j][0] > maxLength) {
                        maxLength = dp[j][0];
                        index = j;
                    }
                }
            }

            if (maxLength != 0) {
                dp[i][0] += maxLength;
                dp[i][1] = index;
            }

            if (dp[i][0] > max) {
                max = dp[i][0];
                numberIndex = i;
            }
        }

        System.out.println(max);
        ArrayList<Integer> answer = new ArrayList<>();
        while (numberIndex != -1) {
            answer.add(seq[numberIndex]);
            numberIndex = dp[numberIndex][1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = max - 1; i >= 0; --i) {
            sb.append(answer.get(i));
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }
}
