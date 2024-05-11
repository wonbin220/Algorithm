package NonlinearDS_13_Practices;

import java.util.*;

public class Practice3 {
    public static ArrayList<ArrayList<String>> solution(ArrayList<ArrayList<String>> accounts) {
        //좌측 String email 노드, 우측 HashSet 인접 노드
        HashMap<String, HashSet<String>> graph = new HashMap<>();
        // email, 사용자 이름
        HashMap<String, String> name = new HashMap<>();
        
        // 그래프 구성
        for (ArrayList<String> account : accounts) {
            // 이름
            String userName = account.get(0);
            // 계정 수 만큼 돌면서
            for (int i = 1; i < account.size(); i++) {
                // 그래프에 해당 이메일 노드 없으면 추가
                if (!graph.containsKey(account.get(i))) {
                    graph.put(account.get(i), new HashSet<>());
                }
                // 계정별 사용자 이름 달기
                name.put(account.get(i), userName);

                // 첫 노드면 continue
                if (i == 1) {
                    continue;
                }
                
                // 그래프 에지 연결
                graph.get(account.get(i)).add(account.get(i - 1));
                graph.get(account.get(i - 1)).add(account.get(i));
            }
        }

        // search를 위해 visited 만듬
        HashSet<String> visited = new HashSet<>();
        // 해당 결과를 저장하기 위한 정리된 부분, email을 다시 2차원 데이터로 정리.
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        
        for (String email : name.keySet()) {
            // 해당 위치에서 serach하면서 연결된 것들을 정리하는 변수
            ArrayList<String> list = new ArrayList<>();
            // 중복 이메일은 제외
            if (visited.add(email)) {
                // 이메일 dfs 로 순회하며 연결
                dfs(graph, email, visited, list);
                // 정렬 후 사용자 이름 추가
                Collections.sort(list);

                // 누구것의 이메일인지 0번째 주소에 해당 name의 get(email)을 가져옴
                list.add(0, name.get(email));
                result.add(list);
            }
        }

        return result;
    }

    public static void dfs(HashMap<String, HashSet<String>> graph, String email, HashSet<String> visited, ArrayList<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }

    public static void main(String[] args) {
        // Test code
        ArrayList<ArrayList<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "john@mail.com", "john_lab@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "john@mail.com", "john02@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));
        accounts = solution(accounts);
        for (ArrayList<String> item: accounts) {
            System.out.println(item);
        }
    }
}
