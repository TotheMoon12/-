import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String commands = br.readLine();
            final int COMMAND_COUNT = commands.length();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
            int[] numbers = new int[n + 1];
            for (int idx = 1; idx <= n; ++idx) {
                numbers[idx] = Integer.parseInt(st.nextToken());
            }

            int front = 1;
            int end = n;
            boolean isFront = true;
            boolean error = false;

            for (int idx = 0; idx < COMMAND_COUNT; ++idx) {
                char command = commands.charAt(idx);

                if (command == 'R') {
                    isFront = !isFront;
                } else {
                    if (front > end) {
                        error = true;
                        break;
                    }

                    if (isFront) {
                        ++front;
                    } else {
                        --end;
                    }
                }
            }

            if (error) {
                builder.append("error");
            } else {
                builder.append('[');
                if (isFront) {
                    for (int idx = front; idx <= end; ++idx) {
                        builder.append(numbers[idx]);

                        if (idx != end) {
                            builder.append(',');
                        }
                    }
                } else {
                    for (int idx = end; idx >= front; --idx) {
                        builder.append(numbers[idx]);

                        if (idx != front) {
                            builder.append(',');
                        }
                    }
                }

                builder.append(']');
            }

            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
