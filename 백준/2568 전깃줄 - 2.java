import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int[][] lines = new int[N][2];
        ArrayList<Integer>[] numberInfo = new ArrayList[N];
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            lines[n][0] = n1;
            lines[n][1] = n2;

            numberInfo[n] = new ArrayList<>();
        }

        Arrays.sort(lines, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        ArrayList<Integer> list = new ArrayList<>(N);
        list.add(lines[0][0]);
        numberInfo[0].add(lines[0][0]);
        for (int n = 1; n < N; ++n) {
            int a = lines[n][0];

            if (a > list.get(list.size() - 1)) {
                numberInfo[list.size()].add(a);
                list.add(a);
            } else {
                int left = 0;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    int value = list.get(mid);

                    if (value >= a) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                list.set(right, a);
                numberInfo[right].add(a);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(N - list.size()).append(System.lineSeparator());
        int next = list.get(list.size() - 1);
        ArrayList<Integer> removeList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; --i) {
            ArrayList<Integer> info = numberInfo[i];

            boolean first = true;
            for (int index = 0; index < info.size(); ++index) {
                int number = info.get(index);
                if (number < next) {
                    if (first) {
                        first = false;
                        next = number;
                    } else {
                        removeList.add(number);
                    }
                } else if (number > next) {
                    removeList.add(number);
                }
            }
        }

        Collections.sort(removeList);
        for (int number : removeList) {
            builder.append(number).append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
