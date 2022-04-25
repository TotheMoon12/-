import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int answer = getOrder(N, r, c);
        System.out.println(answer);
    }

    public static int getOrder(int N, int r, int c) {
        if (N == 0) {
            return 0;
        }

        int halfLength = (int) Math.pow(2, N - 1);
        if (r < halfLength) {
            if (c < halfLength) {
                return getOrder(N - 1, r, c);
            } else {
                return halfLength * halfLength + getOrder(N - 1, r, c - halfLength);
            }
        } else {
            if (c < halfLength) {
                return halfLength * halfLength * 2 + getOrder(N - 1, r - halfLength, c);
            } else {
                return halfLength * halfLength * 3 + getOrder(N - 1, r - halfLength, c - halfLength);
            }
        }
    }
}
