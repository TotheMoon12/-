import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] table = new int[N][M];
        for (int i = 0; i < N; ++i) {
            String s = br.readLine();
            for (int j = 0; j < M; ++j) {
                table[i][j] = s.charAt(j) - '0';
            }
        }

        int answer = -1;
        int[] rowData = {1, 1, -1, -1};
        int[] colData = {1, -1, 1, -1};


        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                for (int startRow = 0; startRow < N; ++startRow) {
                    for (int startCol = 0; startCol < M; ++startCol) {
                        for (int i = 0; i < rowData.length; ++i) {
                            int nextRow = startRow;
                            int nextCol = startCol;
                            int rowDiff = row * rowData[i];
                            int colDiff = col * colData[i];

                            if (rowDiff == 0 && colDiff == 0) {
                                int number = table[nextRow][nextCol];
                                if (isPerfectSquareNumber(number)) {
                                    if (answer == -1) {
                                        answer = number;
                                    } else {
                                        answer = Math.max(answer, number);
                                    }
                                }
                                continue;
                            }

                            StringBuilder strNum = new StringBuilder();
                            while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                                strNum.append(table[nextRow][nextCol]);
                                int number = Integer.parseInt(strNum.toString());
                                if (isPerfectSquareNumber(number)) {
                                    if (answer == -1) {
                                        answer = number;
                                    } else {
                                        answer = Math.max(answer, number);
                                    }
                                }

                                nextRow += rowDiff;
                                nextCol += colDiff;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static boolean isPerfectSquareNumber(int number) {
        int sqrtNum = (int) Math.sqrt(number);
        if (sqrtNum * sqrtNum == number) {
            return true;
        } else {
            return false;
        }
    }
}
