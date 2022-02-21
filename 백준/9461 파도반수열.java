import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] padobanSeq = new long[101];
        padobanSeq[1] = 1;
        padobanSeq[2] = 1;
        padobanSeq[3] = 1;
        padobanSeq[4] = 2;
        padobanSeq[5] = 2;

        for (int i = 6; i <= 100; ++i) {
            padobanSeq[i] = padobanSeq[i - 1] + padobanSeq[i - 5];
        }

        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < testCaseNum; ++i) {
            int N = Integer.parseInt(br.readLine());
            sb.append(padobanSeq[N]);
            sb.append(System.lineSeparator());
        }

        int N = Integer.parseInt(br.readLine());
        sb.append(padobanSeq[N]);

        System.out.println(sb.toString());
    }
}
