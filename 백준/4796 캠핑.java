import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        int count = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L == 0 && P == 0 && V == 0) {
                break;
            }

            int max = 0;
            max += (V / P) * L;
            if (V % P > L) {
                max += L;
            } else {
                max += V % P;
            }

            answer.append(String.format("Case %d: ", count++));
            answer.append(max);
            answer.append(System.lineSeparator());
        }

        System.out.print(answer);
    }
}
