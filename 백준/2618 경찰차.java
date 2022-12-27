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

        final int N = Integer.parseInt(br.readLine());
        final int W = Integer.parseInt(br.readLine());

        int[][] incidents = new int[W + 1][2];
        final int X = 0;
        final int Y = 1;
        for (int w = 1; w <= W; ++w) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            incidents[w][X] = Integer.parseInt(st.nextToken());
            incidents[w][Y] = Integer.parseInt(st.nextToken());
        }


        int[][] dp = new int[W + 1][W + 1];
        int[][][] path = new int[W + 1][W + 1][2];

        dp[0][1] = N - incidents[1][X] + N - incidents[1][Y];
        dp[1][0] = incidents[1][X] + incidents[1][Y] - 2;

        final int PREV_ONE = 0;
        final int PREV_TWO = 1;

        for (int one = 0; one <= W; ++one) {
            for (int two = 0; two <= W; ++two) {
                if (one == 0 && two == 0 || one == 1 && two == 0 || one == 0 && two ==1) {
                    continue;
                }

                dp[one][two] = Integer.MAX_VALUE;
                if (one < two) {
                    path[one][two][PREV_ONE] = one;

                    if (two - one > 1) {
                        int prev = two - 1;
                        int distance;
                        if (prev == 0) {
                            distance = dp[one][prev] + N - incidents[two][X] + N - incidents[two][Y];

                        } else {
                            distance = dp[one][prev] + Math.abs(incidents[two][X] - incidents[prev][X]);
                            distance +=  Math.abs(incidents[two][Y] - incidents[prev][Y]);
                        }

                        dp[one][two] = distance;
                        path[one][two][PREV_TWO] = prev;
                    } else {
                        for (int prev = one - 1; prev >= 0; --prev) {
                            int distance;

                            if (prev == 0) {
                                distance = dp[one][prev] + N - incidents[two][X] + N - incidents[two][Y];
                            } else {
                                distance = dp[one][prev] + Math.abs(incidents[two][X] - incidents[prev][X]);
                                distance +=  Math.abs(incidents[two][Y] - incidents[prev][Y]);
                            }

                            if (distance < dp[one][two]) {
                                dp[one][two] = distance;
                                path[one][two][PREV_TWO] = prev;
                            }
                        }
                    }
                } else if (one > two) {
                    path[one][two][PREV_TWO] = two;

                    if (one - two > 1) {
                        int prev = one - 1;
                        int distance;

                        if (prev == 0) {
                            distance = dp[prev][two] + incidents[one][X] + incidents[one][Y] - 2;
                        } else {
                            distance = dp[prev][two] + Math.abs(incidents[one][X] - incidents[prev][X]);
                            distance +=  Math.abs(incidents[one][Y] - incidents[prev][Y]);
                        }

                        dp[one][two] = distance;
                        path[one][two][PREV_ONE] = prev;
                    } else {
                        for (int prev = two - 1; prev >= 0; --prev) {
                            int distance;

                            if (prev == 0) {
                                distance = dp[prev][two] + incidents[one][X] + incidents[one][Y] - 2;
                            } else {
                                distance = dp[prev][two] + Math.abs(incidents[one][X] - incidents[prev][X]);
                                distance +=  Math.abs(incidents[one][Y] - incidents[prev][Y]);
                            }

                            if (distance < dp[one][two]) {
                                dp[one][two] = distance;
                                path[one][two][PREV_ONE] = prev;
                            }
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        int answerOne = 0;
        int answerTwo = 3;
        for (int idx = 0; idx < W; ++idx) {
            if (dp[idx][W] < answer) {
                answer = dp[idx][W];
                answerOne = idx;
                answerTwo = W;
            }

            if (dp[W][idx] < answer) {
                answer = dp[W][idx];
                answerOne = W;
                answerTwo = idx;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(answer);
        builder.append(System.lineSeparator());

        int[] order = new int[W + 1];
        int idx = W;
        while (answerOne > 0 || answerTwo > 0) {
            if (answerOne > answerTwo) {
                order[idx--] = 1;
            } else {
                order[idx--] = 2;
            }

            int prevOne = path[answerOne][answerTwo][PREV_ONE];
            int prevTwo = path[answerOne][answerTwo][PREV_TWO];
            answerOne = prevOne;
            answerTwo = prevTwo;
        }

        for (int w = 1; w <= W; ++w) {
            builder.append(order[w]);
            builder.append(System.lineSeparator());
        }
        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
