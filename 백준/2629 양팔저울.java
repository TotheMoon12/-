import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int weightNum = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int weight = Integer.parseInt(st.nextToken());
        set.add(weight);
        for (int idx = 1; idx < weightNum; ++idx) {
            weight = Integer.parseInt(st.nextToken());
            HashSet<Integer> newWeights = new HashSet<>();
            for (int possibleWeight : set) {
                if (Math.abs(possibleWeight - weight) > 0) {
                    newWeights.add(Math.abs(possibleWeight - weight));
                }

                if (possibleWeight + weight <= 40000) {
                    newWeights.add(possibleWeight + weight);
                }
            }

            for (int newWeight : newWeights) {
                set.add(newWeight);
            }

            set.add(weight);
        }

        int beadNum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int idx = 0; idx < beadNum; ++idx) {
            int bead = Integer.parseInt(st.nextToken());

            if (set.contains(bead)) {
                answer.append('Y');
            } else {
                answer.append('N');
            }

            answer.append(' ');
        }

        System.out.println(answer);
    }
}
