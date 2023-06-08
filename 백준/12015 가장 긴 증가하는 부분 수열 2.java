import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>(1000000);
        list.add(Integer.parseInt(st.nextToken()));
        for (int n = 1; n < N; ++n) {
            int number = Integer.parseInt(st.nextToken());

            int index = binarySearch(list, number);
            if (index >= list.size()) {
                list.add(number);
            } else {
                list.set(index, number);
            }
        }

        bw.write(String.valueOf(list.size()));
        br.close();
        bw.close();
    }

    public static int binarySearch(ArrayList<Integer> list, int number) {
        int left = 0;
        int right = list.size();
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (mid >= list.size()) {
                return mid;
            }

            int midNumber = list.get(mid);
            if (midNumber > number) {
                result = mid;
                right = mid - 1;
            } else if (midNumber < number) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return result;
    }
}
