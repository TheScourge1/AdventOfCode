package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise5Test {

    @Test
    public void testResult() throws Exception {
        Excercise5 test = new Excercise5();

        //List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise4Test.txt");

        List<String>  jumps = Arrays.asList("0","3","0","1","-3");

        long result = test.findExit(jumps);
        assertTrue("number of jumps expected 5 "+"but found " + result,result == 5);

        result = test.findExit2(jumps);
        assertTrue("number of jumps expected 10 "+"but found " + result,result == 10);

        test.executeTest();
    }


}
