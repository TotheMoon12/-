import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        final int J = Integer.parseInt(br.readLine());
        int basketBack = M;

        int answer = 0;
        for (int j = 0; j < J; ++j) {
            final int apple = Integer.parseInt(br.readLine());

            int basketFront = basketBack - M + 1;
            if (apple < basketFront) {
                int distance = basketFront - apple;
                answer += distance;
                basketBack -= distance;
            } else if (apple > basketBack) {
                int distance = apple - basketBack;
                answer += distance;
                basketBack = apple;
            }
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
