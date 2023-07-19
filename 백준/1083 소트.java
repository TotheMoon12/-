import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for (int n = 0; n < N; ++n) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        final int S = Integer.parseInt(br.readLine());
        int leftS = S;
        StringBuilder builder = new StringBuilder();
        while (leftS > 0 && !list.isEmpty()) {
            int max = list.get(0);
            int index = 0;

            for (int i = 1; i < list.size(); ++i) {
                int number = list.get(i);

                if (i <= leftS) {
                    if (number > max) {
                        max = number;
                        index = i;
                    }
                } else {
                    break;
                }
            }

            leftS -= index;
            list.remove(index);
            builder.append(max);
            builder.append(' ');
        }

        for (int number : list) {
            builder.append(number);
            builder.append(' ');
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
