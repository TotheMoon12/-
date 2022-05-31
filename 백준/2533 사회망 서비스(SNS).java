import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; ++idx) {
            tree[idx] = new ArrayList<>();
        }

        for (int idx = 1; idx < n; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        boolean[] nodeState = new boolean[n + 1];
        search(tree, nodeState, 1, -1);
        int count = 0;
        for (int idx = 1; idx <= n; ++idx) {
            if (nodeState[idx]) {
                ++count;
            }
        }

        System.out.println(count);
    }

    public static boolean search(ArrayList<Integer>[] tree, boolean[] nodeState, int current, int parent) {
        boolean isLeaf = true;
        boolean isChildEarly = false;
        for (int child : tree[current]) {
            if (child == parent) {
                continue;
            }

            isLeaf = false;

            boolean isChildLeaf = search(tree, nodeState, child, current);
            if (isChildLeaf) {
                nodeState[current] = true;
            }

            if (nodeState[child]) {
                isChildEarly = true;
            }
        }

        if (isLeaf || (isChildEarly && !nodeState[current])) {
            return true;
        } else {
            return false;
        }
    }
}
