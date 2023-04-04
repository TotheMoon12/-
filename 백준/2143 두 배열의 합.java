import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(br.readLine());
        final int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; ++n) {
            a[n] = Integer.parseInt(st.nextToken());
        }

        final int M = Integer.parseInt(br.readLine());
        int[] b = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; ++m) {
            b[m] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            int sum = 0;
            for (int j = i; j < N; ++j) {
                sum += a[j];
                aList.add(sum);
            }
        }

        for (int i = 0; i < M; ++i) {
            int sum = 0;
            for (int j = i; j < M; ++j) {
                sum += b[j];
                bList.add(sum);
            }
        }

        Collections.sort(aList);
        Collections.sort(bList);

        int aIndex = 0;
        int bIndex = bList.size() - 1;

        long answer = 0;
        final int A_LIST_LENGTH = aList.size();
        while (aIndex < A_LIST_LENGTH && bIndex >= 0) {
            int aSum = aList.get(aIndex);
            int bSum = bList.get(bIndex);

            int sum = aSum + bSum;
            if (sum == T) {
                long aCount = 0;
                long bCount = 0;

                while (aIndex < A_LIST_LENGTH && aList.get(aIndex) == aSum) {
                    ++aCount;
                    ++aIndex;
                }

                while (bIndex >= 0 && bList.get(bIndex) == bSum) {
                    ++bCount;
                    --bIndex;
                }

                answer += aCount * bCount;
            } else if (sum > T) {
                --bIndex;
            } else {
                ++aIndex;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
