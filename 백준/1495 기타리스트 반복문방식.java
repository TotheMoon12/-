import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] v = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N][M + 1];
        if (S + v[0] <= M) {
            dp[0][S + v[0]] = true;
        }

        if (S - v[0] >= 0) {
            dp[0][S - v[0]] = true;
        }

        for (int music = 1; music < N; ++music) {
            for (int volume = 0; volume <= M; ++volume) {
                int prevPlusVolume = volume - v[music];
                if (prevPlusVolume >= 0 && dp[music - 1][prevPlusVolume]) {
                    dp[music][volume] = true;
                }

                int prevMinusVolume = volume + v[music];
                if (prevMinusVolume <= M && dp[music - 1][prevMinusVolume]) {
                    dp[music][volume] = true;
                }
            }
        }

        int maxVolume = -1;
        for (int volume = M; volume >= 0; --volume) {
            if (dp[N - 1][volume]) {
                maxVolume = volume;
                break;
            }
        }
        
        System.out.println(maxVolume);
    }
}
