package org.adventOfCode.code2017;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise6Test {

    @Test
    public void testResult() throws Exception {
        Excercise6 test = new Excercise6();

        //List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise4Test.txt");

        String memory = "0 2 7 0";

        long result = test.distribute(memory);
        assertTrue("number of steps expected 5 "+"but found " + result,result == 5);

        result = test.cycleDistance(memory);
        assertTrue("number of jumps expected 4 "+"but found " + result,result == 4);

        test.executeTest();
    }


}
