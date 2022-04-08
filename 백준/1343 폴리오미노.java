// 그리디 알고리즘을 통해서 해결한다
// BB의 길이가 2이고 AAAA의 길이가 4이기 때문에 AAAA로 채울 수 있는 것은 BB로 채울 수 있기 때문에
// BB로 다 채울 수 없는 X의 경우는 절대로 AAAA로 만들 수 없다.
// 따라서 BB로 만들 수 있는지 길이를 통해서 확인하고 채울 수 있다면 AAAA로 최대한 먼저 채우고 BB로 채우면 된다.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String board = br.readLine();
        StringBuilder x = new StringBuilder();
        final int LENGTH = board.length();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < LENGTH; ++i) {
            char c = board.charAt(i);

            if (c == 'X') {
                x.append(c);
            } else { // c == '.'
                int xLength = x.length();

                if (xLength > 0) {
                    if (!fillPolyomino(answer, xLength)) {
                        System.out.println("-1");
                        return;
                    }

                    x.setLength(0);
                }

                answer.append(c);
            }
        }

        if (x.length() > 0) {
            if (!fillPolyomino(answer, x.length())) {
                System.out.println("-1");
                return;
            }
        }

        System.out.println(answer.toString());
    }

    public static  boolean fillPolyomino(StringBuilder answer, int xLength) {
        final String A = "AAAA";
        final int aLength = A.length();
        final String B = "BB";
        final int bLength = B.length();

        if (xLength % bLength == 0) {
            int aCount = xLength / aLength;
            int bCount = (xLength - aCount * aLength) / bLength;
            for (int count = 0; count < aCount; ++count) {
                answer.append(A);
            }

            for (int count = 0; count < bCount; ++count) {
                answer.append(B);
            }

            return true;
        } else {
            return false;
        }
    }
}
