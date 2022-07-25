import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        long number = 1;

        long answer = 0;
        while (S >= number) {
            ++answer;
            S -= number;
            ++number;
        }

        System.out.println(answer);
    }
}
