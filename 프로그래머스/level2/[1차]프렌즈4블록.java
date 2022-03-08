import java.util.HashSet;

class Solution {
    static final char BLANK = ' ';

    public static class Point {
        final int row;
        final int col;

        public Point(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
        
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || !(obj instanceof Point)) {
                return false;
            }

            Point other = (Point) obj;
            return this.row == other.row && this.col == other.col;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash ^ 13 + this.row;
            hash = hash ^ 13 + this.col;

            return hash;
        }
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] temp = new char[m][n];
        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                temp[row][col] = board[row].charAt(col);
            }
        }

        HashSet<Point> removedPointSet = getRemovedPointSet(m, n, temp);
        while (removedPointSet.size() > 0) {
            answer += removedPointSet.size();

            // remove block
            for (Point point : removedPointSet) {
                temp[point.row][point.col] = BLANK;
            }

            // drop block
            for (int col = 0; col < n; ++col) {
                for (int row = m - 1; row >= 0; --row) {
                    char c = temp[row][col];

                    if (c != BLANK) {
                        int count = 0;
                        for (int bottomRow = row + 1; bottomRow < m; ++bottomRow) {
                            if (temp[bottomRow][col] == BLANK) {
                                ++count;
                            }
                        }

                        temp[row][col] = BLANK;
                        temp[row + count][col] = c;
                    }
                }
            }

            removedPointSet = getRemovedPointSet(m, n, temp);
        }

        return answer;
    }

    public HashSet<Point> getRemovedPointSet(int m, int n, char[][] board) {
        HashSet<Point> set = new HashSet<>();
        int[] rowData = {0, 1, 1};
        int[] colData = {1, 0, 1};

        for (int row = 0; row < m; ++row) {
            for (int col = 0; col < n; ++col) {
                char c = board[row][col];

                if (c != BLANK) {
                    boolean isRemoved = true;

                    for (int i = 0; i < rowData.length; ++i) {
                        int nextRow = row + rowData[i];
                        int nextCol = col + colData[i];

                        if (nextRow >= m || nextCol >= n || board[nextRow][nextCol] != c) {
                            isRemoved = false;
                            break;
                        }
                    }

                    if (isRemoved) {
                        set.add(new Point(row, col));

                        for (int i = 0; i < rowData.length; ++i) {
                            int nextRow = row + rowData[i];
                            int nextCol = col + colData[i];

                            set.add(new Point(nextRow, nextCol));
                        }
                    }
                }
            }
        }

        return set;
    }

}
