// 뒤에서 부터 최적으로 잘라오면 자르는 첫 번째 위치를 다시 찾을 필요 없다.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int L = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] location = new int[K];
        for (int k = 0; k < K; ++k) {
            location[k] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(location);
        int partMaxLength = location[0];
        for (int k = 1; k < K; ++k) {
            partMaxLength = Math.max(partMaxLength, location[k] - location[k - 1]);
        }

        partMaxLength = Math.max(partMaxLength, L - location[K - 1]);

        int left = partMaxLength;
        int right = L;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int prev = 0;
            int length = 0;
            int count = 0;
            for (int k = 0; k < K; ++k) {
                length = location[k] - prev;

                if (length > mid) {
                    ++count;
                    length = location[k] - location[k - 1];
                    prev = location[k - 1];
                }
            }

            length += L - location[K - 1];
            if (length > mid) {
                ++count;
            }

            if (count <= C) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int first = 0;
        for (int start = 0; start < K; ++start) {
            int prev = location[start];
            int length = 0;
            int count = 1;
            for (int k = start + 1; k < K; ++k) {
                length = location[k] - prev;

                if (length > answer) {
                    ++count;
                    length = location[k] - location[k - 1];
                    prev = location[k - 1];
                }
            }

            length += L - location[K - 1];
            if (length > answer) {
                ++count;
            }

            if (count <= C) {
                first = location[start];
                break;
            }
        }

        bw.write(answer + " " + first);
        br.close();
        bw.close();
    }
}
