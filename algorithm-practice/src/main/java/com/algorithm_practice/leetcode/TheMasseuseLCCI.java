package com.algorithm_practice.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @program: algorithm-practice
 * @description: 面试题 17.16. 按摩师
 * @author: urbane
 * @create: 2020-03-24 10:40
 **/
@Slf4j
public class TheMasseuseLCCI {

    @Test
    public void testMassage(){
        int[] i = {1,2,3,5,8,9};
        int massage = massage(i);
        log.info("\n\nmassage:{}",massage);
    }


    // 空间O(n)
    public  int massage(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }


    public int massage1(int[] nums) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return b;
    }


}
