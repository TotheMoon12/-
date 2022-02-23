import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public static class Data {
        double failRate;
        int stage;

        public Data(double failRate, int stage) {
            this.failRate = failRate;
            this.stage = stage;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] numberByStage = new int[N + 2];

        for (int i = 0; i < stages.length; ++i) {
            ++numberByStage[stages[i]];
        }

        int personNum = numberByStage[N + 1];
        ArrayList<Data> datas = new ArrayList<>();
        for (int i = N; i >= 1; --i) {
            if (personNum + numberByStage[i] == 0) {
                datas.add(new Data(0, i));
            } else {
                datas.add(new Data((double) numberByStage[i] / (personNum + numberByStage[i]), i));
            }

            personNum += numberByStage[i];
        }

      // 선택 정렬
//        for (int i = 0; i < datas.size() - 1; ++i) {
//            int maxIndex = i;
//
//            for (int j = i; j < datas.size(); ++j) {
//                if (datas.get(j).failRate > datas.get(maxIndex).failRate) {
//                    maxIndex = j;
//                } else if (datas.get(j).failRate == datas.get(maxIndex).failRate) {
//                    if (datas.get(j).stage < datas.get(maxIndex).stage) {
//                        maxIndex = j;
//                    }
//                }
//            }
//
//            Data temp = datas.get(maxIndex);
//            datas.set(maxIndex, datas.get(i));
//            datas.set(i, temp);
//        }
        Collections.sort(datas, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.failRate == o2.failRate) {
                    return o1.stage - o2.stage;
                } else
                    if (o1.failRate < o2.failRate) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });


        int[] answer = new int[datas.size()];
        for (int i = 0; i < datas.size(); ++i) {
            answer[i] = datas.get(i).stage;
        }
        return answer;
    }
}
