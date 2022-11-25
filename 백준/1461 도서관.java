import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> minus = new ArrayList<>(N);
        ArrayList<Integer> plus = new ArrayList<>(N);
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; ++n) {
            int book = Integer.parseInt(st.nextToken());

            if (book > 0) {
                plus.add(book);
            } else {
                minus.add(Math.abs(book));
            }
        }

        Collections.sort(minus);
        Collections.sort(plus);
        int max = 0;
        boolean direction = true;
        if (!plus.isEmpty()) {
            max = plus.get(plus.size() - 1);
        }

        if (!minus.isEmpty()) {
            int minusMax = minus.get(minus.size() - 1);
            if (minusMax > max) {
                direction = false;
            }
        }

        int answer = 0;
        for (int idx = plus.size() - 1; idx >= 0; idx -= M) {
            answer += plus.get(idx) * 2;
        }

        for (int idx = minus.size() - 1; idx >= 0; idx -= M) {
            answer += minus.get(idx) * 2;
        }

        if (direction) {
            answer -= plus.get(plus.size() - 1);
        } else {
            answer -= minus.get(minus.size() - 1);
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
