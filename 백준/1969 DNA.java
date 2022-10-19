import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        ArrayList<String> list = new ArrayList<>();
        for (int n = 0; n < N; ++n) {
            list.add(br.readLine());
        }

        final int ALPHABET_COUNT = 26;
        char[] answer = new char[M];
        int hammingDistance = 0;
        for (int m = 0; m < M; ++m) {
            int[] count = new int[ALPHABET_COUNT];

            for (String s : list) {
                ++count[s.charAt(m) - 'A'];
            }

            int max = count[0];
            char c = 'A';
            for (int idx = 0; idx < ALPHABET_COUNT; ++idx) {
                hammingDistance += count[idx];

                if (count[idx] > max) {
                    max = count[idx];
                    c = (char) ('A' + idx);
                }
            }

            answer[m] = c;
            hammingDistance -= count[c - 'A'];
        }

        System.out.println(answer);
        System.out.print(hammingDistance);
        br.close();
    }
}
