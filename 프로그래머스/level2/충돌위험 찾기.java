import java.util.*;

class Solution {
    public class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void up() {
            if (this.row > 0) {
                --this.row;
            }
        }

        public void down() {
            if(this.row < 100) {
                ++this.row;
            }
        }

        public void left() {
            if (this.col > 0) {
                --this.col;
            }
        }

        public void right() {
            if(this.col < 100) {
                ++this.col;
            }
        }
    }

    public class Robot {
        int number;
        Point curPoint;
        ArrayList<Point> stopovers;

        Robot(int number, Point startPoint, ArrayList<Point> stopovers) {
            this.number = number;
            this.curPoint = startPoint;
            this.stopovers = stopovers;
        }

        public Point getCurrentPoint() {
            return this.curPoint;
        }

        public void move() {
            if (stopovers.isEmpty()) {
                return;
            }

            Point stopover = stopovers.get(0);
            if (curPoint.row > stopover.row) {
                curPoint.up();
            } else if (curPoint.row < stopover.row) {
                curPoint.down();
            } else if (curPoint.col > stopover.col) {
                curPoint.left();
            } else if (curPoint.col < stopover.col){
                curPoint.right();
            }
        }

        public void arriveNext() {
            Point stopover = this.stopovers.get(0);
            if (this.curPoint.row == stopover.row && this.curPoint.col == stopover.col) {
                this.stopovers.remove(0);
            }
        }
        public boolean arriveEnd() {
            return this.stopovers.isEmpty();
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        final int ROW_SIZE = 100;
        final int COL_SIZE = 100;
        HashMap<Integer, Point> pointMap = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            int row = points[i][0];
            int col = points[i][1];

            pointMap.put(i + 1, new Point(row, col));
        }

        int[][] map = new int[ROW_SIZE + 1][COL_SIZE + 1];
        ArrayList<Robot> list = new ArrayList<>();
        for (int i = 0; i < routes.length; ++i) {
            int number = i + 1;
            Point startPoint = pointMap.get(routes[i][0]);

            ArrayList<Point> stopovers = new ArrayList<>();
            for (int j = 1; j < routes[i].length; ++j) {
                Point stopover = pointMap.get(routes[i][j]);
                stopovers.add(stopover);
            }

            list.add(new Robot(number, new Point(startPoint.row, startPoint.col), stopovers));
            ++map[startPoint.row][startPoint.col];
        }

        int count = check(map);
        while (!list.isEmpty()) {
            for (Robot robot : list) {
                Point curPoint = robot.getCurrentPoint();
                --map[curPoint.row][curPoint.col];
                robot.move();
                ++map[curPoint.row][curPoint.col];
            }

            count += check(map);
            for (int i = list.size() - 1; i >=0; --i) {
                Robot robot = list.get(i);
                Point curPoint = robot.getCurrentPoint();
                robot.arriveNext();
                if (robot.arriveEnd()) {
                    --map[curPoint.row][curPoint.col];
                    list.remove(i);
                }
            }
        }

        answer = count;
        return answer;
    }

    public int check(int[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                if (map[i][j] > 1) {
                    ++count;
                }
            }
        }

        return count;
    }
}
