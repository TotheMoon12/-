class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int[] discounts = new int[emoticons.length];
        dfs(discounts, 0, users, emoticons, answer);
        return answer;
    }

    private void dfs(int[] discounts, int idx, int[][] users, int[] emoticons, int[] answer) {
        if (idx == discounts.length) {
            int count = 0;
            int total = 0;

            for (int[] user : users) {
                int userDiscount = user[0];
                int criteria = user[1];

                int sum = 0;
                for (int emoticonIdx = 0; emoticonIdx < emoticons.length; ++emoticonIdx) {
                    if (discounts[emoticonIdx] >= userDiscount) {
                        sum += emoticons[emoticonIdx] * (100 - discounts[emoticonIdx]) / 100;
                    }
                }

                if (sum >= criteria) {
                    ++count;
                } else {
                    total += sum;
                }
            }

            if (count > answer[0]) {
                answer[0] = count;
                answer[1] = total;
            } else if (count == answer[0] && total > answer[1]) {
                answer[1] = total;
            }

            return;
        }

        int next = idx + 1;
        discounts[idx] = 10;
        dfs(discounts, next,users,emoticons,answer);
        discounts[idx] = 20;
        dfs(discounts, next,users,emoticons,answer);
        discounts[idx] = 30;
        dfs(discounts, next,users,emoticons,answer);
        discounts[idx] = 40;
        dfs(discounts, next,users,emoticons,answer);
    }
}
