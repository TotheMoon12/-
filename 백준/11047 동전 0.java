import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int idx = 0; idx < N; ++idx) {
            coins[idx] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        for (int idx = N - 1; idx >= 0; --idx) {
            int coin = coins[idx];
            int coinCount = K / coin;

            K -= coin * coinCount;
            answer += coinCount;
            if (K == 0) {
                break;
            }
        }

        System.out.println(answer);
    }
}
