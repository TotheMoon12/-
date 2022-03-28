import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    public class Info {
        int index;
        int score;
        int outLinkCount;
        ArrayList<String> inLinks;

        public Info(int index) {
            this.index = index;
            inLinks = new ArrayList<>();
        }

        public void addLink(String link) {
            this.inLinks.add(link);
        }
    }

    public int solution(String word, String[] pages) {
        double maxScore = 0;
        int index = 0;
        word = word.toLowerCase();

        final String MY_URL_OPEN_TAG = "<meta property=\"og:url\" content=\"";
        final String MY_URL_CLOSE_TAG = "\"/>";
        final String BODY_OPEN_TAG = "<body>";
        final String BODY_CLOSE_TAG = "</body>";
        final String LINK_OPEN_TAG = "<a href=\"";
        final String LINK_CLOSE_TAG = "\">";

        HashMap<String, Info> map = new HashMap<>();
        for (int i = 0; i < pages.length; ++i) {
            String page = pages[i];
            Info info = new Info(i);

            // getURL
            int urlStart = page.indexOf(MY_URL_OPEN_TAG) + MY_URL_OPEN_TAG.length();
            int urlEnd = page.indexOf(MY_URL_CLOSE_TAG, urlStart);
            String url = page.substring(urlStart, urlEnd);

            map.put(url, info);
        }

        for (String url : map.keySet()) {
            Info info = map.get(url);
            String page = pages[info.index];

            // getBody
            int bodyStart = page.indexOf(BODY_OPEN_TAG) + BODY_OPEN_TAG.length();
            int bodyEnd = page.indexOf(BODY_CLOSE_TAG);
            String body = page.substring(bodyStart, bodyEnd).toLowerCase();

            // getLink
            int linkStart = body.indexOf(LINK_OPEN_TAG, 0);
            while (linkStart != -1) {
                linkStart += LINK_OPEN_TAG.length();
                int linkEnd = body.indexOf(LINK_CLOSE_TAG, linkStart);
                String link = body.substring(linkStart, linkEnd);
                if (map.get(link) != null) {
                    map.get(link).addLink(url);
                }
                ++info.outLinkCount;
                linkStart = body.indexOf(LINK_OPEN_TAG, linkEnd + LINK_CLOSE_TAG.length());
            }

            int score = 0;
            ArrayList<String> texts = new ArrayList<>();
            boolean isOpen = false;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < body.length(); ++j) {
                char c = body.charAt(j);

                if (c == '<') {
                    isOpen = true;

                    if (sb.length() > 0) {
                        texts.add(sb.toString());
                        sb.setLength(0);
                    }
                } else if (c == '>') {
                    if (isOpen) {
                        isOpen = false;
                    } else {
                        sb.append(c);
                    }
                } else {
                    if (!isOpen) {
                        sb.append(c);
                    }
                }
            }

            if (sb.length() > 0) {
                texts.add(sb.toString());
            }

            for (int j = 0; j < texts.size(); ++j) {
                String text = texts.get(j);
                String[] words = text.split("[^a-z]");

                for (String compared : words) {
                    if (compared.equals(word)) {
                        ++score;
                    }
                }
            }

            info.score = score;
            map.put(url, info);
        }

        for (String url : map.keySet()) {
            Info info = map.get(url);
            ArrayList<String> links = info.inLinks;

            double score = info.score;
            for (String link : links) {
                Info linkPageInfo = map.get(link);

                if (linkPageInfo != null) {
                    score += linkPageInfo.score / (double) linkPageInfo.outLinkCount;
                }

            }

            if (score > maxScore) {
                maxScore = score;
                index = info.index;
            } else if (score == maxScore) {
                if (info.index < index) {
                    index = info.index;
                }
            }
        }

        return index;
    }
}
