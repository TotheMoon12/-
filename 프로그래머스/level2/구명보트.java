import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        boolean[] visited = new boolean[people.length];
        
        int index = people.length - 1;
        for (int i = 0; i < people.length; ++i) {
            if (!visited[i]) {
                int person = people[i];
                visited[i] = true;
                ++answer;
            
                for (int j = index; j > i; --j) {
                    if (!visited[j]) {
                        int partner = people[j];
                        if (partner + person <= limit) {
                            visited[j] = true;
                            index = j - 1;
                            break;
                        }
                    }
                    --index;
                }
            }            
        }
        
        return answer;
    }
}
