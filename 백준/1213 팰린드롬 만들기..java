import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = br.readLine();
        final int LENGTH = name.length();
        PriorityQueue<Character> pq = new PriorityQueue<>();
        for (int idx = 0; idx < LENGTH; ++idx) {
            pq.offer(name.charAt(idx));
        }

        StringBuilder builder = new StringBuilder();
        char notMatchedChar = '\0';
        int notMatchedCharCount = 0;

        while (!pq.isEmpty()) {
            char c = pq.poll();

            if (!pq.isEmpty()) {
                char pair = pq.peek();

                if (c == pair) {
                    pq.poll();
                    builder.append(c);
                } else {
                    ++notMatchedCharCount;
                    notMatchedChar = c;
                }
            } else {
                ++notMatchedCharCount;
                notMatchedChar = c;
            }
        }

        final boolean EVEN = LENGTH % 2 == 0 ? true : false;
        if ((EVEN && notMatchedCharCount != 0) ||
                (!EVEN && notMatchedCharCount != 1)) {
            System.out.print("I'm Sorry Hansoo");
        } else {
            int HALF_LENGTH = builder.length();
            if (!EVEN) {
                builder.append(notMatchedChar);
            }

            for (int idx = HALF_LENGTH - 1; idx >= 0; --idx) {
                builder.append(builder.charAt(idx));
            }

            System.out.println(builder);
        }
    }
}
