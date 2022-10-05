import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N];
        for (int n = 0; n < N; ++n) {
            scores[n] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        for (int n = N - 2; n >= 0; --n) {
            int diff = scores[n] - scores[n + 1];
            if (diff >= 0) {
                answer += diff + 1;
                scores[n] = scores[n + 1] - 1;
            }
        }

        System.out.print(answer);
    }
}
