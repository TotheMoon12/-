import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int G = Integer.parseInt(br.readLine());
        final int P = Integer.parseInt(br.readLine());

        int[] parent = new int[G + 1];
        int count = 0;
        for (int p = 0; p < P; ++p) {
            int gi = Integer.parseInt(br.readLine());

            int root = find(parent, gi);
            if (root == 1) {
                break;
            } else if (root == 0) {
                parent[gi] = gi;
            } else {
                boolean end = false;
                while (true) {
                    int nextRoot = find(parent, root - 1);

                    if (nextRoot == 0) {
                        parent[root - 1] = root - 1;
                        union(parent, root - 1, root);
                        break;
                    } else if(nextRoot == 1) {
                        end = true;
                        break;
                    } else {
                        union(parent, root, root - 1);
                    }


                    root = nextRoot;
                }

                if (end) {
                    break;
                }
            }

            ++count;
        }

        System.out.print(count);
    }

    public static int find(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return parent[n] = find(parent, parent[n]);
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
