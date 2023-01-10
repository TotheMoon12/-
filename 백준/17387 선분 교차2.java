import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static class Range {
        static final int OUT_OF_RANGE = -10000001;

        int min;
        int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int x1 = Integer.parseInt(st.nextToken());
        final int y1 = Integer.parseInt(st.nextToken());
        final int x2 = Integer.parseInt(st.nextToken());
        final int y2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        final int x3 = Integer.parseInt(st.nextToken());
        final int y3 = Integer.parseInt(st.nextToken());
        final int x4 = Integer.parseInt(st.nextToken());
        final int y4 = Integer.parseInt(st.nextToken());

        Range xRange = getRange(x1, x2, x3, x4);
        Range yRange = getRange(y1, y2, y3, y4);

        if (xRange.min == Range.OUT_OF_RANGE || yRange.min == Range.OUT_OF_RANGE) {
            bw.write("0");
        } else {
            long result1 = CCW(x1, y1, x2, y2, x3, y3);
            long result2 = CCW(x1, y1, x2, y2, x4, y4);
            long result3 = CCW(x3, y3, x4, y4, x1, y1);
            long result4 = CCW(x3, y3, x4, y4, x2, y2);

            int answer;
            if (result1 == 0 && result2 == 0) {
                answer = 1;
            } else if (result1 == 0) {
                if (x3 >= xRange.min && x3 <= xRange.max) {
                    answer = 1;
                } else {
                    answer = 0;
                }
            } else if (result2 == 0) {
                if (x4 >= xRange.min && x4 <= xRange.max) {
                    answer = 1;
                } else {
                    answer = 0;
                }
            } else if (result3 == 0 && result4 == 0) {
                answer = 1;
            } else if (result3 == 0) {
                if (x1 >= xRange.min && x1 <= xRange.max) {
                    answer = 1;
                } else {
                    answer = 0;
                }
            } else if (result4 == 0) {
                if (x2 >= xRange.min && x2 <= xRange.max) {
                    answer = 1;
                } else {
                    answer = 0;
                }
            } else {
                if (((result1 < 0 && result2 > 0) || (result1 > 0 && result2 < 0))
                && ((result3 < 0 && result4 > 0) || (result3 > 0 && result4 < 0)))  {
                    answer = 1;
                } else {
                    answer = 0;
                }
            }

            bw.write(String.valueOf(answer));
        }

        br.close();
        bw.close();
    }

    private static long CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        return x1 * y2 + x2 * y3 + x3 * y1 - (x2 * y1 + x3 * y2 + x1 * y3);
    }

    private static Range getRange(int x1, int x2, int x3, int x4) {
        int line1MinX;
        int line1MaxX;
        if (x1 > x2) {
            line1MinX = x2;
            line1MaxX = x1;
        } else {
            line1MinX = x1;
            line1MaxX = x2;
        }

        int line2MinX;
        int line2MaxX;
        if (x3 > x4) {
            line2MinX = x4;
            line2MaxX = x3;
        } else {
            line2MinX = x3;
            line2MaxX = x4;
        }

        int minX;
        int maxX;
        if (line1MaxX > line2MaxX) {
            maxX = line2MaxX;

            if (line1MinX <= line2MaxX) {
                if (line1MinX <= line2MinX) {
                    minX = line2MinX;
                } else {
                    minX = line1MinX;
                }
            } else {
                minX = Range.OUT_OF_RANGE;
            }
        } else {
            maxX = line1MaxX;

            if (line2MinX <= line1MaxX) {
                if (line2MinX <= line1MinX) {
                    minX = line1MinX;
                } else {
                    minX = line2MinX;
                }
            } else {
                minX = Range.OUT_OF_RANGE;
            }
        }

        return new Range(minX, maxX);
    }
}
