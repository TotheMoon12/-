import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] tree;
    static int[] subTreeNodeCounts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        subTreeNodeCounts = new int[N + 1];
        tree = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            tree[n] = new ArrayList();
        }

        for (int n = 1; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        countSubTreeNodeCount(-1, R);

        StringBuilder answer = new StringBuilder();
        for (int q = 0; q < Q; ++q) {
            int U = Integer.parseInt(br.readLine());
            answer.append(subTreeNodeCounts[U]);
            answer.append(System.lineSeparator());
        }

        System.out.print(answer);
    }

    public static int countSubTreeNodeCount(int parent, int current) {
        int subTreeNodeCount = 1;
        for (int child : tree[current]) {
            if (child == parent) {
                continue;
            }

            subTreeNodeCount += countSubTreeNodeCount(current, child);
        }

        subTreeNodeCounts[current] = subTreeNodeCount;
        return subTreeNodeCount;
    }
}
