package linearDS;

// stackPractice3
// 후위표기법 연산
// 참고 설명) 전위/중위/후위 표기법

// 입출력 예시
// 입력 : " 2 2 +"
// 출력 : 4

// 입력 : "2 2 -"
// 출력 : 0

import java.util.Stack;

public class Practice3 {
    public static double calculate(String str) {
        // Stack stack = new Stack();
        Stack<Double> stack = new Stack();

        for (String s : str.split(" ")) {
            if (s.equals("+")) {
                // stack.pop()((double)stack.pop() + (double) stack.pop());
                // 형변환을 계속 쓰기 힘들면 Stack<double> stack로 만들면된다.
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(- stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                stack.push(1 / stack.pop() * stack.pop());
            } else {
                stack.push(Double.parseDouble(s)); // 한 글자들은 String타입이기때문에
                                                    //Double형으로 바꿔서
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
    //  Test code
        System.out.println(calculate("2 2 +"));         // 4
        System.out.println(calculate("2 2 -"));         // 0
        System.out.println(calculate("2 2 *"));         // 4
        System.out.println(calculate("2 2 /"));         // 1


        System.out.println(calculate("1 1 + 2 * 3 * 2 / 5 -"));     // 1
        System.out.println(calculate("5 2 * 3 - 8 * 4 /"));         // 14
    }
}
