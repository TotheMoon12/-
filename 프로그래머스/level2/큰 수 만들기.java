// k개의 수를 제거했을 때 가장 큰 수를 만들어야 하므로
// 현재 위치를 기준으로 뒤에 k개 중에 나보다 큰 수가 있으면 나를 넣지 않으면 된다
// 단 나를 넣지 않을 때마다 k를 줄여줘야 한다
// 스택을 이용해서도 풀 수 있다

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int count = 0;

        for (int i = 0; i < number.length(); ++i) {
            char digit = number.charAt(i);

            boolean isMax = true;
            for (int j = i + 1; j < number.length() && j <= i + k - count; ++j) {
                char nextDigit = number.charAt(j);

                if (digit < nextDigit) {
                    isMax = false;
                    break;
                }
            }

            if (isMax) {
                answer.append(digit);
            } else {
                ++count;
            }
        }

        while (count < k) {
            answer.deleteCharAt(answer.length() - 1);
            ++count;
        }

        return answer.toString();
    }
}
