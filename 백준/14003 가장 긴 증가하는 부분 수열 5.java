import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static class Pair {
        int number;
        int index;

        public Pair(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Pair>[] sequence = new ArrayList[N];
        for (int n = 0; n < N; ++n) {
            sequence[n] = new ArrayList<>();
        }

        int number = Integer.parseInt(st.nextToken());
        sequence[0].add(new Pair(number, 0));

        int length = 1;
        for (int n = 1; n < N; ++n) {
            number = Integer.parseInt(st.nextToken());
            ArrayList<Pair> prevList = sequence[length - 1];
            if (number > prevList.get(prevList.size() - 1).number) {
                sequence[length++].add(new Pair(number, n));
            } else {
                int left = 0;
                int right = length;

                while (left < right) {
                    int mid = (left + right) / 2;
                    ArrayList<Pair> list = sequence[mid];
                    int value = list.get(list.size() - 1).number;

                    if (value >= number) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                sequence[left].add(new Pair(number, n));
            }
        }

        Pair[] out = new Pair[length];
        getPart(sequence, length - 1, out, length);

        StringBuilder builder = new StringBuilder();
        builder.append(length);
        builder.append(System.lineSeparator());
        for (Pair pair : out) {
            builder.append(pair.number);
            builder.append(" ");
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    public static boolean getPart(ArrayList<Pair>[] sequence, int index, Pair[] out, final int LENGTH) {
        if (index < 0) {
            return true;
        }

        ArrayList<Pair> list = sequence[index];
        for (Pair pair : list) {
            if (index == LENGTH - 1) {
                out[index] = pair;
                if (getPart(sequence, index - 1, out, LENGTH)) {
                    return true;
                }
            } else {
                Pair prev = out[index + 1];
                if (pair.index < prev.index && pair.number < prev.number) {
                    out[index] = pair;
                    if (getPart(sequence, index - 1, out, LENGTH)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

/*
10
10 30 10 50 40 20 50 70 80 90

10 20 40 50
 */
