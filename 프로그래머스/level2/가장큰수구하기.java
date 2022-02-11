// 단순하게 생각하는 것도 중요하다는 것을 알았다
// 어렵게 인덱스로 이동하면서 비교할 것 없이 두 개의 수 중 어는 것이 먼저 올지는
// 2가지 경우밖에 없기 때문에 s1 + s2, s2 + s1으로 합친 경우만 비교하면 된다

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String[] strNumbers = new String[numbers.length];
        boolean isAllZero = true;
        for (int i = 0; i < numbers.length; ++i) {
            strNumbers[i] = Integer.toString(numbers[i]);
            if (numbers[i] != 0) {
                isAllZero = false;
            }
        }

        if (isAllZero) {
            return "0";
        }

        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String number1, String number2) {
                return (number2+number1).compareTo(number1 + number2);
            }
        });

        StringBuilder answer = new StringBuilder();
        for (String number : strNumbers) {
            answer.append(number);
        }

        return answer.toString();
    }
}
