import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for (int n = 0; n < N; ++n) {
            set.add(br.readLine());
        }

        int answer = 0;
        for (int m = 0; m < M; ++m) {
            String s = br.readLine();

            if (set.contains(s)) {
                ++answer;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
