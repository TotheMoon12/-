// 주어진 무한 수열규칙에서 평소처럼 1부터 N까지 dp배열을 만들려면 너무 커서 만들 수 없다
// 그리고 실제로 내가 기억할 숫자또한 N이 최대 1조이고 P=2일 때 약 40개정도이기 때문에 내가 한 번 계산한 값만
// map에 저장하여 사용하게 하는 것으로 해결

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Long, Long> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());
        long Q = Long.parseLong(st.nextToken());

        if (N == 0) {
            System.out.println(1);
        } else {
            map = new HashMap<>();
            map.put(0l, 1l);
            System.out.println(getInfinitySequence(N, P, Q));
        }
    }

    public static long getInfinitySequence(long N, long P, long Q) {
        long aip = N / P;
        long aipValue;
        if (map.containsKey(aip)) {
            aipValue = map.get(aip);
        } else {
            aipValue = getInfinitySequence(aip, P, Q);
            map.put(aip, aipValue);
        }

        long aiq = N / Q;
        long aiqValue;
        if (map.containsKey(aiq)) {
            aiqValue = map.get(aiq);
        } else {
            aiqValue = getInfinitySequence(aiq, P, Q);
            map.put(aiq, aiqValue);
        }

        return aipValue + aiqValue;
    }
}
