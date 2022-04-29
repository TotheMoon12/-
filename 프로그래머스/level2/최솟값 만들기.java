// A배열에서 가장 작은 값과 B배열에서 가장 큰 값을 순서대로 빼서 곱하면 최솟값을 만들 수 있다

import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        final int LENGTH = A.length;
        for (int i = 0; i < LENGTH; ++i) {
            answer += A[i] * B[LENGTH - 1 - i];
        }       

        return answer;
    }
}
