import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static final int UP = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;
    static final int LEFT = 4;

    public static class Shark implements Comparable<Shark> {
        int row;
        int col;
        int speed;
        int direction;
        int z;

        public Shark(int row, int col, int speed, int direction, int z) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.z = z;
        }

        public void move(final int MAX_ROW, final int MAX_COL) {
            if (direction == UP || direction == DOWN) {
                int self = 2 * (MAX_ROW - 1);
                int count = speed % self;

                if (row == 0) {
                    direction = DOWN;
                } else if (row == MAX_ROW) {
                    direction = UP;
                }

                while (count > 0) {
                    if (direction == UP) {
                        count = up(count);
                    } else {
                        count = down(count, MAX_ROW);
                    }
                }
            } else {
                int self = 2 * (MAX_COL - 1);
                int count = speed % self;

                if (col == 0) {
                    direction = RIGHT;
                } else if (col == MAX_COL) {
                    direction = LEFT;
                }

                while (count > 0) {
                    if (direction == LEFT) {
                        count = left(count);
                    } else {
                        count = right(count, MAX_COL);
                    }
                }
            }
        }

        private int up(int count) {
            if (count >= row) {
                count -= (row - 1);
                row = 1;
                direction = DOWN;
                return count;
            } else {
                row -= count;
                return 0;
            }
        }

        private int down(int count, int MAX_ROW) {
            int downCount = MAX_ROW - row;
            if (count >= downCount) {
                row = MAX_ROW;
                direction = UP;
                return count - downCount;
            } else {
                row += count;
                return 0;
            }
        }

        private int left(int count) {
            if (count >= col) {
                count -= (col - 1);
                col = 1;
                direction = RIGHT;
                return count;
            } else {
                col -= count;
                return 0;
            }
        }

        private int right(int count, int MAX_COL) {
            int rightCount = MAX_COL - col;
            if (count > rightCount) {
                col = MAX_COL;
                direction = LEFT;
                return count - rightCount;
            } else {
                col += count;
                return 0;
            }
        }

        @Override
        public int compareTo(Shark o) {
            if (this.col == o.col) {
                if (this.row == o.row) {
                    return this.z - o.z;
                }

                return this.row - o.row;
            }

            return this.col - o.col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        ArrayList<Shark> list = new ArrayList<>();
        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            list.add(new Shark(r, c, s, d, z));
        }

        int answer = 0;
        for (int c = 1; c <= C; ++c) {
            if (list.isEmpty()) {
                break;
            }

            Collections.sort(list);
            Shark big = list.get(list.size() - 1);
            for (int i = list.size() - 2; i >= 0; --i) {
                Shark small = list.get(i);
                if (big.row == small.row && big.col == small.col) {
                    list.remove(small);
                } else {
                    big = small;
                }
            }

            int left = 0;
            int right = list.size() - 1;

            while (left < right) {
                int mid = (left + right) / 2;
                Shark shark = list.get(mid);

                if (shark.col >= c) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            Shark removeShark = list.get(right);
            if (right >= 0 && removeShark.col == c) {
                list.remove(right);
                answer += removeShark.z;
            }

            if (list.isEmpty()) {
                break;
            }

            for (Shark shark : list) {
                shark.move(R, C);
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
