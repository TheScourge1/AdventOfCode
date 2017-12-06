package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excercise5 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise5.txt");

        long result = findExit(input);
        logger.debug("Result of excercise 5A: "+result);

        result = findExit2(input);
        logger.debug("Result of excercise 5B: "+result);

    }


    public long findExit(List<String> input)
    {
        int[] maze = loadMaze(input);
        long result = 0;
        int index = 0;

        while(index >= 0 && index < maze.length)
        {
            maze[index]++;
            index+=maze[index]-1;

            result++;
        }
        return result;
    }

    public long findExit2(List<String> input)
    {
        int[] maze = loadMaze(input);
        long result = 0;
        int index = 0;

        while(index >= 0 && index < maze.length)
        {
            int prevOffset = maze[index];
            if(maze[index] >= 3) maze[index]--;
            else maze[index]++;
            index+=prevOffset;

            result++;
        }
        return result;
    }

    private int[] loadMaze(List<String> input)
    {
        int[] maze = new int[input.size()];
        for(int i = 0; i< input.size();i++)maze[i] = Integer.parseInt(input.get(i));
        return maze;
    }
}
