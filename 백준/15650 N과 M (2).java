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

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        StringBuilder builder = new StringBuilder();
        dfs(1, 0, N, M, builder, bw);

        br.close();
        bw.close();
    }

    public static void dfs(int number, int length, final int N, final int M, StringBuilder builder, BufferedWriter bw) throws IOException {
        if (length == M) {
            bw.write(builder.toString());
            bw.write(System.lineSeparator());
            return;
        }

        if (number > N) {
            return;
        }

        builder.append(number).append(" ");
        dfs(number + 1, length + 1, N, M, builder, bw);
        builder.setLength(builder.length() - 2);
        dfs(number + 1, length, N, M, builder, bw);
    }
}
