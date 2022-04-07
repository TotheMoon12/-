import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public class File {
        String name;
        String header;
        int number;

        public File(final String name, final String header, final int number) {
            this.name = name;
            this.header = header;
            this.number = number;
        }
    }

    public String[] solution(String[] files) {
        final int FILE_NUM = files.length;
        ArrayList<File> fileList = new ArrayList<>();
        for (String fileName : files) {
            final int LENGTH = fileName.length();
            StringBuilder header = new StringBuilder();

            int index = 0;
            for (index = 0; index < LENGTH; ++index) {
                char c = fileName.charAt(index);

                if (c >= '0' && c <= '9') {
                    break;
                } else {
                    header.append(c);
                }
            }

            StringBuilder number = new StringBuilder();
            for (; index < LENGTH; ++index) {
                char c = fileName.charAt(index);

                if (c >= '0' && c <= '9') {
                    number.append(c);
                } else {
                    break;
                }
            }

            File file = new File(fileName, header.toString().toLowerCase(), Integer.parseInt(number.toString()));
            fileList.add(file);
        }

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                int headerDiff = f1.header.compareTo(f2.header);

                if (headerDiff == 0) {
                    return f1.number - f2.number;
                } else {
                    return headerDiff;
                }
            }
        });

        String[] answer = new String[FILE_NUM];
        for (int i = 0; i < FILE_NUM; ++i) {
            answer[i] = fileList.get(i).name;
        }
        return answer;
    }
}
