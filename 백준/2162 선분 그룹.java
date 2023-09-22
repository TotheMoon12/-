import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Line {
        Point p1;
        Point p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public boolean meet(Line other) {
            double result1 = ccw(this.p1, this.p2, other.p1);
            double result2 = ccw(this.p1, this.p2, other.p2);
            double result3 = ccw(other.p1, other.p2, this.p1);
            double result4 = ccw(other.p1, other.p2, this.p2);
            if (result1 * result2 < 0 && result3 * result4 < 0) {
                return true;
            } else if (result1 * result2 == 0 && result3 * result4 < 0 || result1 * result2 < 0 && result3 * result4 == 0) {
                return true;
            } else if (result1 * result2 == 0 && result3 * result4 == 0) {
                if (this.p1.x == this.p2.x) {
                    double smallY = Math.min(this.p1.y, this.p2.y);
                    double bigY = Math.max(this.p1.y, this.p2.y);
                    double smallOY = Math.min(other.p1.y, other.p2.y);
                    double bigOY = Math.max(other.p1.y, other.p2.y);

                    if (smallY >= smallOY && smallY <= bigOY || smallOY >= smallY && smallOY <= bigY) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    double smallX = Math.min(this.p1.x, this.p2.x);
                    double bigX = Math.max(this.p1.x, this.p2.x);
                    double smallOX = Math.min(other.p1.x, other.p2.x);
                    double bigOX = Math.max(other.p1.x, other.p2.x);

                    if (smallX >= smallOX && smallX <= bigOX || smallOX >= smallX && smallOX <= bigX) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        private double ccw(Point p1, Point p2, Point p3) {
            double p1p2X = p2.x - p1.x;
            double p1p2Y = p2.y - p1.y;
            double p2p3x = p3.x - p2.x;
            double p2p3Y = p3.y - p2.y;
            return p1p2X * p2p3Y - p1p2Y * p2p3x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int[] group = new int[N];
        Line[] lines = new Line[N];
        for (int n = 0; n < N; ++n) {
            group[n] = n;

            StringTokenizer st = new StringTokenizer(br.readLine());
            double x1 = Double.parseDouble(st.nextToken());
            double y1 = Double.parseDouble(st.nextToken());
            double x2 = Double.parseDouble(st.nextToken());
            double y2 = Double.parseDouble(st.nextToken());

            lines[n] = new Line(new Point(x1, y1), new Point(x2, y2));
        }

        for (int i = 0; i < N - 1; ++i) {
            Line line = lines[i];
            for (int j = i + 1; j < N; ++j) {
                Line compare = lines[j];

                if (line.meet(compare)) {
                    union(group, i, j);
                }
            }
        }

        int[] count = new int[N];
        for (int n = 0; n < N; ++n) {
            ++count[getGroup(group, n)];
        }

        int groupCount = 0;
        int maxLineNumber = 0;

        for (int n = 0; n < N; ++n) {
            if (count[n] > 0) {
                ++groupCount;
                maxLineNumber = Math.max(maxLineNumber, count[n]);
            }
        }

        bw.write(String.valueOf(groupCount));
        bw.write(System.lineSeparator());
        bw.write(String.valueOf(maxLineNumber));
        br.close();
        bw.close();
    }

    public static int getGroup(int[] group, int n) {
        if (group[n] == n) {
            return n;
        }

        return getGroup(group, group[n]);
    }

    public static void union(int[] group, int n1, int n2) {
        int group1 = getGroup(group, n1);
        int group2 = getGroup(group, n2);

        if (group1 < group2) {
            group[group2] = group1;
        } else {
            group[group1] = group2;
        }
    }
}
