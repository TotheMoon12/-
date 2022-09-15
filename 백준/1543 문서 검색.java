import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String DOCUMENT = br.readLine();
        final String WORD = br.readLine();

        final int DOCUMENT_LENGTH = DOCUMENT.length();
        final int WORD_LENGTH = WORD.length();

        int answer = 0;
        for (int idx = 0; idx + WORD_LENGTH <= DOCUMENT_LENGTH; ++idx) {
            int documentIdx = idx;
            boolean match = true;

            for (int wordIdx = 0; wordIdx < WORD_LENGTH; ++wordIdx) {
                if (DOCUMENT.charAt(documentIdx) != WORD.charAt(wordIdx)) {
                    match = false;
                    break;
                }

                ++documentIdx;
            }

            if (match) {
                ++answer;
                idx += WORD_LENGTH - 1;
            }
        }

        System.out.print(answer);
        br.close();
    }
}
