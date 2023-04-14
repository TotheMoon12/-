import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        long answer = 0;
        for (int n = 1; n <= N; ++n) {
            sum += Integer.parseInt(st.nextToken());

            int remain = sum - K;
            int count = map.getOrDefault(remain, 0);
            answer += count;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
