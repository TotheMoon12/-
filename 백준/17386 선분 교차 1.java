import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
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

        int answer;
        if (x1 == x2) { // x = a
            if (x3 == x4) { // x = b
                answer = 0;
            } else {
                if (x1 >= x3 && x1 <= x4 || x1 >= x4 && x1 <= x3) {
                    double gradient = getGradient(x3, y3, x4, y4);
                    double yIntercept = -gradient * x3 + y3;

                    double result = gradient * x1 + yIntercept;

                    if (result >= y1 && result <= y2 || result >= y2 && result <= y1) {
                        answer = 1;
                    } else {
                        answer = 0;
                    }
                } else {
                    answer = 0;
                }
            }
        } else {
            if (x3 == x4) {
                if (x3 >= x1 && x3 <= x2 || x3 >= x2 && x3 <= x1) {
                    double gradient = getGradient(x1, y1, x2, y2);
                    double yIntercept = -gradient * x1 + y1;

                    double result = gradient * x3 + yIntercept;

                    if (result >= y3 && result <= y4 || result >= y4 && result <= y3) {
                        answer = 1;
                    } else {
                        answer = 0;
                    }
                } else {
                    answer = 0;
                }
            } else {
                double gradient1 = getGradient(x1, y1, x2, y2);
                double yIntercept1 = -gradient1 * x1 + y1;
                double gradient2 = getGradient(x3, y3, x4, y4);
                double yIntercept2 = -gradient2 * x3 + y3;

                if (gradient1 == gradient2) {
                    answer = 0;
                } else {
                    double x = (yIntercept2 - yIntercept1) / (gradient1 - gradient2);

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

                    final int OUT_OF_RANGE = -10000000;
                    int minX;
                    int maxX;
                    if (line1MaxX > line2MaxX) {
                        maxX = line2MaxX;

                        if (line1MinX <= line1MaxX) {
                            if (line1MinX <= line2MinX) {
                                minX = line2MinX;
                            } else {
                                minX = line1MinX;
                            }
                        } else {
                            minX = OUT_OF_RANGE;
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
                            minX = OUT_OF_RANGE;
                        }
                    }

                    if (minX == OUT_OF_RANGE) {
                        answer = 0;
                    } else {
                        if (x >= minX && x <= maxX) {
                            answer = 1;
                        } else {
                            answer = 0;
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static double getGradient(final int x1, final int y1, final int x2, final int y2) {
        return (y2 - y1) / (double)(x2 - x1);
    }
}
