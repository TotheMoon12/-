import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        String line = br.readLine();

        int lCount = 0;
        for (int idx = 0; idx < N; ++idx) {
            if (line.charAt(idx) == 'L') {
                ++lCount;
            }
        }

        System.out.print(Math.min(N, N - lCount / 2 + 1));
        br.close();
    }
}
