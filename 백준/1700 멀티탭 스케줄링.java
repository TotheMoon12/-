import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        ArrayList<Integer>[] orderByElectronic = new ArrayList[K + 1];
        for (int k = 1; k <= K; ++k) {
            orderByElectronic[k] = new ArrayList<>();
        }

        int orderCount = 0;
        ArrayList<Integer> order = new ArrayList<>();
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());
            order.add(number);

            orderByElectronic[number].add(orderCount++);
        }

        int count = 0;
        HashSet<Integer> multiTap = new HashSet<>();
        for (int idx = 0; idx < orderCount; ++idx) {
            int number = order.get(idx);

            if (!multiTap.contains(number)) {
                if (multiTap.size() == N) {
                    int late = idx;
                    int removeNumber = -1;
                    for (int used : multiTap) {
                        if (orderByElectronic[used].size() == 0) {
                            removeNumber = used;
                            break;
                        } else {
                            if (orderByElectronic[used].get(0) > late) {
                                removeNumber = used;
                                late = orderByElectronic[used].get(0);
                            }
                        }
                    }

                    multiTap.remove(removeNumber);
                    ++count;
                }

                multiTap.add(number);
            }
            
            orderByElectronic[number].remove(0);
        }

        System.out.print(count);
    }
}
