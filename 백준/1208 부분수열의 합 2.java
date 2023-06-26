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

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] leftArr = new int[N / 2];
        int[] rightArr = new int[N - N / 2];
        for (int n = 0; n < leftArr.length; ++n) {
            leftArr[n] = Integer.parseInt(st.nextToken());
        }

        for (int n = 0; n < rightArr.length; ++n) {
            rightArr[n] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> leftList = new ArrayList<>();
        make(leftList, leftArr, 0, 0);

        ArrayList<Integer> rightList = new ArrayList<>();
        make(rightList, rightArr, 0, 0);
        Collections.sort(rightList);

        long answer = 0L;
        for (int number : leftList) {
            if (number == S) {
                ++answer;
            }

            int target = S - number;

            int left = 0;
            int right = rightList.size();

            while (left < right) {
                int mid = (left + right) / 2;
                int value = rightList.get(mid);

                if (value >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int lower = right;
            left = 0;
            right = rightList.size();

            while (left < right) {
                int mid = (left + right) / 2;
                int value = rightList.get(mid);

                if (value > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int upper = right;
            int count = upper - lower;
            answer += count;
        }

        for (int number : rightList) {
            if (number == S) {
                ++answer;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void make(ArrayList<Integer> out, int[] arr, int index, int sum) {
        if (index == arr.length) {
            return;
        }

        out.add(sum + arr[index]);
        make(out, arr, index + 1, sum + arr[index]);
        make(out, arr, index + 1, sum);
    }
}
