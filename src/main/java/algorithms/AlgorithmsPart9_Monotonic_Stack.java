package algorithms;

import java.util.Arrays;
import java.util.Stack;

public class AlgorithmsPart9_Monotonic_Stack {

    class Pair {
        int val;
        int indx;

        public Pair(int val, int indx) {
            this.val = val;
            this.indx = indx;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "val=" + val +
                    ", indx=" + indx +
                    '}';
        }
    }

    public void execute() {
        /*
            Monotonic stacks are generally used for solving questions of the type - next greater element,
               next smaller element, previous greater element and previous smaller element.
         */
        int[] nums = {1,2,3,4,3};
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);

        Stack<Pair> stack = new Stack<>();
        for (int i=0; i<nums.length; i++) {
            while (!stack.isEmpty() && nums[i]>stack.peek().val) {
                Pair top = stack.pop();
                ans[top.indx] = nums[i];
            }
            stack.push(new Pair(nums[i], i));
        }

    }
}
