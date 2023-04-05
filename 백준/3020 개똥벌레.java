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
        final int H = Integer.parseInt(st.nextToken());

        int[] countByUpHeight = new int[H + 1];
        int[] countByDownHeight = new int[H + 1];
        for (int n = 0; n < N; ++n) {
            int height = Integer.parseInt(br.readLine());

            if (n % 2 == 0) {
                ++countByUpHeight[height];
            } else {
                ++countByDownHeight[height];
            }
        }

        int[] countByUpSection = new int[H + 2];
        int[] countByDownSection = new int[H + 2];
        for (int h = H; h >= 1; --h) {
            int upCount = countByUpHeight[h];

            countByUpSection[h] += upCount;
            countByUpSection[h - 1] = countByUpSection[h];

            int downCount = countByDownHeight[h];
            int downSection = H - h + 1;
            countByDownSection[downSection] += downCount;
            countByDownSection[downSection + 1] = countByDownSection[downSection];
        }

        int minCount = N;
        int sectionCount = 0;
        for (int section = 1; section <= H; ++section) {
            int count = countByUpSection[section] + countByDownSection[section];
            if (count < minCount) {
                minCount = count;
                sectionCount = 1;
            } else if (count == minCount) {
                ++sectionCount;
            }
        }

        bw.write(minCount + " " + sectionCount);
        br.close();
        bw.close();
    }
}
