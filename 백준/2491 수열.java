import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] upDp = new int[N];
        int[] downDp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 1;
        upDp[0] = 1;
        downDp[0] = 1;

        seq[0] = Integer.parseInt(st.nextToken());
        for (int idx = 1; idx < N; ++idx) {
            seq[idx] = Integer.parseInt(st.nextToken());

            if (seq[idx] >= seq[idx - 1]) {
                upDp[idx] = upDp[idx - 1] + 1;
            } else {
                upDp[idx] = 1;
            }

            if (seq[idx] <= seq[idx - 1]) {
                downDp[idx] = downDp[idx - 1] + 1;
            } else {
                downDp[idx] = 1;
            }

            max = Math.max(max, upDp[idx]);
            max = Math.max(max, downDp[idx]);
        }

        System.out.println(max);
    }
}
