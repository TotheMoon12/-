// 주어진 수를 이진수 문자열로 바꾼 뒤 부족한 길이를 앞에 0을 채워 포화이진트리로 만든다
// 본인이 0인데 자식이 1인 경우가 있는지 루트인덱스부터 시작해 탐색을 한다
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int idx = 0; idx < numbers.length; ++idx) {
            long number = numbers[idx];
            String s = Long.toBinaryString(number);
            int length = s.length();

            int depth = 1;
            while (Math.pow(2, depth) - 1 < length) {
                ++depth;
            }

            int totalLength = (int)Math.pow(2, depth) - 1;
            StringBuilder builder = new StringBuilder(totalLength);
            int extraZeroCount = totalLength - length;
            while (extraZeroCount-- > 0) {
                builder.append('0');
            }
            builder.append(s);

            if (search(builder, totalLength / 2, depth)) {
                answer[idx] = 1;
            } else {
                answer[idx] = 0;
            }
        }
      
        return answer;
    }

    private boolean search(StringBuilder tree, int index, int height) {
        if (height == 1) {
            return true;
        }

        char current = tree.charAt(index);
        int diff = (int) Math.pow(2, height - 2);
        int leftIndex = index - diff;
        int rightIndex = index + diff;

        boolean result = true;
        if (leftIndex >= 0) {
            if (current == '1' || current == '0' && tree.charAt(leftIndex) == '0') {
                result = search(tree, leftIndex, height - 1);
            } else {
                return false;
            }
        }

        if (!result) {
            return false;
        }

        if (rightIndex < tree.length()) {
            if (current == '1' || current == '0' && tree.charAt(rightIndex) == '0') {
                result = search(tree, rightIndex, height - 1);
            } else {
                return false;
            }
        }

        return result;
    }
}
