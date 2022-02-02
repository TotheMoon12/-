import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(int n, String[] data){
        int answer = 40320;
        char[][] cases = new char[40320][8];
        ArrayList<Character> character = new ArrayList<>(Arrays.asList('A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'));

        int count = 0;
        for (int i = 0; i < 8; ++i) {
            char num0 = character.get(i);

            ArrayList<Character> seven = (ArrayList<Character>) character.clone();
            seven.remove(i);

            for (int j = 0; j < 7; ++j) {
                char num1 = seven.get(j);

                ArrayList<Character> six = (ArrayList<Character>) seven.clone();
                six.remove(j);

                for (int k = 0; k < 6; ++k) {
                    char num2 = six.get(k);

                    ArrayList<Character> five = (ArrayList<Character>) six.clone();
                    five.remove(k);

                    for (int l = 0; l < 5; ++l) {
                        char num3 = five.get(l);

                        ArrayList<Character> four = (ArrayList<Character>) five.clone();
                        four.remove(l);

                        for (int m = 0; m < 4; ++m) {
                            char num4 = four.get(m);

                            ArrayList<Character> three = (ArrayList<Character>) four.clone();
                            three.remove(m);

                            for (int o = 0; o < 3; ++o) {
                                char num5 = three.get(o);

                                ArrayList<Character> two = (ArrayList<Character>) three.clone();
                                two.remove(o);

                                for (int p = 0; p < 2; ++p) {
                                    char num6 = two.get(p);

                                    ArrayList<Character> one = (ArrayList<Character>) two.clone();
                                    one.remove(p);

                                    for (int q = 0; q < 1; ++q) {
                                        char num7 = one.get(q);

                                        cases[count][0] = num0;
                                        cases[count][1] = num1;
                                        cases[count][2] = num2;
                                        cases[count][3] = num3;
                                        cases[count][4] = num4;
                                        cases[count][5] = num5;
                                        cases[count][6] = num6;
                                        cases[count][7] = num7;
                                        ++count;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 40320; ++i) {
            for (int j = 0; j < n; ++j) {
                char p1 = data[j].charAt(0);
                char p2 = data[j].charAt(2);
                char operator = data[j].charAt(3);
                int requestInterval = data[j].charAt(4) - 48;

                int interval = Math.abs(indexOf(p1, cases[i], 8) - indexOf(p2, cases[i], 8)) - 1;

                if (operator == '=') {
                    if (interval != requestInterval) {
                        --answer;
                        break;
                    }
                } else if (operator == '>') {
                    if (interval <= requestInterval) {
                        --answer;
                        break;
                    }
                } else if (operator == '<') {
                    if (interval >= requestInterval) {
                        --answer;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private static int indexOf(char c, char[] s, int size) {
        for (int i = 0; i < size; ++i) {
            if (s[i] == c) {
                return i;
            }
        }

        return -1;
    }
}
