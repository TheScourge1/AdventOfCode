package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Excercise3 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise3.txt");

        long result = calculateSteps(277678);
        logger.debug("Result of excercise 3A: "+result);

        result = getStressTestValue(277678);
        logger.debug("Result of excercise 3B: "+result);

    }

    public long calculateSteps(int input)
    {
        long result = 0;

        Coord coord = this.getCoord(input);


        result = Math.abs(coord.x)+ Math.abs(coord.y);
        return result;
    }


    private long getSquareSize(long input)
    {
        int n = 0;
        int sum = 1;
        while(sum < input) sum += 8*(++n);

        return n;
    }

    private long getMaxSquareValue(long squareSize)
    {
        int result = 1;
        for(int i = 1; i <= squareSize; i++) result +=i*8;
        return result;
    }

    private Coord getCoord(long input)
    {
        long squareSize = getSquareSize(input);
        long maxSquareValue = getMaxSquareValue(squareSize);
        long diff = maxSquareValue - input;

        long x, y = 0;
        if(diff >= 0 && diff < 2* squareSize) // lower bound
        {
            x = squareSize-diff;
            y = -squareSize;
        }
        else if( diff >= 2* squareSize && diff < 4* squareSize) // left bound
        {
            x = -squareSize;
            y = diff -3*squareSize;
        }
        else if(diff >= 4* squareSize && diff < 6*squareSize) // upper bound
        {
            x = diff - 5*squareSize;
            y = squareSize;
        }
        else // rightbound
        {
            x = squareSize;
            y = 7*squareSize-diff;
        }

        return new Coord((int)x,(int)y);
    }

    public long getStressTestValue(long input)
    {
        final int OFFSET = 50;
        long[][] grid = new long[OFFSET*2][OFFSET*2];

        long maxValue = 1;
        int index = 1;
        grid[OFFSET][OFFSET] = 1;

        while(maxValue < input)
        {
            index++;
            Coord c = getCoord(index);
            long newValue = 0;
            for(int i = -1; i <=1; i++)
            {
                for(int j = -1; j<=1;j++)
                {
                    if( i == 0 && j == 0) continue;
                    newValue += grid[OFFSET+c.x+i][OFFSET+c.y+j];
                }
            }

            if(maxValue < newValue) maxValue = newValue;
          //  logger.debug("i: "+index + "\t value:"+newValue+ "\tc("+c.x+","+c.y+")");
            grid[OFFSET+c.x][OFFSET+c.y]=newValue;
        }

        return maxValue;
    }

    private class Coord
    {
        int x,y = 0;

        private Coord(){};
        private Coord(int x,int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
