import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        TreeMap<String, Integer> timeByCar = new TreeMap<>();
        TreeMap<String, Stack<Integer>> data = new TreeMap<>();
        for (int i = 0; i < records.length; ++i) {
            StringTokenizer st = new StringTokenizer(records[i]);
            int time = convertMinute(st.nextToken());
            String carNumber = st.nextToken();
            String type = st.nextToken();

            if (type.equals("IN")) {
                if (!data.containsKey(carNumber)) {
                    Stack<Integer> stack = new Stack<>();
                    stack.push(time);
                    data.put(carNumber, stack);
                } else {
                    data.get(carNumber).push(time);
                }
            } else {
                int inTime = data.get(carNumber).pop();
                int addTime = time - inTime;

                if (!timeByCar.containsKey(carNumber)) {
                    timeByCar.put(carNumber, addTime);
                } else {
                    timeByCar.put(carNumber, timeByCar.get(carNumber) + addTime);
                }
            }
        }

        int lastTime = convertMinute("23:59");
        for (String carNumber : data.keySet()) {
            Stack<Integer> stack = data.get(carNumber);
            if (!stack.isEmpty()) {
                int inTime = data.get(carNumber).pop();
                int addTime = lastTime - inTime;

                if (!timeByCar.containsKey(carNumber)) {
                    timeByCar.put(carNumber, addTime);
                } else {
                    timeByCar.put(carNumber, timeByCar.get(carNumber) + addTime);
                }
            }
        }

        int[] answer = new int[timeByCar.size()];
        int index = 0;
        for (String carNumber : timeByCar.keySet()) {
            int time = timeByCar.get(carNumber);

            int fee = fees[1];
            int extraTime = time - fees[0];
            if (extraTime > 0) {
                int perTime = extraTime / fees[2];
                if (extraTime % fees[2] != 0) {
                    ++perTime;
                }

                fee += perTime * fees[3];
            }


            answer[index++] = fee;
        }

        return answer;
    }

    public int convertMinute(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        return hour * 60 + minute;
    }
}
