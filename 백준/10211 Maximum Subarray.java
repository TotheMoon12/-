import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int answer = Integer.parseInt(st.nextToken());
            int sum = answer;
            for (int n = 1; n < N; ++n) {
                int number = Integer.parseInt(st.nextToken());

                if (sum + number > number) {
                    sum += number;
                } else {
                    sum = number;
                }

                answer = Math.max(answer, sum);
            }

            sb.append(answer);
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
        br.close();
    }
}
