import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static final char WALL = '#';
    static final char RED = 'R';
    static final char BLUE = 'B';
    static final char HOLE = 'O';
    static final char BLANK = '.';

    public static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        Position blue = null;
        Position red = null;

        for (int n = 0; n < N; ++n) {
            String s = br.readLine();
            for (int m = 0; m < M; ++m) {
                char type = s.charAt(m);
                board[n][m] = type;

                if (type == BLUE) {
                    blue = new Position(n, m);
                } else if (type == RED) {
                    red = new Position(n, m);
                }
            }
        }

        up(board, blue, red, 1);
        down(board, blue, red, 1);
        left(board, blue, red, 1);
        right(board, blue, red, 1);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void up(char[][] board, Position blue, Position red, int count) {
        if (count > 10) {
            return;
        }

        Position nextBlue = new Position(blue.row, blue.col);
        Position nextRed = new Position(red.row, red.col);
        if (blue.row < red.row) {
            moveUp(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;

            moveUp(board, nextRed);
            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[nextBlue.row][nextBlue.col] = BLANK;
                board[blue.row][blue.col] = BLUE;
                answer = Math.min(answer, count);
                return;
            }

            board[red.row][red.col] = BLANK;
            board[nextRed.row][nextRed.col] = RED;
        } else {
            board[red.row][red.col] = BLANK;
            moveUp(board, nextRed);
            if (board[nextRed.row][nextRed.col] != HOLE) {
                board[nextRed.row][nextRed.col] = RED;
            }

            moveUp(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                if (board[nextRed.row][nextRed.col] != HOLE) {
                    board[nextRed.row][nextRed.col] = BLANK;
                }

                board[red.row][red.col] = RED;
                return;
            }

            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[red.row][red.col] = RED;
                answer = Math.min(answer, count);
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;
        }

        up(board, nextBlue, nextRed, count + 1);
        down(board, nextBlue, nextRed, count + 1);
        left(board, nextBlue, nextRed, count + 1);
        right(board, nextBlue, nextRed, count + 1);

        board[nextBlue.row][nextBlue.col] = BLANK;
        board[blue.row][blue.col] = BLUE;
        board[nextRed.row][nextRed.col] = BLANK;
        board[red.row][red.col] = RED;
    }

    public static void down(char[][] board, Position blue, Position red, int count) {
        if (count > 10) {
            return;
        }

        Position nextBlue = new Position(blue.row, blue.col);
        Position nextRed = new Position(red.row, red.col);
        if (blue.row > red.row) {
            moveDown(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;

            moveDown(board, nextRed);
            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[nextBlue.row][nextBlue.col] = BLANK;
                board[blue.row][blue.col] = BLUE;
                answer = Math.min(answer, count);
                return;
            }

            board[red.row][red.col] = BLANK;
            board[nextRed.row][nextRed.col] = RED;
        } else {
            board[red.row][red.col] = BLANK;
            moveDown(board, nextRed);
            if (board[nextRed.row][nextRed.col] != HOLE) {
                board[nextRed.row][nextRed.col] = RED;
            }

            moveDown(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                if (board[nextRed.row][nextRed.col] != HOLE) {
                    board[nextRed.row][nextRed.col] = BLANK;
                }

                board[red.row][red.col] = RED;
                return;
            }

            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[red.row][red.col] = RED;
                answer = Math.min(answer, count);
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;
        }

        up(board, nextBlue, nextRed, count + 1);
        down(board, nextBlue, nextRed, count + 1);
        left(board, nextBlue, nextRed, count + 1);
        right(board, nextBlue, nextRed, count + 1);

        board[nextBlue.row][nextBlue.col] = BLANK;
        board[blue.row][blue.col] = BLUE;
        board[nextRed.row][nextRed.col] = BLANK;
        board[red.row][red.col] = RED;
    }

    public static void left(char[][] board, Position blue, Position red, int count) {
        if (count > 10) {
            return;
        }

        Position nextBlue = new Position(blue.row, blue.col);
        Position nextRed = new Position(red.row, red.col);
        if (blue.col < red.col) {
            moveLeft(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;

            moveLeft(board, nextRed);
            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[nextBlue.row][nextBlue.col] = BLANK;
                board[blue.row][blue.col] = BLUE;
                answer = Math.min(answer, count);
                return;
            }

            board[red.row][red.col] = BLANK;
            board[nextRed.row][nextRed.col] = RED;
        } else {
            board[red.row][red.col] = BLANK;
            moveLeft(board, nextRed);
            if (board[nextRed.row][nextRed.col] != HOLE) {
                board[nextRed.row][nextRed.col] = RED;
            }

            moveLeft(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                if (board[nextRed.row][nextRed.col] != HOLE) {
                    board[nextRed.row][nextRed.col] = BLANK;
                }

                board[red.row][red.col] = RED;
                return;
            }

            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[red.row][red.col] = RED;
                answer = Math.min(answer, count);
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;
        }

        up(board, nextBlue, nextRed, count + 1);
        down(board, nextBlue, nextRed, count + 1);
        left(board, nextBlue, nextRed, count + 1);
        right(board, nextBlue, nextRed, count + 1);

        board[nextBlue.row][nextBlue.col] = BLANK;
        board[blue.row][blue.col] = BLUE;
        board[nextRed.row][nextRed.col] = BLANK;
        board[red.row][red.col] = RED;
    }

    public static void right(char[][] board, Position blue, Position red, int count) {
        if (count > 10) {
            return;
        }

        Position nextBlue = new Position(blue.row, blue.col);
        Position nextRed = new Position(red.row, red.col);
        if (blue.col > red.col) {
            moveRight(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;

            moveRight(board, nextRed);
            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[nextBlue.row][nextBlue.col] = BLANK;
                board[blue.row][blue.col] = BLUE;
                answer = Math.min(answer, count);
                return;
            }

            board[red.row][red.col] = BLANK;
            board[nextRed.row][nextRed.col] = RED;
        } else {
            board[red.row][red.col] = BLANK;
            moveRight(board, nextRed);
            if (board[nextRed.row][nextRed.col] != HOLE) {
                board[nextRed.row][nextRed.col] = RED;
            }

            moveRight(board, nextBlue);
            if (board[nextBlue.row][nextBlue.col] == HOLE) {
                if (board[nextRed.row][nextRed.col] != HOLE) {
                    board[nextRed.row][nextRed.col] = BLANK;
                }
                
                board[red.row][red.col] = RED;
                return;
            }

            if (board[nextRed.row][nextRed.col] == HOLE) {
                board[red.row][red.col] = RED;
                answer = Math.min(answer, count);
                return;
            }

            board[blue.row][blue.col] = BLANK;
            board[nextBlue.row][nextBlue.col] = BLUE;
        }

        up(board, nextBlue, nextRed, count + 1);
        down(board, nextBlue, nextRed, count + 1);
        left(board, nextBlue, nextRed, count + 1);
        right(board, nextBlue, nextRed, count + 1);

        board[nextBlue.row][nextBlue.col] = BLANK;
        board[blue.row][blue.col] = BLUE;
        board[nextRed.row][nextRed.col] = BLANK;
        board[red.row][red.col] = RED;
    }

    public static void moveUp(char[][] board, Position p) {
        while (true) {
            char type = board[p.row - 1][p.col];
            if (type == BLANK) {
                --p.row;
            } else if (type == HOLE) {
                --p.row;
                return;
            } else {
                return;
            }
        }
    }

    public static void moveDown(char[][] board, Position p) {
        while (true) {
            char type = board[p.row + 1][p.col];
            if (type == BLANK) {
                ++p.row;
            } else if (type == HOLE) {
                ++p.row;
                return;
            } else {
                return;
            }
        }
    }

    public static void moveLeft(char[][] board, Position p) {
        while (true) {
            char type = board[p.row][p.col - 1];
            if (type == BLANK) {
                --p.col;
            } else if (type == HOLE) {
                --p.col;
                return;
            } else {
                return;
            }
        }
    }

    public static void moveRight(char[][] board, Position p) {
        while (true) {
            char type = board[p.row][p.col + 1];
            if (type == BLANK) {
                ++p.col;
            } else if (type == HOLE) {
                ++p.col;
                return;
            } else {
                return;
            }
        }
    }
}
