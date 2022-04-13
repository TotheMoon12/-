class Solution {
    public int solution(int n, int[] stations, int w) {
        int coverCount = 2 * w + 1;
        int count = 0;
        int prevStation = 0;
        for (int i = 0; i < stations.length; ++i) {
            int currentStation = stations[i];
            int end = currentStation - w - 1;
            int start = prevStation + w + 1;
            if (i == 0) {
                count += getStationCount(1, end, coverCount);
            } else {
                count += getStationCount(start, end, coverCount);
            }

            prevStation = currentStation;
        }

        if (prevStation < n) {
            count += getStationCount(prevStation + w + 1, n, coverCount);
        }

        return count;
    }

    public int getStationCount(int start, int end, int coverCount) {
        int count = 0;
        int apartmentCount = end - start + 1;
        if (apartmentCount > 0) {
            int quotient = apartmentCount / coverCount;
            int remain = apartmentCount % coverCount;
            count += quotient;
            if (remain > 0) {
                ++count;
            }
        }

        return count;
    }
}
