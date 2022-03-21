import java.util.HashMap;

class Solution {
    public int solution(String word) {
        int answer = 0;
        HashMap<Character, Integer> data = new HashMap<>();
        data.put('A', 1);
        data.put('E', 2);
        data.put('I', 3);
        data.put('O', 4);
        data.put('U', 5);

        final int LENGTH = word.length();
        int value = 0;
        for (int i = 0; i < LENGTH; ++i) {
            char c = word.charAt(i);
            int order = data.get(c);

            if (order > 1) {
                int count = 0;

                int powerCount = 0;
                for (int j = i; j < 4; ++j) {
                    count += (order - 1) * (int)Math.pow(5, powerCount) * 5;
                    ++powerCount;
                }

                value += count;
            }

            ++value;
        }


        return value;
    }
}
