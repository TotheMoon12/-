class Solution {
    public long solution(int[] sequence) {
        long answer = Math.abs(sequence[0]);
        int[] temp1 = new int[sequence.length];
        int[] temp2 = new int[sequence.length];
        int pulse1 = 1;
        int pulse2 = -1;
        for (int idx = 0; idx < sequence.length; ++idx) {
            temp1[idx] = sequence[idx] * pulse1;
            temp2[idx] = sequence[idx] * pulse2;

            pulse1 *= -1;
            pulse2 *= -1;
        }

        long sum1 = 0;
        long sum2 = 0;
        for (int idx = 0; idx < sequence.length; ++idx) {
            int number1 = temp1[idx];
            sum1 = Math.max(sum1 + number1, number1);

            int number2 = temp2[idx];
            sum2 = Math.max(sum2 + number2, number2);

            answer = Math.max(answer, sum1);
            answer = Math.max(answer, sum2);
        }

        return answer;
    }
}
