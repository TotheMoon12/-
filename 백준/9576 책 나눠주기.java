import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static class Pair {
        final int a;
        final int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder(T);
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int N = Integer.parseInt(st.nextToken());
            final int M = Integer.parseInt(st.nextToken());

            int[] parent = new int[N + 1];
            for (int n = 1; n <= N; ++n) {
                parent[n] = n;
            }

            Pair[] applicationForms = new Pair[M];
            for (int m = 0; m < M; ++m) {
                st = new StringTokenizer(br.readLine());
                final int a = Integer.parseInt(st.nextToken());
                final int b = Integer.parseInt(st.nextToken());
                applicationForms[m] = new Pair(a, b);
            }

            Arrays.sort(applicationForms, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if (o1.a >= o2.a && o1.b <= o2.b) {
                        return -1;
                    } else if (o2.a >= o1.a && o2.b <= o1.b) {
                        return 1;
                    }

                    if (o1.b == o2.b) {
                        return o2.a - o1.a;
                    }

                    return o2.b - o1.b;
                }
            });

            int answer = 0;
            for (int m = 0; m < M; ++m) {
                Pair applicationForm = applicationForms[m];
                int book = find(parent, applicationForm.b);

                if (book >= applicationForm.a && book <= applicationForm.b) {
                    ++answer;
                    union(parent, parent[book - 1], parent[book]);
                }
            }

            builder.append(answer);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    public static int find(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        int myParent = find(parent, parent[n]);
        parent[n] = myParent;
        return myParent;
    }

    public static void union(int[] parent, int n1, int n2) {
        int parent1 = find(parent, n1);
        int parent2 = find(parent, n2);

        if (parent1 < parent2) {
            parent[n2] = parent1;
        } else {
            parent[n1] = parent2;
        }
    }
}
