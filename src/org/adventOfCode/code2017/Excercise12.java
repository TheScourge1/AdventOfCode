package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Pattern;

public class Excercise12 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise12.txt");

        int result = programCountInGroup(input,0);
        logger.debug("Result of excercise 12A: "+result);

       result = countGroups(input);
        logger.debug("Result of excercise 12B: "+result);

    }

    public int programCountInGroup(List<String> input,int group) {
        Map<Integer, List<Integer>> programIds = processInput(input);

        Set<Integer> resultSet = getIdsInGroup(programIds, 0);
        return resultSet.size();
    }


    public int countGroups(List<String> input) {
        Map<Integer, List<Integer>> programIds = processInput(input);
        int groupCount = 0;

        while(programIds.keySet().size() > 0){
            groupCount++;
            Set<Integer> resultSet = getIdsInGroup(programIds, programIds.keySet().iterator().next());
            resultSet.stream().forEach( r -> programIds.remove(r));
        }
        return groupCount;
    }

    private Set<Integer> getIdsInGroup(Map<Integer,List<Integer>> programIds,int group) {
        Set<Integer> resultSet = new HashSet<Integer>();
        Stack<Integer> idsToProcess = new Stack<Integer>();
        idsToProcess.add(group);
        while(idsToProcess.size() > 0) {
            int id = idsToProcess.pop();
            List<Integer> linkedIds = programIds.get(id);
            resultSet.add(id);
            linkedIds.stream().forEach( linkedId -> {
                if(!resultSet.contains(linkedId)) idsToProcess.add(linkedId);
            });
        }

        return resultSet;
    }

    private Map<Integer,List<Integer>> processInput(List<String> input){
        Map<Integer,List<Integer>> programIds = new HashMap<Integer,List<Integer>>();
        for(String s : input)
        {
            List<Integer> linkList = new ArrayList<Integer>();
            String[] idList = s.split(" ");
            Integer id = Integer.parseInt(idList[0]);
            for(int i = 2; i<idList.length;i++){
                linkList.add(Integer.parseInt(idList[i].replace(",","")));
            }
            programIds.put(id,linkList);
        }

        return programIds;
    }



}

