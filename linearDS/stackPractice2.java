package linearDS

// stackPractice2
//괄호 짝 검사

// 입출력 예시
// 입력 : "("
// 출력 : Fail

// 입력 : ")"
// 출력 : Fail

// 입력 : "()"
// 출력 : Pass


import java.util.Stack;

public class Practice2 {
    public static void checkParentthesis(String str) {
        Stack stack = new Stack();
        boolean checkFlag = true;//괄호짝이 맞는지 안맞는지

        for (String s : str.split("")) {
            if (s.equals("(")) {
                stack.push(s);
            } else {
                if (stack.isEmpty()) {
                    checkFlag = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }
        if (checkFlag && stack.isEmpty()) {
            System.out.println("PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

    public static void main(String[] args) {
    //  Test code
        checkParentthesis("(");             // FAIL!
        checkParentthesis("(");             // FAIL!
        checkParentthesis("()");            // PASS!
        checkParentthesis("()()()");        // PASS!
        checkParentthesis("(())()");        // PASS!
        checkParentthesis("(((()))");       // FAIL!
    }
}
