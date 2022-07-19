import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int money = 1000 - Integer.parseInt(br.readLine());
        int changeCount = 0;
        int[] coin = {500, 100, 50, 10, 5, 1};
        for (int idx = 0; idx < coin.length && money > 0; ++idx) {
            changeCount += money / coin[idx];
            money %= coin[idx];
        }

        System.out.println(changeCount);
    }
}
