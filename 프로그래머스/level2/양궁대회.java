class Solution {
    static int maxDiff = -1;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        int[] ryon = new int[info.length];
        dfs(n, info, ryon, 0);

        if (maxDiff == -1) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        return answer;
    }

    public void dfs(int n, int[] info, int[] ryon, int index) {
        if (index == info.length) {
            int score = 0;
            int otherScore = 0;

            ryon[10] += n;
            for (int i = 0; i < info.length; ++i) {
                if (ryon[i] > info[i]) {
                    score += 10 - i;
                } else {
                    if (info[i] > 0) {
                        otherScore += 10 - i;
                    }
                }
            }

            int diff = score - otherScore;
            if (score > otherScore) {
                if (diff > maxDiff) {
                    maxDiff = diff;             
                    answer = ryon;
                } else if (diff == maxDiff) {
                    boolean isNew = false;
                    for (int i = info.length -1; i >= 0; --i) {
                        if (ryon[i] > answer[i]) {
                            isNew = true;
                            break;
                        } else if (ryon[i] < answer[i]) {
                            break;
                        }
                    }
                    
                    if (isNew) {
                        answer = ryon;
                    }
                }
            }

            return;
        }

        for (int i = index; i < info.length; ++i) {
            int[] ryonClone = (int[]) ryon.clone();
            if (n > info[i]) {
                ryonClone[i] = info[i] + 1;
                dfs(n - ryonClone[i], info, ryonClone, i + 1);
            } else {
                dfs(n, info, ryonClone, i + 1);
            }
        }
    }
}
