import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(String[][] relation) {
        int attributeCount = relation[0].length;
        int tupleCount = relation.length;

        ArrayList<Integer> notCandidate = new ArrayList<>();
        for (int i = 0; i < attributeCount; ++i) {
            notCandidate.add(i);
        }

        HashSet<Integer> candidateList = new HashSet<>();
        for (int i = 1; i <= attributeCount; ++i) {
            findCandidate(relation, i, 0,0,0, candidateList);
        }

        return candidateList.size();
    }

    public static void findCandidate(String[][] relation, final int combSize, int bitMask, int count, int index, HashSet<Integer> candidateList) {
        if (count == combSize) {
            int attributeCount = relation[0].length;
            int tupleCount = relation.length;
            HashSet<String> set = new HashSet<>();

            ArrayList<Integer> attributeIndexList = new ArrayList<>();
            for (int i = 0; i < attributeCount; ++i) {
                if (((bitMask >> i) & 1) == 1) {
                    attributeIndexList.add(i);
                }
            }

            for (int candidate : candidateList) {
                if ((bitMask & candidate) == candidate) {
                    return;
                }
            }

            int attributeIndexListSize = attributeIndexList.size();
            for (int row = 0; row < tupleCount; ++row) {
                StringBuilder key = new StringBuilder();
                for (int col = 0; col < attributeIndexListSize; ++col) {
                    key.append(relation[row][attributeIndexList.get(col)]);
                    key.append(" ");
                }

                set.add(key.toString());
            }

            if (set.size() == tupleCount) {
                candidateList.add(bitMask);
            }

            return;
        }

        for (int i = index; i < relation[0].length; ++i) {
            findCandidate(relation, combSize, bitMask | (1 << i), count + 1, index + 1, candidateList);
        }
    }
}
