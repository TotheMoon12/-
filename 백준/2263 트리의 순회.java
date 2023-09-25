import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static class Node {
        int number;
        Node parent;
        Node left;
        Node right;

        public Node(int number) {
            this.number = number;
            parent = null;
            left = null;
            right = null;
        }
    }

    public static class Info {
        int ioIndex;
        int poIndex;

        public Info(int ioIndex, int poIndex) {
            this.ioIndex = ioIndex;
            this.poIndex = poIndex;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        int[] inOrder = new int[n];
        int[] postOrder = new int[n];

        StringTokenizer inOrderST = new StringTokenizer(br.readLine());
        StringTokenizer postOrderST = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            inOrder[i] = Integer.parseInt(inOrderST.nextToken());
            postOrder[i] = Integer.parseInt(postOrderST.nextToken());
        }


        Info indexInfo = new Info(0, 0);
        Node root = makeTree(inOrder, postOrder, -1, indexInfo);
        StringBuilder builder = new StringBuilder();
        preOrder(root, builder);
        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    public static Node makeTree(int[] inOrder, int[] postOrder, int middleNumber, Info indexInfo) {
        Node middle = null;
        while (indexInfo.ioIndex < inOrder.length && indexInfo.poIndex < postOrder.length && postOrder[indexInfo.poIndex] != middleNumber) {
            if (inOrder[indexInfo.ioIndex] == postOrder[indexInfo.poIndex]) {
                if (middle == null) {
                    middle = new Node(inOrder[indexInfo.ioIndex]);
                } else {
                    Node node = new Node(inOrder[indexInfo.ioIndex]);
                    node.left = middle;
                    middle.parent = node;
                    middle = node;
                }

                ++indexInfo.ioIndex;
                ++indexInfo.poIndex;
            } else {
                Node node = new Node(inOrder[indexInfo.ioIndex]);
                node.left = middle;
                if (middle != null) {
                    middle.parent = node;
                }

                middle = node;
                ++indexInfo.ioIndex;
                middle.right = makeTree(inOrder, postOrder, middle.number, indexInfo);
                if (middle.right != null) {
                    middle.right.parent = middle;
                }

                ++indexInfo.poIndex;
            }
        }

        return middle;
    }

    public static void preOrder(Node node, StringBuilder builder) {
        builder.append(node.number);
        builder.append(" ");
        if (node.left != null) {
            preOrder(node.left, builder);
        }

        if (node.right != null) {
            preOrder(node.right, builder);
        }
    }
}
