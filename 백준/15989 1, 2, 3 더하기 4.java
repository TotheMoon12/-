import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            int n = Integer.parseInt(br.readLine());
            int count = 0;
            int twoCount = n / 2;
            for (int two = 0; two <= twoCount; ++two) {
                int three = (n - 2 * two) / 3 + 1;
                count += three;
            }

            answer.append(count);
            answer.append(System.lineSeparator());
        }

        System.out.print(answer);
    }
}
