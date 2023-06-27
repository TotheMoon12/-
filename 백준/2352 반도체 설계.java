import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] connectInfo = new int[N];
        for (int n = 0; n < N; ++n) {
            connectInfo[n] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int n = 0; n < N; ++n) {
            int pair = connectInfo[n];

            int left = 0;
            int right = list.size() - 1;
            int location = -1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int value = list.get(mid);
                if (value > pair) {
                    location = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (location == -1) {
                list.add(pair);
            } else {
                list.set(location, pair);
            }
        }

        bw.write(String.valueOf(list.size()));
        br.close();
        bw.close();
    }
}
