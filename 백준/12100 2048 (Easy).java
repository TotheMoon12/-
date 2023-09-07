import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] initialBoard;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        initialBoard = new int[N][N];
        for (int r = 0; r < N; ++r) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; ++c) {
                initialBoard[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> order = new ArrayList<>();
        solve(order);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void solve(ArrayList<Integer> order) {
        if (order.size() == 5) {
            int[][] board = new int[initialBoard.length][initialBoard.length];
            for (int r = 0; r < board.length; ++r) {
                for (int c = 0; c < board.length; ++c) {
                    board[r][c] = initialBoard[r][c];
                }
            }

            for (int direction : order) {
                if (direction == 0) {
                    up(board);
                } else if (direction == 1) {
                    down(board);
                } else if (direction == 2) {
                    left(board);
                } else {
                    right(board);
                }
            }

            for (int r = 0; r < board.length; ++r) {
                for (int c = 0; c < board.length; ++c) {
                    answer = Math.max(answer, board[r][c]);
                }
            }

            return;
        }

        for (int direction = 0; direction < 4; ++direction) {
            order.add(direction);
            solve(order);
            order.remove(order.size() - 1);
        }
    }

    public static void up(int[][] board) {
        for (int c = 0; c < board.length; ++c) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int r = 0; r < board.length; ++r) {
                if (board[r][c] != 0) {
                    list.add(board[r][c]);
                    board[r][c] = 0;
                }
            }

            if (list.size() == 1) {
                board[0][c] = list.get(0);
            }

            int moveRow = 0;
            for (int i = 0; i < list.size(); ++i) {
                int number1 = list.get(i);
                if (i == list.size() - 1) {
                    board[moveRow][c] = number1;
                    break;
                }

                int number2 = list.get(i + 1);
                if (number1 == number2) {
                    board[moveRow][c] = number1 * 2;
                    ++i;
                } else {
                    board[moveRow][c] = number1;
                }

                ++moveRow;
            }
        }
    }

    public static void down(int[][] board) {
        for (int c = 0; c < board.length; ++c) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int r = board.length - 1; r >= 0; --r) {
                if (board[r][c] != 0) {
                    list.add(board[r][c]);
                    board[r][c] = 0;
                }
            }

            if (list.size() == 1) {
                board[board.length - 1][c] = list.get(0);
            }

            int moveRow = board.length - 1;
            for (int i = 0; i < list.size(); ++i) {
                int number1 = list.get(i);
                if (i == list.size() - 1) {
                    board[moveRow][c] = number1;
                    break;
                }

                int number2 = list.get(i + 1);
                if (number1 == number2) {
                    board[moveRow][c] = number1 * 2;
                    ++i;
                } else {
                    board[moveRow][c] = number1;
                }

                --moveRow;
            }
        }
    }

    public static void left(int[][] board) {
        for (int r = 0; r < board.length; ++r) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int c = 0; c < board.length; ++c) {
                if (board[r][c] != 0) {
                    list.add(board[r][c]);
                    board[r][c] = 0;
                }
            }

            if (list.size() == 1) {
                board[r][0] = list.get(0);
            }

            int moveCol = 0;
            for (int i = 0; i < list.size(); ++i) {
                int number1 = list.get(i);
                if (i == list.size() - 1) {
                    board[r][moveCol] = number1;
                    break;
                }

                int number2 = list.get(i + 1);
                if (number1 == number2) {
                    board[r][moveCol] = number1 * 2;
                    ++i;
                } else {
                    board[r][moveCol] = number1;
                }

                ++moveCol;
            }
        }
    }

    public static void right(int[][] board) {
        for (int r = 0; r < board.length; ++r) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int c = board.length - 1; c >= 0; --c) {
                if (board[r][c] != 0) {
                    list.add(board[r][c]);
                    board[r][c] = 0;
                }
            }

            if (list.size() == 1) {
                board[r][board.length - 1] = list.get(0);
            }

            int moveCol = board.length - 1;
            for (int i = 0; i < list.size(); ++i) {
                int number1 = list.get(i);
                if (i == list.size() - 1) {
                    board[r][moveCol] = number1;
                    break;
                }

                int number2 = list.get(i + 1);
                if (number1 == number2) {
                    board[r][moveCol] = number1 * 2;
                    ++i;
                } else {
                    board[r][moveCol] = number1;
                }

                --moveCol;
            }
        }
    }
}
