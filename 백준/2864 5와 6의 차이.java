import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String number1 = st.nextToken();
        String number2 = st.nextToken();

        int min = Integer.parseInt(number1.replaceAll("6", "5")) + Integer.parseInt(number2.replaceAll("6", "5"));
        int max = Integer.parseInt(number1.replaceAll("5", "6")) + Integer.parseInt(number2.replaceAll("5", "6"));

        System.out.print(min + " " + max);
    }
}
