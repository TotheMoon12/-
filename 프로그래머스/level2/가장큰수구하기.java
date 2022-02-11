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
