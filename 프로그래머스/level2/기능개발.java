import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < progresses.length; ++i) {
            int remainWork = 100 - progresses[i];


            if (remainWork > 0) {
                int day;

                if (remainWork % speeds[i] == 0) {
                    day = remainWork / speeds[i];
                } else {
                    day = remainWork / speeds[i] + 1;
                }

                int count = 1;
                progresses[i] += speeds[i] * day;
                boolean prevClear = true;

                for (int j = i + 1; j < progresses.length; ++j) {
                    progresses[j] += speeds[j] * day;

                    if (!prevClear) {
                        continue;
                    }

                    if (progresses[j] >= 100) {
                        ++count;
                        i = j;
                    } else {
                        prevClear= false;
                    }
                }

                result.add(count);
            }
        }

        answer = new int[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
