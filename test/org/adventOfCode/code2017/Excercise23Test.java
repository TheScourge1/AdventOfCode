package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise23Test {

    @Test
    public void testResult() throws Exception {
        Excercise23 test = new Excercise23();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise23Test.txt");

      /*  int result = test.countMulInstructions(input);
        assertTrue("Expected 5 "+"but found " + result,result == 5 );*/


   /*     result = test.countInfectedNodeEvolvedBursts(input,10000000);
        assertTrue("Expected 26 "+"but found " + result,result == 2511944 );*/

     /*   result = test.getNonCollides(input);
        assertTrue("Expected send count of 1  "+"but found " + result,result == 1);*/

        test.executeTest();
    }



}
