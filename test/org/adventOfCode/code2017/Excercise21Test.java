package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise21Test {

    @Test
    public void testResult() throws Exception {
        Excercise21 test = new Excercise21();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise21Test.txt");

        int result = test.getPixelCount(input,2);
        assertTrue("Expected lid pixels 12 "+"but found " + result,result == 12 );


     /*   result = test.getNonCollides(input);
        assertTrue("Expected send count of 1  "+"but found " + result,result == 1);*/

        test.executeTest();
    }



}
