package com.example.humbhenri.dedris;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by humbhenri on 15/10/17.
 */
public class ArrayUtilsTest {

    @Test
    public void removeColuna() {
        int array[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayUtils.removeColuna(array, 2);
        Assert.assertArrayEquals(new int[]{0, 1, 2}, array[0]);
        Assert.assertArrayEquals(new int[]{0, 4, 5}, array[1]);
        Assert.assertArrayEquals(new int[]{0, 7, 8}, array[2]);
    }
}