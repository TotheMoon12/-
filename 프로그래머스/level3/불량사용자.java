import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        final char PASS = '*';

        ArrayList<String>[] casesByBannedID = new ArrayList[banned_id.length];
        for (int i = 0; i < banned_id.length; ++i) {
            casesByBannedID[i] = new ArrayList<>();
        }

        for (int i = 0; i < banned_id.length; ++i) {
            String banId = banned_id[i];
            int banIdLength = banId.length();

            for (String id : user_id) {
                int idLength = id.length();

                boolean isMatched = true;
                int index = 0;

                if (banIdLength == idLength) {
                    while (index < banIdLength) {
                        char banChar = banId.charAt(index);
                        if (banChar != id.charAt(index) && banChar != PASS) {
                            isMatched = false;
                            break;
                        }

                        ++index;
                    }

                    if (isMatched) {
                        casesByBannedID[i].add(id);
                    }
                }
            }
        }

        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        makeCombination(combinations, new ArrayList<>(), casesByBannedID, 0);

        int combinationsSize = combinations.size();

        ArrayList<ArrayList<String>> combinationsWithOutDuplicate = new ArrayList<>();
        combinationsWithOutDuplicate.add(combinations.get(0));
        for (int i = 1; i < combinationsSize; ++i) {
            ArrayList<String> list1 = combinations.get(i);

            boolean isMatched = true;
            for (int j = 0; j < combinationsWithOutDuplicate.size(); ++j) {
                ArrayList<String> compared = combinationsWithOutDuplicate.get(j);

                isMatched = true;
                for (int k = 0; k < list1.size(); ++k) {
                    if (!list1.get(k).equals(compared.get(k))) {
                        isMatched = false;
                        break;
                    }
                }

                if (isMatched) {
                    break;
                }
            }

            if (!isMatched) {
                combinationsWithOutDuplicate.add(list1);
            }
        }

        return combinationsWithOutDuplicate.size();
    }

    public void makeCombination(ArrayList<ArrayList<String>> combinations, ArrayList<String> list, ArrayList<String>[] casesByBannedID, int index) {
        if (index > casesByBannedID.length) {
            return;
        }

        if (list.size() == casesByBannedID.length) {
            Collections.sort(list);
            combinations.add(list);
            return;
        }

        for (String id : casesByBannedID[index]) {
            if (!list.contains(id)) {
                ArrayList<String> cloned = (ArrayList<String>) list.clone();
                cloned.add(id);
                makeCombination(combinations, cloned, casesByBannedID, index + 1);
            }
        }
    }
}
