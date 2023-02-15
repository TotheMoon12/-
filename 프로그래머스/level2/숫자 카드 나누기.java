import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        HashSet<Integer> cdASet;
        if (arrayA.length > 1) {
            cdASet = getCommonDivisorSet(arrayA[0], arrayA[1]);
        } else {
            cdASet = getDivisorSet(arrayA[0]);
        }

        for (int idx = 2; idx < arrayA.length; ++idx) {
            cdASet = getCommonDivisorSet(arrayA[idx], cdASet);
        }

        HashSet<Integer> cdBSet;
        if (arrayB.length > 1) {
            cdBSet = getCommonDivisorSet(arrayB[0], arrayB[1]);
        } else {
            cdBSet = getDivisorSet(arrayB[0]);
        }

        for (int idx = 2; idx < arrayB.length; ++idx) {
            cdBSet = getCommonDivisorSet(arrayB[idx], cdBSet);
        }

        for (int cdA : cdASet) {
            boolean isNotAllDivided = true;
            for (int number : arrayB) {
                if (number % cdA == 0) {
                    isNotAllDivided = false;
                    break;
                }
            }

            if (isNotAllDivided) {
                answer = Math.max(answer, cdA);
            }
        }

        for (int cdB : cdBSet) {
            boolean isNotAllDivided = true;
            for (int number : arrayA) {
                if (number % cdB == 0) {
                    isNotAllDivided = false;
                    break;
                }
            }

            if (isNotAllDivided) {
                answer = Math.max(answer, cdB);
            }
        }

        return answer;
    }

    private HashSet<Integer> getCommonDivisorSet(int number1, int number2) {
        HashSet<Integer> set = new HashSet<>();
        int min = Math.min(number1, number2);
        for (int divisor = 2; divisor <= min; ++divisor) {
            if (number1 % divisor == 0 && number2 % divisor == 0) {
                set.add(divisor);
            }
        }

        return set;
    }

    private HashSet<Integer> getCommonDivisorSet(int number, HashSet<Integer> set) {
        HashSet<Integer> result = new HashSet<>();
        for (int divisor : set) {
            if (number % divisor == 0) {
                result.add(divisor);
            }
        }

        return result;
    }

    private HashSet<Integer> getDivisorSet(int number) {
        HashSet<Integer> set = new HashSet<>();
        for (int divisor = 2; divisor <= number; ++divisor) {
            if (number % divisor == 0) {
                set.add(divisor);
            }
        }

        return set;
    }
}
