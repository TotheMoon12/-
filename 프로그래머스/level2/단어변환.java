import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;


        Queue<String> strQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        strQueue.add(begin);
        depthQueue.add(0);

        HashMap<String, Integer> set = new HashMap<>();
        set.put(begin, 0);
        int count = 1;
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];

            if (!words[i].equals(begin) && !set.containsKey(word)) {
                set.put(word, count++);
            }
        }

        boolean[] visited = new boolean[set.size()];
        visited[0] = true;
        final int wordLength = begin.length();
        while (!strQueue.isEmpty()) {
            String search = strQueue.poll();
            int depth = depthQueue.poll();

            if (search.equals(target)) {
                return depth;
            }
            
            for (String word : set.keySet()) {
                int index = set.get(word);
                if (!visited[index]) {
                    int diffCount = 0;
                    for (int i = 0; i < wordLength; ++i) {
                        if (word.charAt(i) != search.charAt(i)) {
                            ++diffCount;
                        }
                    }

                    if (diffCount == 1) {
                        visited[index] = true;
                        strQueue.add(word);
                        depthQueue.add(depth + 1);
                    }
                }
            }
        }

        return answer;
    }
}
