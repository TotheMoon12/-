import java.util.HashSet;

class Solution {
    public int solution(String numbers) {
        HashSet<Integer> permutations = new HashSet<>();
        makePermutation(numbers, 0, "", permutations);

        return permutations.size();
    }

    public void makePermutation(String numbers, int bitMask, String number, HashSet<Integer> permutations) {
        int numbersLength = numbers.length();

        if (number.length() != 0) {
            int num = Integer.parseInt(number);
            if (isPrimeNumber(num)) {
                permutations.add(num);
            }
        }

        for (int i = 0; i < numbersLength; ++i) {
            if (((bitMask >> i) & 1) != 1) {
                makePermutation(numbers, bitMask | (1 << i),number + numbers.charAt(i), permutations);
            }
        }
    }

    public boolean isPrimeNumber(int number) {
        if (number == 1 || number == 0) {
            return false;
        }

        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
