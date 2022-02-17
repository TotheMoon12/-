import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>>> storage = new HashMap<>();
        String[] languages = {"java", "python", "cpp"};
        String[] jobGroups = {"backend", "frontend"};
        String[] careers = {"junior", "senior"};
        String[] foods = {"chicken", "pizza"};
        for (int i = 0; i < languages.length; ++i) {
            HashMap<String, HashMap<String, HashMap<String, ArrayList<Integer>>>> jobGroup = new HashMap<>();
            storage.put(languages[i], jobGroup);

            for (int j = 0; j < jobGroups.length; ++j) {
                HashMap<String, HashMap<String, ArrayList<Integer>>> career = new HashMap<>();
                jobGroup.put(jobGroups[j], career);

                for (int k = 0; k < careers.length; ++k) {
                    HashMap<String, ArrayList<Integer>> food = new HashMap<>();
                    career.put(careers[k], food);

                    for (int m = 0; m < foods.length; ++m) {
                        food.put(foods[m], new ArrayList<>());
                    }
                }
            }
        }

        StringTokenizer st;
        for (int i = 0; i < info.length; ++i) {
            st = new StringTokenizer(info[i]);
            String lang = st.nextToken();
            String jobGroup = st.nextToken();
            String career = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            storage.get(lang).get(jobGroup).get(career).get(food).add(score);
        }

        for (int i = 0; i < languages.length; ++i) {
            for (int j = 0; j < jobGroups.length; ++j) {
                for (int k = 0; k < careers.length; ++k) {
                    for (int m = 0; m < foods.length; ++m) {
                        ArrayList<Integer> list = storage.get(languages[i]).get(jobGroups[j]).get(careers[k]).get(foods[m]);
                        Collections.sort(list);
                    }
                }
            }
        }

        for (int i = 0; i < query.length; ++i) {
            st = new StringTokenizer(query[i]);

            ArrayList<String> langCondition = new ArrayList<>();
            ArrayList<String> jobGroupCondition = new ArrayList<>();
            ArrayList<String> careerCondition = new ArrayList<>();
            ArrayList<String> foodCondition = new ArrayList<>();

            String lang = st.nextToken();
            if (lang.equals("-")) {
                langCondition.add("java");
                langCondition.add("python");
                langCondition.add("cpp");
            } else {
                langCondition.add(lang);
            }

            st.nextToken();
            String jobGroup = st.nextToken();
            if (jobGroup.equals("-")) {
                jobGroupCondition.add("backend");
                jobGroupCondition.add("frontend");
            } else {
                jobGroupCondition.add(jobGroup);
            }

            st.nextToken();
            String career = st.nextToken();
            if (career.equals("-")) {
                careerCondition.add("junior");
                careerCondition.add("senior");
            } else {
                careerCondition.add(career);
            }

            st.nextToken();
            String food = st.nextToken();
            if (food.equals("-")) {
                foodCondition.add("chicken");
                foodCondition.add("pizza");
            } else {
                foodCondition.add(food);
            }

            int score = Integer.parseInt(st.nextToken());

            int count = 0;
            for (int j = 0; j < langCondition.size(); ++j) {
                for (int k = 0; k < jobGroupCondition.size(); ++k) {
                    for (int m = 0; m < careerCondition.size(); ++m) {
                        for (int n = 0; n < foodCondition.size(); ++n) {
                            ArrayList<Integer> list = storage.get(langCondition.get(j)).get(jobGroupCondition.get(k)).get(careerCondition.get(m)).get(foodCondition.get(n));
                            if (list.size() > 0) {
                                int left = 0;
                                int right = list.size() - 1;
                                int min = -1;
                                while (left <= right) {
                                    int mid = (left + right) / 2;

                                    if (list.get(mid) >= score) {
                                        if (min == -1) {
                                            min = mid;
                                        } else if (mid < min) {
                                            min = mid;
                                        }

                                        right = mid - 1;
                                    } else {
                                        left = mid + 1;
                                    }
                                }

                                if (min != -1) {
                                    count += list.size() - min;
                                }
                            }
                        }
                    }
                }
            }

            answer[i] = count;
        }

        return answer;
    }
}
