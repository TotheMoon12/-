public class Main {
    public static void main(String[] args) {
        boolean[] visited = new boolean[10000];

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < 10000; ++i) {
            if (!visited[i]) {
                answer.append(i);
                answer.append(System.lineSeparator());
            }

            d(i, visited);
        }

        System.out.println(answer.toString());
    }

    public static void d(int n, boolean[] visited) {
        if (n >= 10000) {
            return;
        }

        if (visited[n]) {
            return;
        }

        visited[n] = true;
        int next = n;
        int divider = 10000;
        while (divider > 0) {
            int quotient = n / divider;
            next += quotient;
            n %= divider;
            divider /= 10;
        }

        d(next, visited);
    }
}
