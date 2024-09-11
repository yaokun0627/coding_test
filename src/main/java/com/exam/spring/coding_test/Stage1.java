package com.exam.spring.coding_test;

import java.util.Stack;
import java.util.function.Function;

/**
 * 对于只包含字母字符a-z的给定字符串，如果连续3个或更多个
 * 如果字符相同，请将其从字符串中删除。重复此过程，直到
 * *不超过3个相同的字符并排坐着
 *
 * @author: yaokun
 * @date: 2023/09/11 12:06
 */
public class Stage1 {

    /**
     * #Stage1
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical, remove them from the string. Repeat this process until
     * there is no more than 3 identical characters sitting besides each other.
     * <p>
     * Example:
     * Input: aabcccbbad
     * Output:
     * -> aabbbad
     * -> aaad
     * -> d
     */
    public static void main(String[] args) {
        String input = "aabcccbbad";

        // 方法1: Lambda包装函数实现
        String output1 = removeSequencesMethod1(input);
        // 方法2: 栈实现
        String output2 = removeSequencesMethod2(input);

        // 输出结果应为: aabbbad
        System.out.println("方法1输出: " + output1);
        System.out.println("方法2输出: " + output2);
    }


    /**
     * 方法1:
     * Lambda包装函数-通过计算比较串中连续相同字符的数量，如果数量大于3，则删除该字符
     *
     * @param s
     * @return
     */
    public static String removeSequencesMethod1(String s) {
        Function<StringBuilder, StringBuilder> removeFunction = sb -> {
            boolean hasChanged = false;
            int length = sb.length();
            int i = 0;
            // 遍历字符串，查找连续3个或更多个相同字符
            while (i < length - 2) {
                char currentChar = sb.charAt(i);
                if (currentChar == sb.charAt(i + 1) && currentChar == sb.charAt(i + 2)) {
                    // 发现了连续3个或更多个相同字符，进行删除
                    int j = i + 3;
                    while (j < length && sb.charAt(j) == currentChar) {
                        j++;
                    }
                    sb.delete(i, j);
                    length = sb.length();
                    hasChanged = true;
                    // 无需移动i指针，因为已删除字符
                } else {
                    i++;
                }
            }
            return hasChanged ? (StringBuilder) Function.identity().apply(sb) : sb;
        };
        StringBuilder sb = new StringBuilder(s);
        return removeFunction.apply(sb).toString();
    }

    /**
     * 方法2: 栈实现
     * 通过Stack来模拟栈操作，如果栈顶元素与当前字符相等，并且栈中至少有两个元素，则弹出栈顶元素，否则将当前字符压入栈中。
     *
     * @param s
     * @return
     */
    public static String removeSequencesMethod2(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> tempStack = new Stack<>();

        for (char character : s.toCharArray()) {
            // 如果栈顶元素与当前字符相等，并且栈中至少有两个元素，则弹出栈顶元素
            if (stack.size() >= 2 && stack.peek() == character && stack.get(stack.size() - 2) == character) {
                stack.pop();
                stack.pop();
                // 将剩余的元素重新压入栈
                tempStack.addAll(stack);
                // 清空原有栈
                stack.clear();
            } else {
                stack.push(character);
            }
        }
        tempStack.addAll(stack);
        StringBuilder result = new StringBuilder();
        while (!tempStack.isEmpty()) {
            result.insert(0, tempStack.pop());
        }
        return result.toString();
    }

}
