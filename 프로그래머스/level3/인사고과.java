import java.util.Arrays;
import java.util.Comparator;

class Solution4 {
    public int solution(int[][] scores) {
        int answer = 1;

        final int MY_A = scores[0][0];
        final int MY_B = scores[0][1];
        final int MY_SUM = scores[0][0] + scores[0][1];

        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        int maxBScore = scores[0][1];
        int maxBScoreWithoutSameAScore = -1;
        boolean[] isExcluded = new boolean[scores.length];
        for (int idx = 1; idx < scores.length; ++idx) {
            if (scores[idx][0] < scores[idx - 1][0]) {
                maxBScoreWithoutSameAScore = maxBScore;
            }

            if (scores[idx][1] < maxBScoreWithoutSameAScore) {
                isExcluded[idx] = true;
            }

            if (isExcluded[idx] && scores[idx][0] == MY_A && scores[idx][1] == MY_B) {
                answer = -1;
                break;
            }
            maxBScore = Math.max(maxBScore, scores[idx][1]);

            if (!isExcluded[idx] && scores[idx][0] + scores[idx][1] > MY_SUM) {
                ++answer;
            }
        }

        if (answer != -1 && scores[0][0] + scores[0][1] > MY_SUM) {
            ++answer;
        }
        return answer;
    }
}
