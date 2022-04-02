import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String word1, String word2) {
                int length1 = word1.length();
                int length2 = word2.length();
                if (length1 == length2) {
                    return word1.compareTo(word2);
                }

                return length1 - length2;
            }
        });

        int N = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            String word = br.readLine();
            if (!set.contains(word)) {
                set.add(word);
                pq.add(word);
            }
        }
        br.close();

        while (!pq.isEmpty()) {
            bw.write(pq.poll());
            bw.write(System.lineSeparator());
        }

        bw.flush();
        bw.close();
    }
}
