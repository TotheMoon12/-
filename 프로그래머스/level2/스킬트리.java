class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; ++i) {
            String skill_tree = skill_trees[i];
            
            boolean isPossible = true;
            int order = 0;
            for (int j = 0; j < skill_tree.length(); ++j) {
                char c = skill_tree.charAt(j);
                
                int index = skill.indexOf(c);
                if (index != -1) {
                    if (order == index) {
                        ++order;
                    } else {
                        isPossible = false;
                        break;
                    }
                }
            }
            
            if (isPossible) {
                ++answer;
            }
        }
        return answer;
    }
}
