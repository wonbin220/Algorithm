package linearDS_14_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.stream.Stream;

public class Practice5 {
    public static ArrayList<Integer> solution(String[] gems) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        HashSet<String> set = new HashSet<>();
        Stream.of(gems).forEach(x -> set.add(x));
        int n = set.size(); // 보석 종류의 갯수

        if (n == 1) {
            result.add(new ArrayList<>(Arrays.asList(1, 1)));
            return result.get(0); //
        }

        Hashtable<String, Integer> ht = new Hashtable<>();
        boolean isCandidate = false;

        int left = 0;
        int right = 0;
        ht.put(gems[0] , 1);

        while (true) {
            if (isCandidate == false) {
                right += 1;
                if (right >= gems.length) {
                    break;
                }

                if (ht.containsKey(gems[right]) == false) {//현재 보석이 해시테이블에 들어있는지, 아닌지
                    ht.put(gems[right], 1); // 보석 추가
                } else {
                    ht.put(gems[right], ht.get(gems[right]) + 1); // 기존의 데이터를 꺼내서 1증가시켜서 다시 넣어줌
                }

                if (ht.size() == n) { // 현재 보석종류가 해시테이블에 다 있으면
                    isCandidate = true;
                    result.add(new ArrayList<>(Arrays.asList(left + 1, right + 1)));
                }
            } else {// 현재 구간에서 사면 된다고 판단 되었을때
                left += 1;
                int cnt = ht.get(gems[left - 1]) - 1;

                if (cnt == 0) {
                    ht.remove(gems[left - 1]); // 해시테이블에서 해당 보석을 뺌
                    isCandidate = false;
                } else { // cnt가 0 이 안되었으면 구매가 가능한 상태이니
                    ht.put(gems[left - 1], cnt);
                    result.add(new ArrayList<>(Arrays.asList(left + 1, right + 1)));
                }
            }
        }
        // 구간들 길이 체크(가장 짧은 구간을 찾는다)
        int minIdx = 0;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> cur = result.get(i);
            left = cur.get(0);
            right = cur.get(1);

            if (right - left < minNum) {
                minNum = right - left;
                minIdx = i;
            }
        }

        return result.get(minIdx);
    }

    public static void main(String[] args) {
        // Test code
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(solution(gems));

        gems = new String[]{"AA", "AB", "AC", "AA", "AC"};
        System.out.println(solution(gems));

        gems = new String[]{"XYZ", "XYZ", "XYZ"};
        System.out.println(solution(gems));

        gems = new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(solution(gems));
    }
}
