import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int SCV_COUNT = Integer.parseInt(br.readLine());
        int[] hp = new int[SCV_COUNT];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int count = 0; count < SCV_COUNT; ++count) {
            hp[count] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        if (SCV_COUNT == 1) {
            answer = hp[0] / 9;
            if (hp[0] % 9 != 0) {
                ++answer;
            }
        } else if (SCV_COUNT == 2) {
            answer = 0;
            while (hp[0] > 0 || hp[1] > 0) {
                if (hp[0] > hp[1]) {
                    hp[0] -= 9;
                    hp[1] -= 3;
                } else {
                    hp[0] -= 3;
                    hp[1] -= 9;
                }

                ++answer;
            }
        } else if (SCV_COUNT == 3) {
            final int MAX = 60;
            int[][][] dp = new int[MAX + 1][MAX + 1][MAX + 1];
            scv3(dp, hp[0], hp[1], hp[2], 0);
            answer = dp[0][0][0];
        }

        System.out.print(answer);
    }

    public static void scv3(int[][][] dp, int hp1, int hp2, int hp3, int count) {
        hp1 = Math.max(0, hp1);
        hp2 = Math.max(0, hp2);
        hp3 = Math.max(0, hp3);

        if (count < dp[hp1][hp2][hp3] || dp[hp1][hp2][hp3] == 0) {
            dp[hp1][hp2][hp3] = count;
        } else {
            return;
        }

        scv3(dp, hp1 - 9, hp2 - 3, hp3 - 1, count + 1);
        scv3(dp, hp1 - 9, hp2 - 1, hp3 - 3, count + 1);
        scv3(dp, hp1 - 3, hp2 - 9, hp3 - 1, count + 1);
        scv3(dp, hp1 - 3, hp2 - 1, hp3 - 9, count + 1);
        scv3(dp, hp1 - 1, hp2 - 9, hp3 - 3, count + 1);
        scv3(dp, hp1 - 1, hp2 - 3, hp3 - 9, count + 1);
    }
}
