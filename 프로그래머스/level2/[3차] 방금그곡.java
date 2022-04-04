import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Solution {
    public class Music {
        int interval;
        String title;

        public Music(int startTime, int endTime, String title) {
            this.title = title;
            this.interval = endTime - startTime;
        }
    }

    public String solution(String m, String[] musicinfos) {
        ArrayList<Music> musics = new ArrayList<>();
        StringTokenizer st;
        Music answer = null;
        String lowerM = convertLowerTone(m);

        for (int i = 0; i < musicinfos.length; ++i) {
            st = new StringTokenizer(musicinfos[i], ",");
            int startTime = getMinute(st.nextToken());
            int endTime = getMinute(st.nextToken());
            if (endTime < startTime) {
                endTime = 1440;
            }

            String title = st.nextToken();
            String tones = st.nextToken();
            final int LENGTH = tones.length();

            StringBuilder sb = new StringBuilder();
            int toneIndex = 0;
            for (int time = startTime; time < endTime; ++time) {
                char tone = tones.charAt(toneIndex);
                toneIndex = (toneIndex + 1) % LENGTH;

                if (tones.charAt(toneIndex) == '#') {
                    tone = (char)(tone | 0x20);
                    toneIndex = (toneIndex + 1) % LENGTH;
                }

                sb.append(tone);
            }

            int searchIndex = sb.indexOf(lowerM);
            if (searchIndex != -1) {
                if (answer == null || endTime - startTime > answer.interval) {
                    answer = new Music(startTime, endTime, title);
                }
            }
        }

        if (answer == null) {
            return "(None)";
        }

        return answer.title;
    }

    public int getMinute(String time) {
        int minute = 0;
        StringTokenizer st = new StringTokenizer(time, ":");
        minute += Integer.parseInt(st.nextToken()) * 60;
        minute += Integer.parseInt(st.nextToken());

        return minute;
    }

    public String convertLowerTone(String tones) {
        StringBuilder sb = new StringBuilder();
        final int LENGTH = tones.length();
        for (int i = 0; i < LENGTH; ++i) {
            char tone = tones.charAt(i);
            if (tone == '#') {
                int prevIndex = sb.length() - 1;
                char prevTone = sb.charAt(prevIndex);
                sb.setCharAt(prevIndex, (char) (prevTone | 0x20));
            } else {
                sb.append(tone);
            }
        }

        return sb.toString();
    }
}
