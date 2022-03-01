import java.util.ArrayList;

class Solution {
    public static class Food {
        long time;
        final int index;

        public Food(long time, int index) {
            this.time = time;
            this.index = index;
        }
    }

    public int solution(int[] food_times, long k) {
        ArrayList<Food> foods = new ArrayList<>();
        for (int i = 0; i < food_times.length; ++i) {
            foods.add(new Food(food_times[i], i));
        }

        long time = k;
        while (time > 0 && !foods.isEmpty()) {
            long move = 0l;
            long count = time / foods.size();

            ArrayList<Food> temp = new ArrayList<>();
            if (count > 0) {
                for (int i = 0; i < foods.size(); ++i) {
                    Food food = foods.get(i);

                    if (food.time - count > 0) {
                        temp.add(new Food(food.time - count, food.index));
                    } else {
                        move += (count - food.time);
                    }
                }

                long processTime = count * foods.size() - move;
                time -= processTime;
                foods = temp;
            } else {
                break;
            }
        }

        if (foods.size() == 0) {
            return -1;
        } else {
            int index = 0;

           int foodCount = foods.size();

           for (long i = 0; i < time; ++i) {
               Food food = foods.get(index);
               --food.time;

               if (food.time == 0) {
                   --foodCount;
                   if (foodCount == 0) {
                       return -1;
                   }
               }
               
               ++index;
           }

            return foods.get(index).index + 1;
        }
    }
}
