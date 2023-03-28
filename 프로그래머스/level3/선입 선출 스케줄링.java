class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }

        int left = 0;
        int right = 25000000;
        int total = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int core : cores) {
                count += mid / core + 1;

                if (count > n) {
                    break;
                }
            }

            if (count >= n) {
                total = count;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int lastCore = 0;
        if (total == n) {
            for (int idx = 0; idx < cores.length; ++idx) {
                if (left % cores[idx] == 0) {
                    lastCore = idx;
                }
            }
        } else {
            int count = 0;
            for (int idx = 0; idx < cores.length; ++idx) {
                count += right / cores[idx] + 1;
            }

            int remain = n - count;
            for (int idx = 0; idx < cores.length; ++idx) {
                if (left % cores[idx] == 0) {
                    lastCore = idx;

                    if (--remain == 0) {
                        break;
                    }
                }
            }
        }

        return lastCore + 1;
    }
}
