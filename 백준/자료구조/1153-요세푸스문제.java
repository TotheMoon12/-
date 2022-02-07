// 걸린 시간 : 14분 41초
/*
 * 백준 : 1153 요세푸스 문제
 * 문제 : 1번부터 N번까지 사람들이 원을 이루며 앉아있고 양의 정수 K(<= N)가 주어진다. 이제 순서대로 K번째 사람을 제거하는데 모든 사람이
          제거될 때까지 반복한다.(사람을 제거하면 해당 위치에서 다시 K번째 사람을 제거한다)
 *       
 * 풀이 : 리스트에 1부터 주어진 수 N을 채우고 지울 인덱스를 지정할 변수 removedIndex를 사용하여 해당 인덱스의 값을 리스트에서 제거하고
          리스트가 비어있지 않다면 다시 인덱스를 구해서 값을 제거한다
 *       이 때 인덱스를 구할 때 현재 제거한 인덱스에서 K번째 인덱스를 지워야 하고 K번째 인덱스가 리스트의 사이즈를 넘어갈 수 있기 때문에 
         나머지 연산을 이용하여 계산하고 또한 나머지 연산에서 사용하는 리스트의 크기는 값을 제거할 때마다 1씩 줄어든다는 점에 주의한다
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            list.add(i + 1);
        }

        int removedIndex = (K - 1) % N;

        StringBuilder answer = new StringBuilder();
        answer.append('<');
        answer.append(list.get(removedIndex));
        list.remove(removedIndex);
        --N;

        while (!list.isEmpty()) {
            removedIndex = (removedIndex + K - 1) % N;
            answer.append(", ");
            answer.append(list.get(removedIndex));
            list.remove(removedIndex);
            --N;
        }
        answer.append('>');

        System.out.println(answer.toString());
    }
}
