import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int MAX_CARD = 1_000_000;
        int[] number = new int[MAX_CARD + 1];
        for (int n = 1; n <= N; ++n) {
            int card = Integer.parseInt(st.nextToken());
            number[card] = n;
        }

        int[] scores = new int[N + 1];
        // 선택된 카드만 확인하게 하면 지금 외각for문보다 for문을 더 적게 돌게 할 수 있음
        for (int card = 1; card <= MAX_CARD; ++card) {
            int player = number[card];
            if (number[card] == 0) {
                continue;
            }

            for (int big = card * 2; big <= MAX_CARD; big += card) {
                int other = number[big];
                if (number[big] != 0) {
                    ++scores[player];
                    --scores[other];
                }
            }
        }

        StringBuilder builder = new StringBuilder(N);
        for (int n = 1; n <= N; ++n) {
            builder.append(scores[n])
                    .append(" ");
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
