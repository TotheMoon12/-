// 모든 경우를 보지만 더하고 뺀 것에 따른 각 곡의 차례에서의 볼륨값의 상태를 HashSet으롤 저장해두고 다음번에 해당 곡에서 해당 볼륨에 도달한다면
// 이미 본 경우이기 때문에 무시하는 방식으로 활용한다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] v = new int[N];
        st = new StringTokenizer(br.readLine());
        HashSet<Integer>[] hashSets = new HashSet[N];
        for (int i = 0; i < N; ++i) {
            v[i] = Integer.parseInt(st.nextToken());
            hashSets[i] = new HashSet<>();
        }

        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        isMax(S, 0, v, dp, M, hashSets);



        System.out.println(dp[N - 1]);
    }

    public static boolean isMax(int p, int index, int[] v, int[] dp, final int upperBound, HashSet<Integer>[] hashSets) {
        if (index == v.length) {
            if (p > dp[v.length - 1]) {
                dp[v.length - 1] = p;
                return true;
            } else {
                return false;
            }
        }

        int nextP = p + v[index];
        boolean max = false;
        if (nextP <= upperBound) {
            if (!hashSets[index].contains(nextP)) {
                hashSets[index].add(nextP);
                if (isMax(nextP, index + 1, v, dp, upperBound, hashSets)) {
                    dp[index] = nextP;
                    max = true;
                }
            }
        }

        nextP = p - v[index];
        if (nextP >= 0) {
            if (!hashSets[index].contains(nextP)) {
                hashSets[index].add(nextP);
                if (isMax(nextP, index + 1, v, dp, upperBound, hashSets)) {
                    dp[index] = nextP;
                    max = true;
                }
            }
        }

        return max;
    }
}
