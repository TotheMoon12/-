import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    public static class Pair implements Comparable<Pair> {
        char alpha;
        int score;

        public Pair(char alpha, int score) {
            this.alpha = alpha;
            this.score = score;
        }

        @Override
        public int compareTo(Pair o) {
            return o.score - this.score;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        final int ALPHABET_COUNT = 26;

        final int MAX_DIGIT = 8;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int n = 0; n < N; ++n) {
            String word = br.readLine();
            words[n] = word;
            final int LENGTH = word.length();

            for (int idx = 0; idx < LENGTH; ++idx) {
                char c = word.charAt(idx);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + (int)Math.pow(10, LENGTH - 1 - idx));
                } else {
                    map.put(c, (int)Math.pow(10, LENGTH - 1 - idx));
                }
            }
        }

        int[] valueOfAlphabet = new int[ALPHABET_COUNT];
        int value = 9;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (char alpha : map.keySet()) {
            int score = map.get(alpha);
            pq.offer(new Pair(alpha, score));
        }

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            valueOfAlphabet[pair.alpha - 'A'] = value--;
        }

        int answer = 0;
        for (String word : words) {
            StringBuilder strNumber = new StringBuilder();
            final int LENGTH = word.length();
            for (int idx = 0; idx < LENGTH; ++idx) {
                strNumber.append(valueOfAlphabet[word.charAt(idx) - 'A']);
            }

            answer += Integer.parseInt(strNumber.toString());
        }

        System.out.print(answer);
    }
}
