import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        Stack<Character> stack = new Stack<>();
        stack.push(number.charAt(0));

        final int LENGTH = number.length();
        int index = 1;

        while (K > 0 && index < LENGTH) {
            char digit = number.charAt(index);

            while (!stack.isEmpty() && K > 0 && stack.peek() < digit) {
                --K;
                stack.pop();
            }

            stack.push(digit);
            ++index;
        }

        while (K-- > 0) {
            stack.pop();
        }

        while (index < LENGTH) {
            char digit = number.charAt(index++);
            stack.push(digit);
        }

        if (stack.isEmpty()) {
            System.out.print(0);
        } else {
            StringBuilder answer = new StringBuilder();
            Iterator digit = stack.iterator();
            while (digit.hasNext()) {
                answer.append(digit.next());
            }

            System.out.print(answer);
        }

        br.close();
    }
}
