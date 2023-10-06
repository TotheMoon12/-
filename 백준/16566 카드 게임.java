import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        boolean[] isExist = new boolean[N + 1];
        int[] minsoo = new int[M];
        int[] parent = new int[M];
        
        for (int m = 0; m < M; ++m) {
            parent[m] = m;            
            isExist[Integer.parseInt(st.nextToken())] = true;
        }

        // 다른 분 코드 참조 sort함수말고 배열로 있으면 넣게 하면 o(n)에 가능
        int minsooIndex = 0;
        for (int n = 1; n <= N; ++n) {
            if (isExist[n]) {
                minsoo[minsooIndex++] = n;
            }
        }
        
        StringBuilder builder = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; ++k) {
            int chul = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = M;

            while (left < right) {
                int mid = (left + right) / 2;
                int value = minsoo[mid];

                if (value > chul) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int index = getParent(parent, right);
            builder.append(minsoo[index]).append(System.lineSeparator());
            parent[index] = index + 1;
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        int top = getParent(parent, parent[n]);
        parent[n] = top;
        return top;
    }
}
