class Solution {

    public int solution(int[] stones, int k) {
        int answer = 200000000;
        int left = 0;
        int right = 200000000;
        while (left <= right) {
            int mid = (left + right) / 2;

            boolean isRight = false;
            int count = 0;
            for (int i = 0; i < stones.length; ++i) {
                if (stones[i] - mid <= 0) {
                    ++count;

                    if (count >= k) {
                        isRight = true;
                        break;
                    }
                } else {
                    count = 0;
                }
            }

            if (isRight) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
