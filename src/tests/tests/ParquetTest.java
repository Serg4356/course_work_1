package tests;

import org.junit.*;
import parquet.Parquet;

/**
 * Created by Пользователь on 13.11.2016.
 */
public class ParquetTest {
    @org.junit.Test
    public void acceptCut() throws Exception {
        //Assert.assertEquals(true, Parquet.acceptCut(33,18,6));
        //Assert.assertEquals(false, Parquet.acceptCut(0,0,5));
        Assert.assertEquals(true, Parquet.acceptCut(1,0,5));
    }

    @org.junit.Test
    public void countValues() throws Exception {
        long[] correctArr = {1,1,2,3,5,8,13,21,34};
        Parquet a = new Parquet();
        a.countValues(2,8);
        Assert.assertArrayEquals(correctArr,a.arr[0]);
        

    }

    @org.junit.Test
    public void main() throws Exception {

    }

    public static void main(String[] args) throws Exception {


    }

}