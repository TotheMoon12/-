import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static class Truck {
        private final int weight;
        private int location;

        public Truck(final int weight) {
            this.weight = weight;
            this.location = 1;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int weightOnBridge = 0;

        int index = 0;
        ArrayList<Truck> bridge = new ArrayList<>();
        while (!bridge.isEmpty() || index < truck_weights.length) {
            while (index < truck_weights.length && weightOnBridge + truck_weights[index] <= weight && bridge.size() < bridge_length) {
                if (!bridge.isEmpty()) {
                    Truck truck = bridge.get(0);

                    if (truck.location == bridge_length) {
                        break;
                    }
                }

                for (Truck truck : bridge) {
                    ++truck.location;
                }

                bridge.add(new Truck(truck_weights[index]));
                weightOnBridge += truck_weights[index++];
                ++answer;
            }

            if (!bridge.isEmpty()) {
                Truck firstTruck = bridge.get(0);
                if (firstTruck.location == bridge_length) {
                    bridge.remove(0);
                    weightOnBridge -= firstTruck.weight;

                    for (Truck truck : bridge) {
                        ++truck.location;
                    }

                    if (index < truck_weights.length && weightOnBridge + truck_weights[index] <= weight) {
                        bridge.add(new Truck(truck_weights[index]));
                        weightOnBridge += truck_weights[index++];
                    }

                    ++answer;
                } else if (firstTruck.location < bridge_length) {
                    int remainLength = bridge_length - firstTruck.location;
                    for (Truck truck : bridge) {
                        truck.location += remainLength;
                    }

                    answer += remainLength;
                }
            }
        }

        return answer;
    }
}
