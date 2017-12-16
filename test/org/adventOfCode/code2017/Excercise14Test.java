package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise14Test {

    @Test
    public void testResult() throws Exception {
        Excercise14 test = new Excercise14();

      //  List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise13Test.txt");


        int result = test.countUsedSquares("flqrgnkx");
        assertTrue("Expected used square count 8108 "+"but found " + result,result == 8108);

        result = test.countRegions("flqrgnkx");
        assertTrue("Expected region count 1242 "+"but found " + result,result == 1242);

        test.executeTest();
    }

}
