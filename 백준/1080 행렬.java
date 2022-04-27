import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];
        int[][] b = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String s = br.readLine();
            for (int j = 0; j < m; ++j) {
                a[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; ++i) {
            String s = br.readLine();
            for (int j = 0; j < m; ++j) {
                b[i][j] = s.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int i = 0; i <= n - 3; ++i) {
            for (int j = 0; j <= m - 3; ++j) {
                int aElement = a[i][j];
                int bElement = b[i][j];

                if (aElement != bElement) {
                    ++count;
                    for (int startRow = i; startRow < i + 3; ++startRow) {
                        for (int startCol = j; startCol < j + 3; ++startCol) {
                            a[startRow][startCol] = (a[startRow][startCol] + 1) % 2;
                        }
                    }
                }
            }
        }

        boolean isSame = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int aElement = a[i][j];
                int bElement = b[i][j];

                if (aElement != bElement) {
                    isSame = false;
                    break;
                }
            }

            if (!isSame) {
                break;
            }
        }

        if (isSame) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
