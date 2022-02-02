import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public static boolean isAlphabet(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<String> makeMultipleSet(String str) {
        ArrayList<String> multipleSet = new ArrayList<>();

        for (int i = 1; i < str.length(); ++i) {
            char c1 = str.charAt(i - 1);
            char c2 = str.charAt(i);

            if (!isAlphabet(c1)) {
                continue;
            }

            if (!isAlphabet(c2)) {
                ++i;
                continue;
            }

            StringBuilder part = new StringBuilder();
            part.append(Character.toUpperCase(c1));
            part.append(Character.toUpperCase(c2));
            multipleSet.add(part.toString());
        }

        return multipleSet;
    }

    public int solution(String str1, String str2) {
        int answer = 0;
        final int CONSTANT = 65536;

        // if (str1.length() == 0 && str2.length() == 0) {
        //     return CONSTANT;
        // } else if (str1.length() == 0 || str2.length() == 0) {
        //     return 0;
        // }

        ArrayList<String> str1MS = makeMultipleSet(str1);
        ArrayList<String> str2MS = makeMultipleSet(str2);

        int str1MSSize = str1MS.size();
        int str2MSSize = str2MS.size();
        if (str1MSSize == 0 && str2MSSize == 0) {
            return CONSTANT;
        } else if (str1MSSize == 0 || str2MSSize == 0) {
            return 0;
        }

        Collections.sort(str1MS);
        Collections.sort(str2MS);

        int index1 = 0;
        int index2 = 0;
        double commonCount = 0;
        double sumCount = 0;

        while (index1 < str1MSSize && index2 < str2MSSize) {
            int diff = str1MS.get(index1).compareTo(str2MS.get(index2));
            if (diff == 0) {
                ++commonCount;
                ++index1;
                ++index2;
            } else if (diff < 0) {
                ++index1;
            } else {
                ++index2;
            }

            ++sumCount;
        }

        if (index1 < str1MSSize) {
            sumCount += str1MSSize - index1;
        }

        if (index2 < str2MSSize) {
            sumCount += str2MSSize - index2;
        }

        answer = (int) ((commonCount / sumCount) * CONSTANT);
        return answer;
    }
}
