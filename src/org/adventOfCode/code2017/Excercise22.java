package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Excercise22 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise22.txt");

        int result = countInfectedNodeBursts(input,10000);
        logger.debug("Result of excercise 22A: "+result);

        result = countInfectedNodeEvolvedBursts(input,10000000);
        logger.debug("Result of excercise 22B: "+result);

    }


    public int countInfectedNodeBursts(List<String> input,int bursts) throws Exception{
        int result = 0;

        Map<Node,NodeStatus> matrix = new HashMap<>();
        Cursor cursor = new Cursor();
        populateMatrix(input,matrix);

        for(int i = 0; i < bursts; i++){
            Node prevLocation = cursor.location;
            executeBurst(matrix,cursor);
            if(matrix.get(prevLocation) == NodeStatus.I) result++;
        }

        printMatrix(matrix);
        return result;
    }


    public int countInfectedNodeEvolvedBursts(List<String> input,int bursts) throws Exception{
        int result = 0;

        Map<Node,NodeStatus> matrix = new HashMap<>();
        Cursor cursor = new Cursor();
        populateMatrix(input,matrix);

        for(int i = 0; i < bursts; i++){
            Node prevLocation = cursor.location;
            executeEvolvedBurst(matrix,cursor);
            if(matrix.get(prevLocation) == NodeStatus.I) result++;
        }

        printMatrix(matrix);
        return result;
    }


    private void populateMatrix(List<String> input,Map<Node,NodeStatus> matrix) throws Exception{
        if(input.size() != input.get(0).length()) throw new Exception("Currently only square grids implemented");
        if(input.size() %2 != 1) throw new Exception("Currently only odd grids implemented");

        int startIndex = -input.size()/2;

        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.size(); j++ ){
                Node coord = new Node(i+startIndex,j+startIndex);
                NodeStatus result = NodeStatus.C;
                if(input.get(i).substring(j,j+1).equals("#")) result = NodeStatus.I;
                matrix.put(coord,result);
            }
        }
    }

    private void executeBurst(Map<Node,NodeStatus> matrix,Cursor cursor){
        if(matrix.get(cursor.location) != null && matrix.get(cursor.location) == NodeStatus.I){
            matrix.put(cursor.location,NodeStatus.C);
            cursor.turnRight();
            cursor.moveForeward();
        }
        else{
            matrix.put(cursor.location,NodeStatus.I);
            cursor.turnLeft();
            cursor.moveForeward();
        }
    }

    private void executeEvolvedBurst(Map<Node,NodeStatus> matrix,Cursor cursor){
        if(matrix.get(cursor.location) == null || matrix.get(cursor.location) == NodeStatus.C){
            matrix.put(cursor.location,NodeStatus.W);
            cursor.turnLeft();
        }
        else if(matrix.get(cursor.location) == NodeStatus.W){
            matrix.put(cursor.location,NodeStatus.I);
        }
        else if(matrix.get(cursor.location) == NodeStatus.I){
            matrix.put(cursor.location,NodeStatus.F);
            cursor.turnRight();
        }
        else{
            matrix.put(cursor.location,NodeStatus.C);
            cursor.turnRight();
            cursor.turnRight();
        }
        cursor.moveForeward();
    }

    private void printMatrix(Map<Node,NodeStatus> matrix){
        int gridSize = 10;
        for(int i = -gridSize/2; i < gridSize; i++){
            StringBuilder b = new StringBuilder();
            for(int j = -gridSize/2; j < gridSize; j++) {
                if (matrix.get(new Node(i, j)) != null && matrix.get(new Node(i, j)) == NodeStatus.I) b.append("# ");
                else b.append(". ");
            }
       //     logger.debug(b.toString());
        }
    }

    private enum Orientation  { N, S , E , W};
    private enum NodeStatus  { W, I , F , C};

    private class Cursor{

        Node location = new Node(0L,0L);
        Orientation direction = Orientation.N;

        private void turnRight(){
            if(direction == Orientation.N) direction = Orientation.E;
            else if(direction == Orientation.E) direction = Orientation.S;
            else if(direction == Orientation.S) direction = Orientation.W;
            else direction = Orientation.N;
        }

        private void turnLeft(){
            if(direction == Orientation.N) direction = Orientation.W;
            else if(direction == Orientation.W) direction = Orientation.S;
            else if(direction == Orientation.S) direction = Orientation.E;
            else direction = Orientation.N;
        }

        private void moveForeward(){
            if(direction == Orientation.N) location = new Node(location.x-1,location.y);
            else if(direction == Orientation.S) location = new Node(location.x+1,location.y);
            else if(direction == Orientation.E) location = new Node(location.x,location.y+1);
            else if(direction == Orientation.W) location = new Node(location.x,location.y-1);
        }
    }
    
    private class Node{
        long x = 0;
        long y = 0;
        
        private Node(long x,long y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o){
            Node p1 = (Node)o;
            return p1.x == this.x && p1.y == this.y;
        }
        
        @Override
        public int hashCode(){
            return (int)(x + 31*y);
        }
    }
}

