import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N];
        for (int n = 0; n < N; ++n) {
            parent[n] = n;
        }

        int answer = 0;
        for (int m = 1; m <= M; ++m) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int parent1 = getParent(parent, n1);
            int parent2 = getParent(parent, n2);

            if (parent1 == parent2) {
                answer = m;
                break;
            } else {
                if (parent1 < parent2) {
                    parent[parent2] = parent1;
                } else {
                    parent[parent1] = parent2;
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
    
    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        parent[n] = getParent(parent, parent[n]);
        return parent[n];
    }
}
