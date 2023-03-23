class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)];
        int index = 0;

        final int MAX_DIVIDER = 10000000;
        if (begin == 1) {
            ++begin;
            ++index;
        }

        while (begin <= end) {
            answer[index] = 1;
            for (int idx = 2; idx <= MAX_DIVIDER && idx <= Math.sqrt(begin); ++idx) {
                if (begin % idx == 0) {
                    answer[index] = idx;

                    long quotient = begin / idx;
                    if (quotient <= MAX_DIVIDER) {
                        answer[index] = (int)quotient;
                        break;
                    }
                }
            }

            ++index;
            ++begin;
        }

        return answer;
    }
}
