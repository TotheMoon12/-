import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());
        MAX = C;

        st = new StringTokenizer(br.readLine());
        int[] leftArr = new int[N / 2];
        for (int n = 0; n < leftArr.length; ++n) {
            leftArr[n] = Integer.parseInt(st.nextToken());
        }

        int[] rightArr = new int[N - N / 2];
        for (int n = 0; n < rightArr.length; ++n) {
            rightArr[n] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            if (rightArr[0] <= C) {
                bw.write("2");
            } else {
                bw.write("1");
            }

            br.close();
            bw.close();
            return;
        }

        ArrayList<Integer> leftList = new ArrayList<>();
        make(leftArr, 0, 0, leftList);

        ArrayList<Integer> rightList = new ArrayList<>();
        make(rightArr, 0, 0, rightList);
        Collections.sort(rightList);

        long answer = 0;
        for (int number : leftList) {
            if (number > MAX) {
                continue;
            }

            int target = C - number;

            int left = 0;
            int right = rightList.size();

            while (left < right) {
                int mid = (left + right) / 2;
                int value = rightList.get(mid);

                if (value > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer += right;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void make(int[] arr, int index, int sum, ArrayList<Integer> out) {
        if (index == arr.length) {
            out.add(sum);
            return;
        }

        int next = sum + arr[index];
        if (next <= MAX) {
            make(arr, index + 1, next, out);
        }

        make(arr, index + 1, sum, out);
    }
}
