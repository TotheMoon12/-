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
        final long A = Long.parseLong(st.nextToken());
        final long B = Long.parseLong(st.nextToken());

        long answer = 0;
        long digit = 1;

        while (digit <= B) {
            long divider = digit * 2;

            long oneCountA = A / divider * digit + Math.max(A % divider - divider / 2, 0); // A 이전까지 1의 개수
            long countB = B + 1;
            long oneCountB = countB / divider * digit + Math.max(countB % divider - divider / 2, 0); // B 까지 1의 개수
            answer += oneCountB - oneCountA;

            digit *= 2;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
