import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> isUsed = new HashSet<>();

        isUsed.add(words[0]);
        for (int i = 1; i < words.length; ++i) {
            String word = words[i];

            if (word.charAt(0) != words[i - 1].charAt(words[i - 1].length() - 1) || isUsed.contains(word)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                return answer;
            } else {
                isUsed.add(word);
            }
        }

        return answer;
    }
}
