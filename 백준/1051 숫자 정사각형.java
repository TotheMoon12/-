import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] rectangle = new char[N][M];

        for (int n = 0; n < N; ++n) {
            String s = br.readLine();

            for (int m = 0; m < M; ++m) {
                rectangle[n][m] = s.charAt(m);
            }
        }

        int answer = 0;
        for (int n = 0; n < N - 1; ++n) {
            for (int m = 0; m < M - 1; ++m) {
                int number = rectangle[n][m];

                for (int length = answer + 1; n + length <= N - 1 && m + length <= M - 1; ++length) {
                    if (rectangle[n + length][m] == number
                            && rectangle[n][m + length] == number
                            && rectangle[n + length][m + length] == number) {
                        answer = Math.max(answer, length);
                    }
                }
            }
        }

        ++answer;
        System.out.print(answer * answer);
    }
}
