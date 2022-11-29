import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int PROBLEM_MAX_D = 10000;

        int maxD = 0;
        PriorityQueue<Integer>[] pqArr = new PriorityQueue[PROBLEM_MAX_D + 1];
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (pqArr[d] == null) {
                pqArr[d] = new PriorityQueue<>(Comparator.reverseOrder());
            }

            pqArr[d].offer(p);
            maxD = Math.max(maxD, d);
        }

        int answer = 0;
        for (int day = maxD; day >= 1; --day) {
            int maxP = 0;
            int maxDayIndex = -1;

            for (int next = day; next <= maxD; ++next) {
                if (pqArr[next] != null && !pqArr[next].isEmpty()) {
                    int p = pqArr[next].peek();
                    if (pqArr[next].peek() > maxP) {
                        maxP = p;
                        maxDayIndex = next;
                    }
                }
            }

            if (maxDayIndex != -1) {
                answer += maxP;
                pqArr[maxDayIndex].poll();
            }
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
