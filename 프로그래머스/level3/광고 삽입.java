// 해설을 보고 구현
// 99시간 99분 99초가 최대여서 100시간으로 봤을 때 시각이 360000개가 넘질 않는다
// 처음에 360000개면 메모리 초과가 날 것으로 생각해서 시도해보지 않았는데
// 해설을 보고 다시 생각해보니 long배열크기 360000이면 해봤자 3mb를 넘지 않는다
// 다음부터는 메모리가 정말 어느정도인지 잘 생각해보자
// 그리고 시각을 다 만들어 놓았기 때문에 처음시작 0초일 때만 광고시간범위를 다 더하고
// 다음 부터는 시작시각 - 1초의 누적을 빼고 마지막 시각만 새로 더해주면 된다
// 누적 횟수가 int범위를 넘어설 수 있는 것에 주의하자 => long을 써야 한다

import java.util.StringTokenizer;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        long[] times = new long[360001];

        StringTokenizer st;
        for (int i = 0; i < logs.length; ++i) {
            st = new StringTokenizer(logs[i], "-");
            int startTime = convertToSecond(st.nextToken());
            int endTime = convertToSecond(st.nextToken());

            for (int time = startTime; time < endTime; ++time) {
                ++times[time];
            }
        }

        int playTime = convertToSecond(play_time);
        int advTime = convertToSecond(adv_time);

        long sum = 0;
        for (int start = 0; start < advTime; ++start) {
            sum += times[start];
        }

        int answerTime = 0;
        long maxSum = sum;
        int end = playTime - advTime;
        for (int time = 1; time <= end + 1; ++time) {
            int endTime = time + advTime;
            sum -= times[time - 1];
            sum += times[endTime - 1];

            if (sum > maxSum) {
                maxSum = sum;
                answerTime = time;
            }
        }

        String answer = convertToTimeString(answerTime);
        return answer;
    }

    public static int convertToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        return hour * 60 * 60 + minute * 60 + second;
    }

    public static String convertToTimeString(int time) {
        StringBuilder sb = new StringBuilder();

        int hour = time / 60 / 60;
        time -= hour * 60 * 60;
        int minute = time / 60;
        time -= minute * 60;
        int second = time;

        if (hour < 10) {
            sb.append('0');
        }

        sb.append(hour);
        sb.append(":");

        if (minute < 10) {
            sb.append('0');
        }

        sb.append(minute);
        sb.append(":");

        if (second < 10) {
            sb.append('0');
        }
        sb.append(second);

        return sb.toString();
    }
}
