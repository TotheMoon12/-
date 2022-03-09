import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static class Point {
        int row;
        int col;

        public Point(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class Puzzle {
        private ArrayList<Point> points;

        public Puzzle() {
            points = new ArrayList<>();
        }

        public void rotate() {
            for (Point point : points) {
                int row = point.row;
                int col = point.col;

                point.row = -col;
                point.col = row;
            }
        }

        public ArrayList<Point> getPoints() {
            return this.points;
        }

        public int getSize() {
            return this.points.size();
        }

        public void addPoint(Point point) {
            this.points.add(point);
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        ArrayList<Puzzle> boardPuzzles = getPuzzleListFrom(game_board, 0);
        ArrayList<Puzzle> tablePuzzles = getPuzzleListFrom(table, 1);

        int answer = 0;
        boolean[] isUsed = new boolean[tablePuzzles.size()];
        for (int i = 0; i < boardPuzzles.size(); ++i) {
            Puzzle boardPuzzle = boardPuzzles.get(i);
            final int BP_SIZE = boardPuzzle.getSize();
            ArrayList<Point> pointsBP = boardPuzzle.getPoints();

            for (int j = 0; j < tablePuzzles.size(); ++j) {
                if (isUsed[j]) {
                    continue;
                }

                Puzzle tablePuzzle = tablePuzzles.get(j);
                if (tablePuzzle.getSize() != BP_SIZE) {
                    continue;
                }

                if (isMatched(pointsBP, tablePuzzle.getPoints())) {
                    isUsed[j] = true;
                    answer += BP_SIZE;
                    break;
                }

                for (int l = 0; l < 3; ++l) {
                    tablePuzzle.rotate();

                    if (isMatched(pointsBP, tablePuzzle.getPoints())) {
                        isUsed[j] = true;
                        break;
                    }
                }

                if (isUsed[j]) {
                    answer += BP_SIZE;
                    break;
                }
            }
        }

        return answer;
    }

    boolean isMatched(ArrayList<Point> pointsBP, ArrayList<Point> pointsTP) {
        final int SIZE = pointsBP.size();

        for (int i = 0; i < SIZE; ++i) {
            ArrayList<Point> temp = new ArrayList<>();
            Point point = pointsTP.get(i);
            for (int j = 0; j < SIZE; ++j) {
                Point cur = pointsTP.get(j);
                temp.add(new Point(cur.row - point.row, cur.col - point.col));
            }

            int count = 0;
            for (int j = 0; j < SIZE; ++j) {
                Point curPoint = temp.get(j);

                for (Point compared : pointsBP) {
                    if (compared.row == curPoint.row && compared.col == curPoint.col) {
                        ++count;
                        break;
                    }
                }
            }

            if (count == SIZE) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Puzzle> getPuzzleListFrom(int[][] map, final int SPACE) {
        final int ROW_SIZE = map.length;
        final int COL_SIZE = map[0].length;
        final int[] rowData = {-1,1,0,0};
        final int[] colData = {0,0,-1,1};
        boolean[][] visited = new boolean[ROW_SIZE][COL_SIZE];
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE; ++col) {
                if (map[row][col] == SPACE && !visited[row][col]) {
                    Puzzle puzzle = new Puzzle();
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(row);
                    queue.add(col);
                    visited[row][col] = true;
                    while (!queue.isEmpty()) {
                        int curRow = queue.poll();
                        int curCol = queue.poll();

                        puzzle.addPoint(new Point(curRow - row, curCol - col));

                        for (int i = 0; i < rowData.length; ++i) {
                            int nextRow = curRow + rowData[i];
                            int nextCol = curCol + colData[i];

                            if (nextRow < 0 || nextRow >= ROW_SIZE || nextCol < 0 || nextCol >= COL_SIZE) {
                                continue;
                            }

                            if (map[nextRow][nextCol] == SPACE && !visited[nextRow][nextCol]) {
                                visited[nextRow][nextCol] = true;
                                queue.add(nextRow);
                                queue.add(nextCol);
                            }
                        }
                    }

                    puzzles.add(puzzle);
                }
            }
        }

        return puzzles;
    }
}
