package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise2Test {

    @Test
    public void testResult() throws Exception {
        Excercise2 test = new Excercise2();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise2Test.txt");

        long result = test.calculateChecksum(input);
        assertTrue("checksum expect 18 "+"but found " + result,result == 18);

        input = FileUtil.readStringsFromFile("resource/2017/Excercise2BTest.txt");
        result = test.calculateEvenChecksum(input);
        assertTrue("checksum expect 9 "+"but found " + result,result == 9);

        test.executeTest();
    }


}
