import java.util.ArrayList;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        ArrayList<Double> areaList = new ArrayList<>();
        int prev = k;
        double sum = 0.0;
        while (true) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }

            double area = (prev + k) / 2.0;
            sum += area;
            areaList.add(sum);
            prev = k;
            if (k == 1) {
                break;
            }
        }

        final int LAST_X = areaList.size();
        for (int idx = 0; idx < ranges.length; ++idx) {
            int a = ranges[idx][0];
            int b = LAST_X + ranges[idx][1];

            if (a > LAST_X || a > b) {
                answer[idx] = -1.0;
            } else {
                if (a == b) {
                    answer[idx] = 0;
                } else {
                    answer[idx] = areaList.get(b - 1);
                    if (a > 0) {
                        answer[idx] -= areaList.get(a - 1);
                    }
                }
            }
        }

        return answer;
    }
}
