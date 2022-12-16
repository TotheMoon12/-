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
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        int answer;
        int minN = K * (K + 1) / 2;
        if (N < minN) {
            answer = -1;
        } else {
            int remain = (N - minN) % K;
            answer = remain == 0 ? K - 1 : K;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
