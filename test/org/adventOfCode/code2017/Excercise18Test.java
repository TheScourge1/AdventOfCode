package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise18Test {

    @Test
    public void testResult() throws Exception {
        Excercise18 test = new Excercise18();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise18Test.txt");


        int result = test.getLastFrequency(input);
        assertTrue("Expected frequency 4   "+"but found " + result,result == 4 );

        input = Arrays.asList("snd 1","snd 2","snd p","rcv a","rcv b","rcv c","rcv d");

        result = test.getSendCount(input);
        assertTrue("Expected send cound of 3  "+"but found " + result,result == 3);

        test.executeTest();
    }



}
