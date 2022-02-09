import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<Character, ArrayList<String>> dictionary = new HashMap<>();
        ArrayList<String> lastIndexStringList = new ArrayList<>();
      
        for (int i = 0; i < phone_book.length; ++i) {
            String number = phone_book[i];
            char firstNumber = number.charAt(0);
            if (number.length() == 1) {
                lastIndexStringList.add(number);
            }

            if (!dictionary.containsKey(firstNumber)) {
                dictionary.put(firstNumber, new ArrayList<>());
            }

            ArrayList<String> list = dictionary.get(firstNumber);
            list.add(number);
        }

        for (String number : lastIndexStringList) {
            char indexNumber = number.charAt(0);

            if (dictionary.get(indexNumber).size() > 1) {
                return false;
            }
        }

        return isExisted(dictionary, phone_book, 1);
    }

    public boolean isExisted(HashMap<Character, ArrayList<String>> dictionary, String[] phone_book, int index) {
        boolean answer = true;

        for (Character key : dictionary.keySet()) {
            ArrayList<String> list = dictionary.get(key);

            if (list.size() == 1) {
                continue;
            }

            HashMap<Character, ArrayList<String>> radixDictionary = new HashMap<>();
            ArrayList<String> lastIndexStringList = new ArrayList<>();
            for (String number : list) {
                if (index == number.length() - 1) {
                    lastIndexStringList.add(number);
                } else if (index >= number.length()) {
                    continue;
                }

                char indexNumber = number.charAt(index);
                if (!radixDictionary.containsKey(indexNumber)) {
                    radixDictionary.put(indexNumber, new ArrayList<>());
                }

                ArrayList<String> listByIndexNumber = radixDictionary.get(indexNumber);
                listByIndexNumber.add(number);
            }

            for (String number : lastIndexStringList) {
                char indexNumber= number.charAt(index);

                if (radixDictionary.get(indexNumber).size() > 1) {
                    return false;
                }
            }

            answer = isExisted(radixDictionary, phone_book, index + 1);
            if (!answer) {
                return false;
            }
        }

        return true;
    }
}
