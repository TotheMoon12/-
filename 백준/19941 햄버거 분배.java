import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        ArrayList<Integer> personList = new ArrayList<>();
        ArrayList<Integer> hamburgerList = new ArrayList<>();
        for (int idx = 0; idx < N; ++idx) {
            char c = s.charAt(idx);
            if (c == 'P') {
                personList.add(idx);
            } else {
                hamburgerList.add(idx);
            }
        }

        int answer = 0;
        int personIndex = 0;
        int hamburgerIndex = 0;
        final int PERSON_NUM = personList.size();
        final int HAMBURGER_NUM = hamburgerList.size();
        while (personIndex < PERSON_NUM && hamburgerIndex < HAMBURGER_NUM) {
            int person = personList.get(personIndex);
            int hamburger = hamburgerList.get(hamburgerIndex);

            if (Math.abs(person - hamburger) <= K) {
                ++answer;
                ++personIndex;
                ++hamburgerIndex;
            } else {
                if (person > hamburger) {
                    ++hamburgerIndex;
                } else {
                    ++personIndex;
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
