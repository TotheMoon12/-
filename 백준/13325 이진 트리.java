import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int answer; // 다른 분의 코드를 참조하여 트리를 순회하면서 미리 결과값을 누적시키면 되었음.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int K = Integer.parseInt(br.readLine());
        final int NODE_COUNT = (int) Math.pow(2, K + 1) - 1;
        int[] tree = new int[NODE_COUNT + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int k = 2; k <= NODE_COUNT; ++k) {
            tree[k] = Integer.parseInt(st.nextToken());
        }

        searchTree(tree, 1);
        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static int searchTree(int[] tree, int k) {
        int sum = tree[k];

        int leftIndex = k * 2;
        int rightIndex = leftIndex + 1;
        if (rightIndex <= tree.length) {
            int left = searchTree(tree, leftIndex);
            int right = searchTree(tree, rightIndex);

            if (left >= right) {
                sum += left;
                tree[rightIndex] += left - right;
            } else {
                sum += right;
                tree[leftIndex] += right - left;
            }

            answer += tree[leftIndex] + tree[rightIndex];
        }

        return sum;
    }
}
