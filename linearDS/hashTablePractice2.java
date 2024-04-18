package linearDS_12_Practices;
// Practice2
// 정수형 배열 nums 와 target 이 주어졌을 때,
// nums 에서 임의의 두 수를 더해 target 을 구할 수 있는지 확인하는 프로그램을 작성하세요.
// 두 수 의 합으로 target 을 구할 수 있으면 해당 값의 index 를 반환하고,
// 없는 경우 null 을 반환하세요.

// 입출력 예시
// nums: 7, 11, 5, 3
// target: 10
// 출력: 0, 3

import java.util.Arrays;
import java.util.Hashtable;

// nums: 8, 3, -2
// target: 6
// 출력: 0, 2
public class Practice2 {
    public static int[] solution(int[] numbers, int target) {
        int[] result = new int[2];

        Hashtable<Integer, Integer> ht = new Hashtable<>();

        for (int i = 0; i < numbers.length; i++) {
            if (ht.containsKey(numbers[i])) {
                result[0] = ht.get(numbers[i]);
                result[1] = i;
                return result;
            }
            ht.put(target - numbers[i], i);
        }
        return  null;
    }


    public static void main(String[] args) {
        int[] nums = {7, 11, 5, 3};
        System.out.println(Arrays.toString(solution(nums, 10)));
        nums = new int[]{8, 3, -2};
        System.out.println(Arrays.toString(solution(nums, 6)));
        nums = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(solution(nums, 12)));
    }
}
