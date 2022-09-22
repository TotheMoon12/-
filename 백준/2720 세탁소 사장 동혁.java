import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;
        final int PENNY = 1;

        StringBuilder builder = new StringBuilder();
        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            int cent = Integer.parseInt(br.readLine());

            builder.append(cent / QUARTER);
            builder.append(" ");
            cent %= QUARTER;

            builder.append(cent / DIME);
            builder.append(" ");
            cent %= DIME;

            builder.append(cent / NICKEL);
            builder.append(" ");
            cent %= NICKEL;

            builder.append(cent);
            builder.append(System.lineSeparator());
        }

        System.out.print(builder);
        br.close();
    }
}
