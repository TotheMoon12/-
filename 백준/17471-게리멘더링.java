import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int totalPopulation;
    static int answer;
    static int[] populations;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int locationNumber = Integer.parseInt(br.readLine());
        populations = new int[locationNumber + 1];
        map = new ArrayList[locationNumber + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= locationNumber; ++i) {
            populations[i] = Integer.parseInt(st.nextToken());
            map[i] = new ArrayList<>();
            totalPopulation += populations[i];
        }

        answer = totalPopulation;
        for (int i = 1; i <= locationNumber; ++i) {
            st = new StringTokenizer(br.readLine());

            st.nextToken();
            while (st.hasMoreTokens()) {
                int adjacent = Integer.parseInt(st.nextToken());

                if (!map[i].contains(adjacent)) {
                    map[i].add(adjacent);
                }

                if (!map[adjacent].contains(i)) {
                    map[adjacent].add(i);
                }
            }
        }

        answer = totalPopulation;
        for (int i = 1; i < locationNumber; ++i) {
            makeCombination(0, 1, locationNumber, i, 0);
        }

        if (answer == totalPopulation) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static void makeCombination(int bitMask, int index, final int totalLocationCount, final int locationCount, int count) {
        if (index >= totalLocationCount) {
            return;
        }

        if (count == locationCount) {
            ArrayList<Integer> area1 = new ArrayList<>();
            ArrayList<Integer> area2 = new ArrayList<>();
            int area1Population = 0;
            int area2Population = 0;

            for (int i = 1; i <= totalLocationCount; ++i) {
                if ((bitMask & (1 << (i - 1))) != 0) {
                    area1.add(i);
                    area1Population += populations[i];
                } else {
                    area2.add(i);
                    area2Population += populations[i];
                }
            }

            boolean[] area1Visited = new boolean[totalLocationCount + 1];
            boolean[] area2Visited = new boolean[totalLocationCount + 1];
            if (getConnectedCount(area1, area1Visited, area1.get(0)) == area1.size() && getConnectedCount(area2, area2Visited, area2.get(0)) == area2.size()) {
                int diff = Math.abs(area1Population - area2Population);
                if (diff < answer) {
                    answer = diff;
                }
            }

            return;
        }

        makeCombination(bitMask | (1 << (index - 1)), index + 1, totalLocationCount, locationCount, count + 1);
        makeCombination(bitMask, index + 1, totalLocationCount, locationCount, count);
    }

    public static int getConnectedCount(ArrayList<Integer> locations, boolean[] visited, int src) {
        visited[src] = true;

        int count = 1;
        for (int next : map[src]) {
            if (!visited[next] && locations.contains(next)) {
                count += getConnectedCount(locations, visited, next);
            }
        }

        return count;
    }
}

