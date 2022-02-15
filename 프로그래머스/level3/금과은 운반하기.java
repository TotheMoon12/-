class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1l;
        long left = 0l;
        long right = 1000000000l * 200000 * 2;
        int cityNum = g.length;
        long[] wInMidTime = new long[cityNum];
        final int MINERAL_GOLD = 0;
        final int MINERAL_SILVER = 1;

        while (left <= right) {
            long mid = (left + right) / 2;

            long gold = 0l;
            long silver = 0l;
            long[][] moved = new long[cityNum][2];
            for (int i = 0; i < cityNum; ++i) {
                long transportNum = mid / (t[i] * 2);
                if (mid % (t[i] * 2) >= t[i]) {
                    ++transportNum;
                }

                wInMidTime[i] = w[i] * transportNum;

                long movedGold = Math.min(wInMidTime[i], g[i]);
                moved[i][MINERAL_GOLD] = movedGold;
                gold += movedGold;

                moved[i][MINERAL_SILVER] = Math.min(wInMidTime[i] - movedGold, s[i]);
                silver += moved[i][MINERAL_SILVER];
            }

            if (gold >= a) {
                long goldToSilver = gold - a;

                for (int i = 0; i < cityNum; ++i) {
                    long remainSilver = s[i] - moved[i][MINERAL_SILVER];

                    if (remainSilver > 0l) {
                        long movedSilver = Math.min(goldToSilver, remainSilver);
                        if (moved[i][MINERAL_GOLD] > 0l) {
                            movedSilver = Math.min(movedSilver, moved[i][MINERAL_GOLD]);
                            gold -= movedSilver;
                            silver += movedSilver;
                            goldToSilver -= movedSilver;
                        }
                    }

                    if (gold >= a && silver >= b) {
                        break;
                    }
                }

                if (gold >=a && silver >= b) {
                    if (answer == -1) {
                        answer = mid;
                    }  else {
                        answer = Math.min(answer, mid);
                    }

                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
