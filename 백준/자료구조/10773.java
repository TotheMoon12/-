/*
 * 백준 : 10773
 * 문제 : 주어지는 정수들의 개수 K가 먼저 주어지고 K개의 정수들이 차례로 주어질 때,
 *       정수가 0이면 이전 숫자를 지우고 아니면 더해서 최종적으로 합해진 수를 구하라
 * 풀이 : 정수가 0인 경우가 연속으로 나올 수 있기 때문에 이전에 나온 숫자들을 저장을 해두어야 차례대로 지울 수 있다
 *       제일 최근에 넣은 숫자부터 지워야 하기 때문에 자료구조 스택을 사용하여 문제를 해결한다
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int sum = 0;
        Stack<Integer> prevNumberStack = new Stack<>();

        for (int i = 0; i < K; ++i) {
            int number = Integer.parseInt(br.readLine());

            if (number != 0) {
                sum += number;
                prevNumberStack.push(number);
            } else {
                int prevNumber = 0;
                if (!prevNumberStack.empty()) {
                    prevNumber = prevNumberStack.pop();
                }

                sum -= prevNumber;
            }
        }

        System.out.println(sum);
    }
}
