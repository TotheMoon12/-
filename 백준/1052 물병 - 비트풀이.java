import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static  void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        String binary = Integer.toBinaryString(N);
        final int LENGTH = binary.length();
      
        int count = 0;
        int extra = 0;
        int remove = 0;
        for (int idx = 0; idx < LENGTH; ++idx) {
            if (binary.charAt(idx) == '1') {
                ++count;
            }

            if (count == K && extra == 0) {
                extra = (int) Math.pow(2, LENGTH - idx - 1);
            }

            if (count == K + 1) {
                remove = Integer.parseInt(binary.substring(idx), 2);
                break;
            }
        }

        int answer = 0;
        if (count > K) {
            answer = extra - remove;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
