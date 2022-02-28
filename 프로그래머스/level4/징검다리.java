// 알고리즘 스터디원의 풀이에 따라 품
import java.util.Arrays;

class Solution4 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);

        int left = 0;
        int right = distance;

        while (left <= right) {
            int mid = (left + right) / 2;
            int rockIndex = 0;
            int start = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = rockIndex; j < rocks.length; ++j) {
                    if (rocks[j] - start < mid) {
                        rockIndex = j + 1;
                        break;
                    } else {
                        start = rocks[j];
                        rockIndex = j + 1;
                    }
                }
            }

            boolean isPossible = true;
            for (; rockIndex < rocks.length; ++rockIndex) {
                if (rocks[rockIndex] - start < mid) {
                    isPossible = false;
                    break;
                } else {
                    start = rocks[rockIndex];
                }
            }

            // 도착지와 마지막 start 거리 비교
            if (rockIndex == rocks.length) {
                if (distance - start < mid) {
                    isPossible = false;
                }
            }

            if (isPossible) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
}
