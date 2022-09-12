import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> plusPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minusPQ = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        boolean zero = false;
        for (int n = 0; n < N; ++n) {
            int number = Integer.parseInt(br.readLine());

            if (number > 0) {
                plusPQ.offer(number);
            } else if (number < 0) {
                minusPQ.offer(number);
            } else {
                zero = true;
            }
        }

        int answer = 0;

        while (!plusPQ.isEmpty()) {
            int number = plusPQ.poll();

            if (!plusPQ.isEmpty()) {
                int next = plusPQ.poll();
                if (number == 1 || next == 1) {
                    number += next;
                } else {
                    number *= next;
                }
            }

            answer += number;
        }

        while (!minusPQ.isEmpty()) {
            int number = minusPQ.poll();

            if (!minusPQ.isEmpty()) {
                number *= minusPQ.poll();
            } else {
                if (zero) {
                    number = 0;
                }
            }

            answer += number;
        }

        System.out.print(answer);
    }
}
