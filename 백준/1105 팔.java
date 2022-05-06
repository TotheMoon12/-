// 두 숫자의 자리수가 다르면 무조건 두 수 사이의 가장 8이 적게 들어있는 수에 들어있는 8의 개수는 0개일 수 밖에 없다
// 또한 자리수가 같으면 같은 자리 숫자가 모두 8일 때 8의 개수가 증가하는데
// 맨 앞의 수부터 두 숫자가 같을 때만 뒤의 숫자를 더 확인해볼 필요가 있고
// 같은 자리수의 두 숫자가 달라지는 순간 더 이상 8이 생길 수가 없다.

// 두 숫자의 자리수가 다르면 무조건 두 수 사이의 가장 8이 적게 들어있는 수에 들어있는 8의 개수는 0개일 수 밖에 없다
// 또한 자리수가 같으면 같은 자리 숫자가 모두 8일 때 8의 개수가 증가하는데
// 맨 앞의 수부터 두 숫자가 같을 때만 뒤의 숫자를 더 확인해볼 필요가 있고
// 같은 자리수의 두 숫자가 달라지는 순간 더 이상 8이 생길 수가 없다.


// 이전 풀이에서는 숫자로 변경을 했지만
// 다른 분의 풀이를 보니 한자리씩 볼 것이기 때문에 숫자로 변환하는 과정을 할 필요없이
// 문자열로 한 자리씩 보면 된다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String L = st.nextToken();
        String R = st.nextToken();

        int answer = 0;
        final int LEFT_LENGTH = L.length();
        final int RIGHT_LENGTH = R.length();
        if (LEFT_LENGTH == RIGHT_LENGTH) {
            for (int i = 0; i < LEFT_LENGTH; ++i) {
                char leftNumber= L.charAt(i);
                char rightNumber = R.charAt(i);
                if (leftNumber == rightNumber) {
                    if (leftNumber == '8' && rightNumber == '8') {
                        ++answer;
                    }
                } else {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}




// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.StringTokenizer;

// public class Main {
//     public static void main(String[] args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int L = Integer.parseInt(st.nextToken());
//         int R = Integer.parseInt(st.nextToken());

//         ArrayList<Integer> leftNumberList = new ArrayList<>();
//         ArrayList<Integer> rightNumberList = new ArrayList<>();

//         getNumberList(L, leftNumberList);
//         getNumberList(R, rightNumberList);

//         final int LEFT_NUMBER_LIST_SIZE = leftNumberList.size();
//         final int RIGHT_NUMBER_LIST_SIZE = rightNumberList.size();

//         int answer = 0;
//         if (LEFT_NUMBER_LIST_SIZE == RIGHT_NUMBER_LIST_SIZE) {
//             for (int i = LEFT_NUMBER_LIST_SIZE - 1; i >= 0; --i) {
//                 int leftNumber = leftNumberList.get(i);
//                 int rightNumber = rightNumberList.get(i);

//                 if (leftNumber == rightNumber) {
//                     if (leftNumber == 8 && rightNumber == 8) {
//                         ++answer;
//                     }
//                 } else {
//                     break;
//                 }
//             }
//         }

//         System.out.println(answer);
//     }

//     public static void getNumberList(int number, ArrayList<Integer> outNumberList) {
//         final int TEN = 10;

//         while (true) {
//             int quotient = number / TEN;
//             int remain = number % TEN;

//             outNumberList.add(remain);
//             if (quotient > 0) {
//                 number = quotient;
//             } else {
//                 break;
//             }
//         }
//     }
// }
