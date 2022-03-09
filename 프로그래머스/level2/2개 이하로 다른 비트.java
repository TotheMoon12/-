class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        int index = 0;
        for (long number : numbers) {
            int location = 0;
            for (int shift = 0; shift < 64; ++shift) {
                if (((number >> shift) & 1l) == 0) {
                    location = shift;
                    number |= (1l << shift);
                    break;
                }
            }

            if (location > 0) {
                number &= ~(1l << (location - 1));
            }

            answer[index++] = number;
        }

        return answer;
    }
} 
