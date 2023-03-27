class Solution {
    static int answer = Integer.MAX_VALUE;

    public int solution(int[] picks, String[] minerals) {
        int[][] fatigueByPick = {
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };

        dfs(picks, minerals, 0, 0, fatigueByPick);
        return answer;
    }

    public void dfs(int[] picks, String[] minerals, int index, int fatigue, int[][] fatigueByPick) {
        if (index >= minerals.length) {
            answer = Math.min(answer, fatigue);
            return;
        }

        int pickaxCount = 0;
        for (int idx = 0; idx < picks.length; ++idx) {
            if (picks[idx] > 0) {
                ++pickaxCount;
                int count = 5;
                int nextFatigue = fatigue;
                int nextIndex = index;
                while (count-- > 0 && nextIndex < minerals.length) {
                    String mineral = minerals[nextIndex++];
                    switch (mineral) {
                        case ("diamond"):
                            nextFatigue += fatigueByPick[idx][0];
                            break;
                        case ("iron"):
                            nextFatigue += fatigueByPick[idx][1];
                            break;
                        case ("stone"):
                            nextFatigue += fatigueByPick[idx][2];
                            break;
                        default:
                            break;
                    }
                }

                --picks[idx];
                dfs(picks, minerals, nextIndex, nextFatigue, fatigueByPick);
                ++picks[idx];
            }
        }

        if (pickaxCount == 0) {
            answer = Math.min(answer, fatigue);
        }
    }
}
