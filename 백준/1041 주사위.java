import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final long N = Long.parseLong(br.readLine());
        long[] dice = new long[6];
        StringTokenizer diceST = new StringTokenizer(br.readLine());

        long oneMin = 50L;
        long sum = 0L;
        long max = 0L;
        for (int idx = 0; idx < 6; ++idx) {
            dice[idx] = Long.parseLong(diceST.nextToken());
            oneMin = Math.min(oneMin, dice[idx]);
            sum += dice[idx];
            max = Math.max(max, dice[idx]);
        }

        if (N == 1) {
            System.out.print(sum - max);
            br.close();
            return;
        }

        // A: 0, B: 1, C: 2, D:3, E:4, F:5
        long twoMin = 100L;
        twoMin = Math.min(twoMin, dice[0] + dice[1]);
        twoMin = Math.min(twoMin, dice[0] + dice[2]);
        twoMin = Math.min(twoMin, dice[0] + dice[3]);
        twoMin = Math.min(twoMin, dice[0] + dice[4]);
        twoMin = Math.min(twoMin, dice[1] + dice[2]);
        twoMin = Math.min(twoMin, dice[1] + dice[3]);
        twoMin = Math.min(twoMin, dice[1] + dice[5]);
        twoMin = Math.min(twoMin, dice[2] + dice[4]);
        twoMin = Math.min(twoMin, dice[2] + dice[5]);
        twoMin = Math.min(twoMin, dice[3] + dice[4]);
        twoMin = Math.min(twoMin, dice[3] + dice[5]);
        twoMin = Math.min(twoMin, dice[4] + dice[5]);

        long threeMin = 150L;
        threeMin = Math.min(threeMin, dice[0] + dice[1] + dice[2]);
        threeMin = Math.min(threeMin, dice[0] + dice[1] + dice[3]);
        threeMin = Math.min(threeMin, dice[0] + dice[3] + dice[4]);
        threeMin = Math.min(threeMin, dice[0] + dice[2] + dice[4]);
        threeMin = Math.min(threeMin, dice[5] + dice[2] + dice[1]);
        threeMin = Math.min(threeMin, dice[5] + dice[1] + dice[3]);
        threeMin = Math.min(threeMin, dice[5] + dice[3] + dice[4]);
        threeMin = Math.min(threeMin, dice[5] + dice[2] + dice[4]);

        long answer = 0L;
        answer += (N - 2) * (N - 1) * 4 * oneMin;
        answer += (N - 2) * (N - 2) * oneMin;
        answer += threeMin * 4;
        answer += twoMin * (N - 1) * 4;
        answer += twoMin * (N - 2) * 4;

        System.out.print(answer);
        br.close();
    }
}

