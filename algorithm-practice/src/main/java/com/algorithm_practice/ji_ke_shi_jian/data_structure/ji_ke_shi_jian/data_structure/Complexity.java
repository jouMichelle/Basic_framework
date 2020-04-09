package com.algorithm_practice.ji_ke_shi_jian.data_structure.ji_ke_shi_jian.data_structure;

import org.junit.Test;

/**
 * @program: algorithm-practice
 * @description: 复杂度
 * @author: urbane
 * @create: 2020-03-25 15:27
 **/

public class Complexity {

    int cal(int n) {
        int sum = 0;
        int i = 1;
        for (; i <= n; ++i) {
            sum = sum + i;
        }
        return sum;
    }

    @Test
    public void testComplexity(){
        int cal = cal(10);
        System.out.println(cal);
    }
}
