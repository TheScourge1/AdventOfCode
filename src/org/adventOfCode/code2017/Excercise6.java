package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Excercise6 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise6.txt");

        long result = distribute(input.get(0));
        logger.debug("Result of excercise 6A: "+result);

        result = cycleDistance(input.get(0));
        logger.debug("Result of excercise 6B: "+result);

    }

    public long distribute(String memoryString)
    {
        List<Long> memory = Arrays.stream(memoryString.split("\\s"))
                                    .mapToLong(s -> Long.parseLong(s))
                                    .boxed()
                                    .collect(Collectors.toList());

        Map<String,Long> visitedMemoryStates = new HashMap<String,Long>();
        long result = 1;
        while(true)
        {
            long maxValue = memory.stream().max(Comparator.naturalOrder()).get();
            int index = memory.indexOf(maxValue);
            memory.set(index,0L);
            while(maxValue > 0)
            {
                index = (index + 1) % memory.size();
                memory.set(index,memory.get(index)+1);
                maxValue--;
            }
            StringBuilder b = new StringBuilder();
            memory.forEach(n -> {b.append(n);b.append(" ");});
            if(visitedMemoryStates.keySet().contains(b.toString())) return result;
            else
            {
                visitedMemoryStates.put(b.toString(),result);
                result++;
            }
        }
    }

    public long cycleDistance(String memoryString)
    {
        List<Long> memory = Arrays.stream(memoryString.split("\\s"))
                .mapToLong(s -> Long.parseLong(s))
                .boxed()
                .collect(Collectors.toList());

        Map<String,Long> visitedMemoryStates = new HashMap<String,Long>();
        long result = 1;
        while(true)
        {
            long maxValue = memory.stream().max(Comparator.naturalOrder()).get();
            int index = memory.indexOf(maxValue);
            memory.set(index,0L);
            while(maxValue > 0)
            {
                index = (index + 1) % memory.size();
                memory.set(index,memory.get(index)+1);
                maxValue--;
            }
            StringBuilder b = new StringBuilder();
            memory.forEach(n -> {b.append(n);b.append(" ");});
            if(visitedMemoryStates.keySet().contains(b.toString())) return result-visitedMemoryStates.get(b.toString());
            else
            {
                visitedMemoryStates.put(b.toString(),result);
                result++;
            }
        }
    }


}
