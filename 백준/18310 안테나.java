import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> locations = new ArrayList<>();
        for (int n = 0; n < N; ++n) {
            locations.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(locations);

        int half = N / 2;
        if (N % 2 == 0) {
            --half;
        }

        System.out.print(locations.get(half));
        br.close();
    }
}
