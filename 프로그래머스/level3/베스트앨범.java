// 해시맵을 이용하여 장르별 총 재생 횟수를 저장하고
// 또한 해시맵을 이용하여 장르가 같은 음악끼리 저장한다
// 그렇게 해서 먼저 장르별 재생 횟수를 통해 내림차순으로 정렬하고
// 해당 순에서 저장해둔 음악을 재생횟수로 내림차순으로 정렬해서 각 장르별 최대 2개까지의 고유번호를 정답에 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
    public class Genre implements Comparable<Genre> {
        final String genre;
        final int playCount;

        public Genre(final String genre, final int playCount) {
            this.genre = genre;
            this.playCount = playCount;
        }


        @Override
        public int compareTo(Genre other) {
            return other.playCount - this.playCount;
        }
    }

    public class Music implements  Comparable<Music> {
        final int number;
        final int playCount;

        public Music(final int number, final int playCount) {
            this.number = number;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Music other) {
            if (this.playCount == other.playCount) {
                return this.number - other.number;
            } else if (this.playCount > other.playCount){
                return -1;
            } else {
                return 1;
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        HashMap<String, Integer> genrePlayCount = new HashMap<>();
        HashMap<String, ArrayList<Music>> listByGenre = new HashMap<>();
        for (int i = 0; i < plays.length; ++i) {
            String genre = genres[i];

            if (genrePlayCount.containsKey(genre)) {
                genrePlayCount.put(genre, genrePlayCount.get(genre) + plays[i]);
            } else {
                genrePlayCount.put(genre, plays[i]);
            }

            if (listByGenre.containsKey(genre)) {
                ArrayList<Music> list = listByGenre.get(genre);
                list.add(new Music(i, plays[i]));
            } else {
                ArrayList<Music> list = new ArrayList<>();
                list.add(new Music(i, plays[i]));
                listByGenre.put(genre, list);
            }
        }

        ArrayList<Genre> list = new ArrayList<>();
        for (String genre : genrePlayCount.keySet()) {
            list.add(new Genre(genre, genrePlayCount.get(genre)));
        }

        Collections.sort(list);
        ArrayList<Integer> temp = new ArrayList<>();
        for (Genre genre : list) {
            String type = genre.genre;

            ArrayList<Music> musicList = listByGenre.get(type);
            Collections.sort(musicList);

            for (int i = 0; i < musicList.size() && i < 2; ++i) {
                temp.add(musicList.get(i).number);
            }
        }

        answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); ++i) {
            answer[i] = temp.get(i);
        }
        return answer;
    }
}
