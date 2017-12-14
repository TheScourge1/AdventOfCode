package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise12Test {

    @Test
    public void testResult() throws Exception {
        Excercise12 test = new Excercise12();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise12Test.txt");


        int result = test.programCountInGroup(input,0);
        assertTrue("Expected number of programs 6 "+"but found " + result,result == 6);

        result = test.countGroups(input);
        assertTrue("Expected number of groups 2 "+"but found " + result,result == 2);

        test.executeTest();
    }

}
