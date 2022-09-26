import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken()); // 행
        final int M = Integer.parseInt(st.nextToken()); // 열

        int count;
        if (N == 1) {
            count = 1;
        } else if (N <= 2) {
            count = Math.min((M - 1) / 2 + 1, 4);
        } else {
            if (M < 7) {
                count = Math.min(M, 4);
            } else {
                count = M - 2;
            }
        }

        System.out.print(count);
    }
}
