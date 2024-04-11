import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static class Node {
        Node left;
        Node right;
        int number;

        public Node() {
            left = null;
            right = null;
        }
    }

    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        ArrayList<Integer> list = new ArrayList<>();
        while ((input = br.readLine()) != null) {
            int number = Integer.parseInt(input);
            list.add(number);
        }

        Node root = new Node();
        root.number = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            addNode(root, list.get(i));
        }
        StringBuilder builder = new StringBuilder();
        postOrder(root, builder);
        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    public static void addNode(Node node, int number) {
        if (number < node.number) {
            if (node.left == null) {
                node.left = new Node();
                node.left.number = number;
            } else {
                addNode(node.left, number);
            }
        } else {
            if (node.right == null) {
                node.right = new Node();
                node.right.number = number;
            } else {
                addNode(node.right, number);
            }
        }
    }


    public static void postOrder(Node node, StringBuilder builder) {
        if (node == null) {
            return;
        }

        postOrder(node.left, builder);
        postOrder(node.right, builder);
        builder.append(node.number);
        builder.append(System.lineSeparator());
    }
}
