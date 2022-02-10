import java.util.ArrayList;

class Solution {
    public static final class Job {
        final int priority;
        final int location;

        public Job(final int priority, final int location) {
            this.priority = priority;
            this.location = location;
        }
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;

        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < priorities.length; ++i) {
            jobs.add(new Job(priorities[i], i));
        }

        int sequence = 0;
        while (!jobs.isEmpty()) {
            Job job = jobs.get(0);
            jobs.remove(0);

            boolean isTheMostPriority = true;
            for (int i = 0; i < jobs.size(); ++i) {
                Job other = jobs.get(i);

                if (job.priority < other.priority) {
                    isTheMostPriority = false;
                    break;
                }
            }

            if (isTheMostPriority) {
                ++sequence;

                if (job.location == location) {
                    return sequence;
                }
            } else {
                jobs.add(job);
            }
        }

        return answer;
    }
}
