import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int[] hps;
    static int[] delights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        hps = new int[N];
        delights = new int[N];

        StringTokenizer stHp = new StringTokenizer(br.readLine());
        StringTokenizer stDelight = new StringTokenizer(br.readLine());

        int total = 1 << (N + 1);
        visited = new boolean[total];

        for (int n = 0; n < N; ++n) {
            hps[n] = Integer.parseInt(stHp.nextToken());
            delights[n] = Integer.parseInt(stDelight.nextToken());
        }

        int answer = 0;
        for (int bitMask = 0; bitMask < total; ++bitMask) {
            boolean isPossible = true;

            int delight = 0;
            int hp = 100;

            for (int n = 0; n < N; ++n) {
                if (((bitMask >> n) & 1) == 1) {
                     if (hp - hps[n] > 0) {
                         delight += delights[n];
                         hp -= hps[n];
                     } else {
                         isPossible = false;
                         break;
                     }
                }
            }

            if (isPossible) {
                answer = Math.max(answer, delight);
            }
        }

        System.out.println(answer);
    }
}
