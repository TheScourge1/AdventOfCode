package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise22Test {

    @Test
    public void testResult() throws Exception {
        Excercise22 test = new Excercise22();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise22Test.txt");

        int result = test.countInfectedNodeBursts(input,7);
        assertTrue("Expected 5 "+"but found " + result,result == 5 );
        result = test.countInfectedNodeBursts(input,70);
        assertTrue("Expected 40 "+"but found " + result,result == 41 );
        result = test.countInfectedNodeBursts(input,10000);
        assertTrue("Expected 5587 "+"but found " + result,result == 5587 );

        result = test.countInfectedNodeEvolvedBursts(input,100);
        assertTrue("Expected 26 "+"but found " + result,result == 26 );

        result = test.countInfectedNodeEvolvedBursts(input,10000000);
        assertTrue("Expected 26 "+"but found " + result,result == 2511944 );

     /*   result = test.getNonCollides(input);
        assertTrue("Expected send count of 1  "+"but found " + result,result == 1);*/

        test.executeTest();
    }



}
