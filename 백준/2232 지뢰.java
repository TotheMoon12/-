import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static class Mine {
        final int number;
        final int index;

        public Mine(final int number, final int index) {
            this.number = number;
            this.index = index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] mines = new int[N];
        PriorityQueue<Mine> pq = new PriorityQueue<>(new Comparator<Mine>() {
            @Override
            public int compare(Mine m1, Mine m2) {
                return m2.number - m1.number;
            }
        });

        for (int i = 0; i < N; ++i) {
            mines[i] = Integer.parseInt(br.readLine());
            pq.add(new Mine(mines[i], i));
        }

        br.close();

        PriorityQueue<Integer> mineIndexASC = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        while (!pq.isEmpty()) {
            Mine mine = pq.poll();

            if (mines[mine.index] != 0) {
                mineIndexASC.add(mine.index + 1);

                int power = mine.number;
                for (int i = mine.index + 1; i < N; ++i) {
                    if (power > mines[i]) {
                        power = mines[i];
                        mines[i] = 0;
                    } else {
                        break;
                    }
                }

                power = mine.number;
                for (int i = mine.index - 1; i >= 0; --i) {
                    if (power > mines[i]) {
                        power = mines[i];
                        mines[i] = 0;
                    } else {
                        break;
                    }
                }
            }
        }

        while(!mineIndexASC.isEmpty()) {
            bw.write(mineIndexASC.poll() + "");
            bw.write(System.lineSeparator());
        }
        bw.flush();
        bw.close();
    }
}
