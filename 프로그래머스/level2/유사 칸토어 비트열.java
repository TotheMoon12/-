class Solution {
    public int solution(int n, long l, long r) {
        int answer = getOneCount(n, l, r, 1);
        return answer;
    }

    private int getOneCount(int n, long l, long r, long startIdx) {
        final int PART_COUNT = 5;
        long length = (long) Math.pow(PART_COUNT, n);
        long prevLength = length / PART_COUNT;
        final long PART_TOTAL_ONE_COUNT = (long) Math.pow(4, n - 1);

        int count = 0;
        for (long part = 1; part <= PART_COUNT; ++part) {
            if (part == 3) {
                continue;
            }

            long start = prevLength * (part - 1) + startIdx;
            long end = start + prevLength - 1;
            if (l <= start && r >= end) {
                count += PART_TOTAL_ONE_COUNT;
            } else if (l >= start && l <= end && r > end) {
                count += getOneCount(n - 1, l, end, start);
            } else if (l < start && r >= start && r <= end) {
                count += getOneCount(n - 1, start, r, start);
            } else if (l >= start && r <= end) {
                count += getOneCount(n - 1, l, r, start);
            }
        }

        return count;
    }
}
