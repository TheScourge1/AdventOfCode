package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;

public class Excercise13 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise13.txt");

        int result = getseverity(input);
        logger.debug("Result of excercise 13A: "+result);

        result = getRequiredDelay(input);
        logger.debug("Result of excercise 13B: "+result);

    }

    Map<Integer,Integer> firewallPositions = new HashMap<Integer,Integer>();
    Map<Integer,Integer> firewallDepths = new HashMap<Integer,Integer>();
    int lastLayer = 0;

    public int getseverity(List<String> input) {
        int result = 0;
        init(input);

        for(int i = 0; i<=lastLayer; i++) {
            if(firewallPositions.containsKey(i) && firewallPositions.get(i) == 0) result += i * firewallDepths.get(i);
            moveFirewalls();
        }

        return result;
    }

    public int getRequiredDelay(List<String> input) {
        int result = -1;
        boolean detected = true;
        init(input);

        while(detected) {
            for(int position : firewallPositions.keySet()) firewallPositions.put(position,0);
            result++;
            if(result % 100 == 0) logger.debug("at delay: "+result);
            delay(result);
            detected = false;
            for(int i = 0; i<=lastLayer; i++) {
                if (firewallPositions.containsKey(i) && firewallPositions.get(i) == 0) detected = true;
                moveFirewalls();
            }
        }
        return result;
    }

    private void init(List<String> input){
        for(String s:input) {
            String[] layer = s.split(": ");
            firewallDepths.put(Integer.parseInt(layer[0]), Integer.parseInt(layer[1]));
            firewallPositions.put(Integer.parseInt(layer[0]), 0);
            if(Integer.parseInt(layer[0]) > lastLayer) lastLayer = Integer.parseInt(layer[0]);
        }
    }

    private void moveFirewalls(){
        for(int layer : firewallDepths.keySet()) {
            if (firewallPositions.get(layer) + 1 == firewallDepths.get(layer))
                firewallPositions.put(layer, -firewallDepths.get(layer) + 2); // tracking reverse with negative values
            else firewallPositions.put(layer, firewallPositions.get(layer) + 1);
        }
    }

    private void delay(int delay){
        for(int layer : firewallDepths.keySet()){
            int depth = firewallDepths.get(layer);
            int remainderDelay = delay % (2*(depth-1));
            if(remainderDelay < depth) firewallPositions.put(layer,remainderDelay);
            else firewallPositions.put(layer,remainderDelay-2*(depth-1));
        }
    }

}

