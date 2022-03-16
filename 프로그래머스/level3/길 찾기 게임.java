import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {

        final int X = 0;
        final int Y = 1;
        HashMap<Integer, Integer> numberMap = new HashMap<>();
        for (int i = 0; i < nodeinfo.length; ++i) {
            numberMap.put(nodeinfo[i][X], i + 1);
        }

        ArrayList<int[]> sortedNodeList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; ++i) {
            sortedNodeList.add(nodeinfo[i]);
        }

        Collections.sort(sortedNodeList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[Y] == o2[Y]) {
                    if (o1[X] < o2[X]) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (o1[Y] > o2[Y]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });


        // preorder
        ArrayList<Integer> preorderList = new ArrayList<>();
        searchPreorder(sortedNodeList, preorderList, numberMap);

        // postorder
        ArrayList<Integer> postorderList = new ArrayList<>();
        searchPostorder(sortedNodeList, postorderList, numberMap);

        int[][] answer = new int[2][nodeinfo.length];
        int index = 0;
        for (int number : preorderList) {
            answer[0][index++] = number;
        }

        index = 0;
        for (int number : postorderList) {
            answer[1][index++] = number;
        }

        return answer;
    }

    public void searchPreorder(ArrayList<int[]> sortedNodeList, ArrayList<Integer> list, HashMap<Integer, Integer> numberMap) {
        int x = sortedNodeList.get(0)[0];
        list.add(numberMap.get(x));

        ArrayList<int[]> leftList = new ArrayList<>();
        ArrayList<int[]> rightList = new ArrayList<>();

        for (int i = 1; i < sortedNodeList.size(); ++i) {
            if (sortedNodeList.get(i)[0] < x) {
                leftList.add(sortedNodeList.get(i));
            } else {
                rightList.add(sortedNodeList.get(i));
            }
        }

        if (leftList.size() > 0) {
            searchPreorder(leftList, list, numberMap);
        }

        if (rightList.size() > 0) {
            searchPreorder(rightList, list, numberMap);
        }
    }

    public void searchPostorder(ArrayList<int[]> sortedNodeList, ArrayList<Integer> list, HashMap<Integer, Integer> numberMap) {
        int x = sortedNodeList.get(0)[0];

        ArrayList<int[]> leftList = new ArrayList<>();
        ArrayList<int[]> rightList = new ArrayList<>();

        for (int i = 1; i < sortedNodeList.size(); ++i) {
            if (sortedNodeList.get(i)[0] < x) {
                leftList.add(sortedNodeList.get(i));
            } else {
                rightList.add(sortedNodeList.get(i));
            }
        }

        if (leftList.size() > 0) {
            searchPostorder(leftList, list, numberMap);
        }

        if (rightList.size() > 0) {
            searchPostorder(rightList, list, numberMap);
        }

        list.add(numberMap.get(x));
    }
}
