import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int[] set = new int[N];
        for (int n = 0; n < N; ++n) {
            set[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(set);

        int answer = 0;
        for (int i = N - 1; i > 0; --i) {
            int target = set[i];

            for (int j = 0; j < i; ++j) {
                int first = set[j];
                for (int k = 0; k < i; ++k) {
                    int third = target - (first + set[k]);
                    if (third <= 0) {
                        break;
                    }

                    int left = 0;
                    int right = i - 1;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        int value = set[mid];

                        if (value == third) {
                            answer = target;
                            break;
                        } else if (value < third) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                }

                if (answer != 0) {
                    break;
                }
            }

            if (answer != 0) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
