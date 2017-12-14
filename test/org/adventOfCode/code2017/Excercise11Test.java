package org.adventOfCode.code2017;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class Excercise11Test {

    @Test
    public void testResult() throws Exception {
        Excercise11 test = new Excercise11();

       // List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise9Test.txt");


        int result = test.getStepCount("ne,ne,ne");
        assertTrue("Expected number of steps 3 "+"but found " + result,result == 3);
        result = test.getStepCount("ne,ne,sw,sw");
        assertTrue("Expected number of steps 0 "+"but found " + result,result == 0);
        result = test.getStepCount("ne,ne,s,s");
        assertTrue("Expected number of steps 2 "+"but found " + result,result == 2);
        result = test.getStepCount("se,sw,se,sw,sw");
        assertTrue("Expected number of steps 3 "+"but found " + result,result == 3);

        test.executeTest();
    }

}
