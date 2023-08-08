import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int ROW = Integer.parseInt(st.nextToken());
        final int COL = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        final int PAPER_COUNT = Integer.parseInt(br.readLine());
        final int ERROR = Integer.parseInt(br.readLine());

        int left = 0;
        for (int e = 0; e < ERROR; ++e) {
            st = new StringTokenizer(br.readLine());
            left = Math.max(left, Integer.parseInt(st.nextToken()));
            int col = Integer.parseInt(st.nextToken());

            list.add(col);
        }

        Collections.sort(list);

        int right = Math.max(ROW, COL);
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 0;
            int c = -mid;
            for (int col : list) {
                if (col >= c + mid) {
                    c = col;
                    ++count;
                }
            }

            if (count <= PAPER_COUNT) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
