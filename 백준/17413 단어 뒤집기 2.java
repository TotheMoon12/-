import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        final int LENGTH = s.length();
        int openTagCount = 0;
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder(LENGTH);
        for (int idx = 0; idx < LENGTH; ++idx) {
            char c = s.charAt(idx);

            if (c == '<') {
                popAll(stack, builder);
                ++openTagCount;
                builder.append(c);
            } else if (c == '>') {
                --openTagCount;
                builder.append(c);
            } else if (c == ' ') {
                popAll(stack, builder);
                builder.append(c);
            } else {
                if (openTagCount > 0) {
                    builder.append(c);
                } else {
                    stack.push(c);
                }
            }
        }

        popAll(stack, builder);

        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    private static void popAll(Stack<Character> stack, StringBuilder builder) {
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
    }
}
