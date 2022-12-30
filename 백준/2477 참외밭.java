import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int countByOne = Integer.parseInt(br.readLine());
        int[][] info = new int[6][2];

        int horizonMax = 0;
        int verticalMax = 0;
        final int DIRECTION = 0;
        final int LENGTH = 1;
        final int EAST = 1;
        final int WEST = 2;
        final int SOUTH = 3;
        final int NORTH = 4;
        for (int i = 0; i < 6; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            info[i][DIRECTION] = direction;
            info[i][LENGTH] = length;

            if (direction == EAST || direction == WEST) {
                horizonMax = Math.max(horizonMax, length);
            } else {
                verticalMax = Math.max(verticalMax, length);
            }
        }

        int side1Idx = 0;
        int side2Idx = 1;
        int side3Idx = 2;
        int side4Idx = 3;

        int area = horizonMax * verticalMax;
        for (int i = 0; i < 6; ++i) {
            if (info[side1Idx][DIRECTION] == SOUTH && info[side2Idx][DIRECTION] == EAST && info[side3Idx][DIRECTION] == SOUTH && info[side4Idx][DIRECTION] == EAST
            || info[side1Idx][DIRECTION] == EAST && info[side2Idx][DIRECTION] == NORTH && info[side3Idx][DIRECTION] == EAST && info[side4Idx][DIRECTION] == NORTH
            || info[side1Idx][DIRECTION] == WEST && info[side2Idx][DIRECTION] == SOUTH && info[side3Idx][DIRECTION] == WEST && info[side4Idx][DIRECTION] == SOUTH
            || info[side1Idx][DIRECTION] == NORTH && info[side2Idx][DIRECTION] == WEST && info[side3Idx][DIRECTION] == NORTH && info[side4Idx][DIRECTION] == WEST) {
                area -= info[side2Idx][LENGTH] * info[side3Idx][LENGTH];
                break;
            }

            ++side1Idx;
            side2Idx = (side2Idx + 1) % 6;
            side3Idx = (side3Idx + 1) % 6;
            side4Idx = (side4Idx + 1) % 6;
        }

        bw.write(String.valueOf(area * countByOne));
        br.close();
        bw.close();
    }
}
