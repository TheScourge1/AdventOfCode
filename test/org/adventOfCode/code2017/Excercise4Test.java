package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise4Test {

    @Test
    public void testResult() throws Exception {
        Excercise4 test = new Excercise4();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise4Test.txt");

        long result = test.countParafrases(input);
        assertTrue("number expect 2 "+"but found " + result,result == 2);

        input = Arrays.asList("abcde fghij","abcde xyz ecdab","a ab abc abd abf abj","iiii oiii ooii oooi oooo","oiii ioii iioi iiio");
        result = test.countAnagrams(input);
        assertTrue("steps expect 3 "+"but found " + result,result == 3);

        test.executeTest();
    }


}
