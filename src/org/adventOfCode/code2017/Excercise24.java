package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;

public class Excercise24 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise24.txt");

        int result = getMaxBridgeStrenght(input);
        logger.debug("Result of excercise 24A: "+result);

        result = getLongestBridgeStrength(input);
        logger.debug("Result of excercise 24B: "+result);

    }

    public int getMaxBridgeStrenght(List<String> input)throws Exception{
        int result = 0;

        Map<Integer,List<Integer>> bridgeComponents = loadBridgeComponents(input);
        List<Integer> componentList = new ArrayList<>();
        componentList.add(0);

        Comparator<List<Integer>> bridgeComparator =
                (b1,b2) -> (b1.stream().mapToInt( i -> i).sum() >= b2.stream().mapToInt( i -> i).sum())?1:-1;

        List<Integer> resultBridge = getBridge(bridgeComponents,componentList,bridgeComparator);
        result = resultBridge.stream().mapToInt( i -> i).sum();

        return result;
    }

    public int getLongestBridgeStrength(List<String> input) throws Exception{
        int result = 0;

        Map<Integer,List<Integer>> bridgeComponents = loadBridgeComponents(input);
        List<Integer> componentList = new ArrayList<>();
        componentList.add(0);

        Comparator<List<Integer>> bridgeComparator =
                (b1,b2) -> {
                        if(b1.size() > b2.size()) return 1;
                        else if(b1.size() < b2.size()) return -1;
                        else return (b1.stream().mapToInt( i -> i).sum() >= b2.stream().mapToInt( i -> i).sum())?1:-1;
                    };

        List<Integer> resultBridge = getBridge(bridgeComponents,componentList,bridgeComparator);
        result = resultBridge.stream().mapToInt( i -> i).sum();

        return result;
    }



    private List<Integer> getBridge(Map<Integer,List<Integer>> bridgeComponents,List<Integer> currentBridgeComponents,Comparator<List<Integer>> bridgeComparator){
        List<Integer> resultBridge = currentBridgeComponents;

        int currentValue = currentBridgeComponents.get(currentBridgeComponents.size()-1);
        List<Integer> newComponents = bridgeComponents.get(currentValue);
        if(newComponents == null || newComponents.size() == 0)
            return currentBridgeComponents;
        else{
            for(int component : newComponents){
                Map<Integer,List<Integer>> reducedComponentList = new HashMap<>();
                List<Integer> newBridgeComponentList = new ArrayList<>(currentBridgeComponents);
                for(Integer key : bridgeComponents.keySet()){
                    reducedComponentList.put(key,new ArrayList(bridgeComponents.get(key)));
                }

                reducedComponentList.get(currentValue).remove(
                        reducedComponentList.get(currentValue).indexOf(component));
                if(reducedComponentList.get(currentValue).size() == 0)
                    reducedComponentList.remove(currentValue);

                reducedComponentList.get(component).remove(
                        reducedComponentList.get(component).indexOf(currentValue)); // removing inverse bridge component
                if(reducedComponentList.get(component).size() == 0) reducedComponentList.remove(component);
                newBridgeComponentList.add(currentValue);
                newBridgeComponentList.add(component);
                List<Integer> newBridge = getBridge(reducedComponentList,newBridgeComponentList,bridgeComparator);
                if(bridgeComparator.compare(resultBridge,newBridge) < 0 )
                {
                    resultBridge = newBridge;
                }
            }
        }

        return resultBridge;
    }


    private Map<Integer,List<Integer>> loadBridgeComponents(List<String> input) throws Exception{
        Map<Integer,List<Integer>> bridgeComponents = new HashMap<>();

        for(String s : input){
            String[] values = s.split("/");
            int source = Integer.parseInt(values[0]);
            int target = Integer.parseInt(values[1]);
            if(! bridgeComponents.keySet().contains(source)) bridgeComponents.put(source,new ArrayList<Integer>());
            bridgeComponents.get(source).add(target);

            // Adding all components twice!!. So remove double if needed.
            if(! bridgeComponents.keySet().contains(target)) bridgeComponents.put(target,new ArrayList<Integer>());
            bridgeComponents.get(target).add(source);
        }

        int size = bridgeComponents.values().stream().mapToInt( l -> l.size()).sum();
        if(size != 2*input.size())
            throw new Exception("Double Bridge commponent detected. Expected component count: "+input.size()*2+" but found: "+size);


        return bridgeComponents;
    }

}

