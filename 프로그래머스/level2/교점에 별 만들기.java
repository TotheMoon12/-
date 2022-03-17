import java.util.ArrayList;

class Solution {
    public static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public String[] solution(int[][] line) {
        ArrayList<Point> points = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for (int i = 0; i < line.length; ++i) {
            double a = line[i][0];
            double b = line[i][1];
            double e = line[i][2];

            for (int j = i + 1; j < line.length; ++j) {
                double c = line[j][0];
                double d = line[j][1];
                double f = line[j][2];

                double x;
                double y;
                if (a == 0) {
                    if (c == 0) {
                        continue;
                    } else if (d == 0) {
                        x = -f / c;
                        y = -e / b;
                    } else {
                        x = ((d * e) / b - f) / c;
                        y = -e / b;
                    }
                } else if (b == 0) {
                    if (c == 0) {
                        x = -e / a;
                        y = -f / d;
                    } else if (d == 0) {
                        continue;
                    } else {
                        x = -e / a;
                        y = (-c * x - f) / d;
                    }
                } else {
                    if (c == 0) {
                        y = -f / d;
                        x = (-b * y - e) / a;
                    } else if (d == 0) {
                        x = -f / c;
                        y = (-a * x - e) / b;
                    } else {
                        x = (b * f - e * d) / (a * d - b * c);
                        y = (e * c - a * f) / (a * d - b * c);
                    }
                }

                if (x == (double) (long) (x) && y == (double) (long) (y)) {
                    Point point = new Point((long) x, (long) y);
                    points.add(point);

                    if (x > maxX) {
                        maxX = (long) x;
                    }

                    if (x < minX) {
                        minX = (long) x;
                    }

                    if (y > maxY) {
                        maxY = (long) y;
                    }

                    if (y < minY) {
                        minY = (long) y;
                    }
                }
            }
        }

        int rowSize = (int) Math.abs(maxY - minY + 1);
        int colSize = (int) Math.abs(maxX - minX + 1);
        StringBuilder[] sb = new StringBuilder[rowSize];
        for (int i = 0; i < rowSize; ++i) {
            sb[i] = new StringBuilder();

            for (int j = 0; j < colSize; ++j) {
                sb[i].append('.');
            }
        }
        String[] answer = new String[rowSize];
        for (int i = 0; i < points.size(); ++i) {
            Point point = points.get(i);

            point.x -= minX;
            point.y -= minY;
            point.y = (maxY - minY) - point.y;
            sb[(int) point.y].setCharAt((int) point.x, '*');
        }

        for (int i = 0; i < sb.length; ++i) {
            answer[i] = sb[i].toString();
        }

        return answer;
    }
}
