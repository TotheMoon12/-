import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] values = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            int max = Integer.parseInt(st.nextToken());
            for (int j = i - 1; j >= i / 2; --j) {
                max = Math.max(max, values[j] + values[i - j]);
            }

            values[i] = max;
        }

        System.out.println(values[N]);
    }
}
