package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;





public class Excercise11 extends GenericExcercise {

	Building startBuilding = new Building();
	private final static List<String> CHIPTYPES = new ArrayList<String>();
	
	@Override
	public void executeTest() throws Exception {
		
		String testInput = "resource/Excercise11.txt";
		readTestData(testInput);
		int result = searchValidResult();
		
		logger.debug("Result for excercise 11A:" + result);

		testInput = "resource/Excercise11B.txt";
		readTestData(testInput);
		result = searchValidResult();
		logger.debug("Result for excercise 11B:" + result);

	}
	
	public void readTestData(String file) throws Exception
	{
		List<String> inputLines = FileUtil.readStringsFromFile(file);
		
		startBuilding = new Building();
		int floor = 3;
		for(String line :  inputLines)
		{
			String[] commands = line.split(" ");
			for(String command : commands)
			{
				if(command != null && ! command.equals("") && ! command.equals(".") && !command.startsWith("F"))
				{
					if(command.equals("E")) startBuilding.setElevatorLocation(floor);
					else if( command.length() != 2 || !( command.endsWith("G") || command.endsWith("M")))
						throw new Exception("Unexpected device found: "+command);
					else	
						startBuilding.initBuilding(floor, command);				
				}
			}
			floor--;
		}
		
		logger.debug(startBuilding);
	}
	
	public int searchValidResult()
	{
		int result = 0;
		
		Set<Building> buildingHistory = new HashSet<Building>();
		Set<Building> prevBuildingList = new HashSet<Building>();
		prevBuildingList.add(startBuilding);
		
		
		for(int depth = 1; depth < 100; depth++)
		{
			//logger.debug("Searching at depth: "+(depth)+" history size: "+buildingHistory.size());
			//logger.debug("Searching at depth: "+(depth)+" searching size: "+prevBuildingList.size());
			
			Set<Building> buildingsAtDepth = new HashSet<Building>();
			buildingHistory.addAll(prevBuildingList);
			
			for(Building b : prevBuildingList)
			{
				List<Building> newBuildingList = moveOneLocation(b,buildingHistory);
				for(Building newBuilding : newBuildingList)
				{
					if(newBuilding.isValidFinishLocation()) 
					{
						Building itt = newBuilding;
					/*	while(itt != null)
						{
							logger.debug(itt);
							itt = itt.prevBuilding;
						}*/
						
						return depth;
					}
				}
				buildingsAtDepth.addAll(newBuildingList);	
			}
			
			prevBuildingList = buildingsAtDepth;
		}
		
		
		
		return result;
	}
	
	public List<Building> moveOneLocation(Building building,Set<Building> buildingHistory) 
	{
		List<Building> newBuildingLocations = new ArrayList<Building>();
		
		//logger.debug(building);
		
		int[] currentFloor = building.getFloor(building.getElevatorLocation());
		for(int i = 0; i < currentFloor.length;i++)
		{
			for(int j = i+1; j <= currentFloor.length;j++) // j == currentFloor is not valid but required to enter single i = currentFloor -1
			{
				if( (currentFloor[i] == Building.NONE && 
						(j == currentFloor.length ||currentFloor[j] == Building.NONE))) continue;
				else if(currentFloor[i] == Building.NONE) continue;
				else
				{
					//Moving chip 
					if(currentFloor[i] == Building.CHIP || currentFloor[i] == Building.BOTH)
					{
						//Moving only the first
						Building newBuilding = building.move(1, i,  Building.CHIP, -1, Building.NONE);
						if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						
						newBuilding = building.move(-1, i,  Building.CHIP, -1, Building.NONE);
						if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						
						//Moving both, as chip only combo is possible
						if(j < currentFloor.length &&
								(currentFloor[j] == Building.CHIP || currentFloor[j] == Building.BOTH))
						{
							newBuilding = building.move(1, i,  Building.CHIP, j, Building.CHIP);
							if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						
							newBuilding = building.move(-1, i,  Building.CHIP, j, Building.CHIP);
							if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						}
						
					}
					//moving gen
					if(currentFloor[i] == Building.GEN || currentFloor[i] == Building.BOTH)
					{
						//Moving only the first
						Building newBuilding = building.move(1, i,  Building.GEN, -1, Building.NONE);
						if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						
						newBuilding = building.move(-1, i,  Building.GEN, -1, Building.NONE);
						if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						
						//Moving both, as gen only combo gen is possible
						if(j < currentFloor.length &&
								(currentFloor[j] == Building.GEN || currentFloor[j] == Building.BOTH))
						{
							newBuilding = building.move(1, i,  Building.GEN, j, Building.GEN);
							if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						
							newBuilding = building.move(-1, i,  Building.GEN, j, Building.GEN);
							if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
						}
						
						
					}//moving the pair
					if(currentFloor[i] == Building.BOTH )
					{
						Building newBuilding = building.move(1, i,  Building.BOTH, -1, Building.NONE);
						if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);

						newBuilding = building.move(-1, i,  Building.BOTH, -1, Building.NONE);
						if(newBuilding != null && !buildingHistory.contains(newBuilding)) newBuildingLocations.add(newBuilding);
					}

				}
			}
		}
		
		
		return newBuildingLocations;
	}
	

	/** 
	 * Modeling the building as types of chip arrays. Each array contains an entry for each floor.
	 * 0 = nothing, 1 = Generator, 2 Equals chip, 3 equals both present on the same floor
	 * 
	 * EG example case:
	 * 	 L H
	 * 	 0 0
	 *   1 0
	 *   0 1
	 * E 2 2
	 */
	
	private class Building
	{
		private final static int TOPFLOOR = 3;
		
		private final static int NONE = 0;
		private final static int GEN = 1;
		private final static int CHIP = 2;
		private final static int BOTH = 3;
		
		private long stateHash = 0;
		private int[] lineStates;
		private Building prevBuilding = null;
		
		int[][] building;
		private int elevatorLocation = 0;
		
		public Building()
		{
			building = new int[4][];
			CHIPTYPES.clear();
		}
		
		public boolean isValidFinishLocation()
		{
			if(building == null) return false;
			for(int i = 0; i<building[0].length;i++)
				if(building[TOPFLOOR][i] != BOTH) return false;
			return true;
		}
		
		public void initBuilding(int floor,String str) throws Exception
		{
			if(floor <0 || floor > TOPFLOOR) throw new Exception("Unexpected floor: "+floor);
			
			stateHash = 0;
			
			if(CHIPTYPES.indexOf(str.substring(0,1)) < 0) //Must add extra chip type
			{
				CHIPTYPES.add(str.substring(0,1));
				int newBuildingSize = 1;
				if(building[0] != null) newBuildingSize = building[0].length +1;
				int[][] newBuilding = new int[4][];
				for(int i = 0; i<=TOPFLOOR; i++) newBuilding[i] = new int[newBuildingSize];
				for(int i = 0; building[0] != null && i <= TOPFLOOR; i++) // only copy stuff if not array init phase
					for(int j = 0; j < building[0].length;j++)
						newBuilding[i][j] = building[i][j];
				building = newBuilding;
			}
			
			int chipTypeIndex = CHIPTYPES.indexOf(str.substring(0,1)) ;
			int chipType = 0;
			if(str.endsWith("G")) chipType = GEN;
			else chipType = CHIP;
			if(building[floor][chipTypeIndex] == NONE) 
				building[floor][chipTypeIndex] = chipType;
			else building[floor][chipTypeIndex] = BOTH;
		}
		
		public Building cloneBuilding() 
		{
			Building clone = new Building();

			int[][] newBuilding = new int[TOPFLOOR+1][];
			for(int i = 0; i<= TOPFLOOR; i++) newBuilding[i] = new int[building[0].length];
			for(int i = 0; i <= TOPFLOOR; i++)
				for(int j = 0; j < building[0].length;j++)
					newBuilding[i][j] = building[i][j];


			clone.setElevatorLocation(this.getElevatorLocation());
			clone.building = newBuilding;
			clone.prevBuilding = this;
			
			return clone;
		}
		
		/**
		 * Try to move down the types specified. Returns null if not possible
		 * @param firstIndex
		 * @param secondIndex
		 * @return
		 */
		public Building move(int direction,int firstIndex, int firstType,int secondIndex,int secondType)
		{
			stateHash = 0;
			if(elevatorLocation == 0 && direction == -1) return null;
			if(elevatorLocation == TOPFLOOR && direction == 1) return null;
			else
			{
				Building newBuilding = this.cloneBuilding();
				if(firstType != NONE)
				{
					//setting target location
					if(newBuilding.getFloor(this.getElevatorLocation()+direction)[firstIndex] == NONE) //target empty so write
						newBuilding.getFloor(this.getElevatorLocation()+direction)[firstIndex] = firstType;
					else // if certainly both are there
						newBuilding.getFloor(this.getElevatorLocation()+direction)[firstIndex] = BOTH;
					
					//setting origin location
						if(newBuilding.getFloor(this.getElevatorLocation())[firstIndex] == CHIP || 
								newBuilding.getFloor(this.getElevatorLocation())[firstIndex] == GEN
								|| firstType == BOTH) //case of single origin or both move => clear
							newBuilding.getFloor(this.getElevatorLocation())[firstIndex] = NONE;
						else if(firstType == CHIP)  // Double origin, keep the opposite
							newBuilding.getFloor(this.getElevatorLocation())[firstIndex] = GEN;
						else
							newBuilding.getFloor(this.getElevatorLocation())[firstIndex] = CHIP;
				}

				if(secondIndex > 0 && secondType != NONE)
				{
					//setting target location
					if(newBuilding.getFloor(this.getElevatorLocation()+direction)[secondIndex] == NONE) //target empty so write
						newBuilding.getFloor(this.getElevatorLocation()+direction)[secondIndex] = secondType;
					else // if certainly both are there
						newBuilding.getFloor(this.getElevatorLocation()+direction)[secondIndex] = BOTH;
					
					//setting origin location
					if(newBuilding.getFloor(this.getElevatorLocation())[secondIndex] == CHIP || 
							newBuilding.getFloor(this.getElevatorLocation())[secondIndex] == GEN
							|| secondType == BOTH) //case of single origin => clear
						newBuilding.getFloor(this.getElevatorLocation())[secondIndex] = NONE;
					else if(secondType == CHIP)  // Double origin, keep the opposite
						newBuilding.getFloor(this.getElevatorLocation())[secondIndex] = GEN;
					else
						newBuilding.getFloor(this.getElevatorLocation())[secondIndex] = CHIP;
				}

				if(!newBuilding.isValidFloor(newBuilding.getElevatorLocation())) return null;
				if(!newBuilding.isValidFloor(newBuilding.getElevatorLocation()+direction)) return null;
				
				newBuilding.setElevatorLocation(this.getElevatorLocation()+direction);
				return newBuilding;
			}
		}

		public int getElevatorLocation() {
			return elevatorLocation;
		}

		public void setElevatorLocation(int elevatorLocation) {
			this.elevatorLocation = elevatorLocation;
		}
		
		public int[] getFloor(int floor)
		{
			return building[floor];
		}
		
		public boolean isValidFloor(int floor)
		{
			boolean containsFreeChip = false;
			boolean containsGen = false;
			
			for(int i = 0; i <building[0].length;i++)
			{
				if(building[floor][i] == CHIP) containsFreeChip = true;
				else if(building[floor][i] == GEN || building[floor][i] == BOTH) containsGen = true;
			}
			
			if(containsFreeChip && containsGen) return false;
			else return true;
		}
		
		
		/**
		 * To start lets use the number of double combo's present on a location as a metric
		 * @return
		 */
		public int buildingWeight()
		{
			
			int result = 0;
			for(int floor = 0; floor <= TOPFLOOR; floor++)
			{
				result += this.countType(BOTH,floor)*building[0].length*floor;
			}
			
			result -= elevatorLocation;
		
			return result;
		}
		
		/**
		 * comparing building states. Each chip/gen combo can be in one out of 16 combinations
		 */
		
		public boolean isEquivalent(Building otherBuilding)
		{
			if(this.stateHash == 0) this.calcState();
			if(otherBuilding.stateHash == 0) otherBuilding.calcState();
			
			if(this.stateHash != otherBuilding.stateHash) return false;
			else
			{
				for(int i = 0; i < lineStates.length; i++)
					if(lineStates[i] != otherBuilding.lineStates[i]) return false;
				
				return this.elevatorLocation == otherBuilding.elevatorLocation;
			}
		}
		
		/**
		 * Representing lineState as (3 0 0 0) =1 and (0 0 0 3) = 16
		 */
		
		private void calcState()
		{
			lineStates = new int[16];
			stateHash = 0;
			
			for(int i = 0; i < building[0].length;i++)
			{
				int lineState = 0;
				
				for(int floor = 0; floor <= TOPFLOOR; floor++)
				{
					switch (building[floor][i])
					{
						case 0:
							break;
						case 1:
							lineState += floor;
							break;
						case 2: 
							lineState += floor*4;
							break;
						case 3:
							lineState += (floor)*4+floor;
							break;
					}
				}
				
				lineStates[lineState]++; //one more of this state found
			}
			
			for(int i = 0; i < lineStates.length; i++)
			{
				stateHash += (i+1)*lineStates[i];
				stateHash *= building[0].length;
			}
		}
		
		private int countType(int type,int floor)
		{
			int result = 0;
			
			for(int i = 0; i < building[floor].length;i++)
				if(building[floor][i] == type) result++;
			
			return result;
		}
		
		public boolean equals(Object e)
		{			
			return isEquivalent((Building)e);
		}
		
		
		public int hashCode()
		{/*
			int result = floors.stream().mapToInt(a -> a.stream()
													    .mapToInt(s -> s.hashCode())
													    .reduce(0,Integer::sum))
										.reduce(0,Integer::sum);
								
			return result+this.getElevatorLocation()*100000;*/
			
			return (int)this.stateHash;
		}
		
		
		public String toString()
		{
			
			StringBuilder result = new StringBuilder();
			{
				result.append("\n");
				for(String chipType : CHIPTYPES) result.append("\t"+chipType);
				result.append("\n4");
				if(this.getElevatorLocation() == 3) result.append(" E");
				for(int i = 0; i < building[3].length; i++) result.append("\t"+building[3][i]);
				result.append("\n3");
				if(this.getElevatorLocation() == 2) result.append(" E");
				for(int i = 0; i < building[2].length; i++) result.append("\t"+building[2][i]);
				result.append("\n2");
				if(this.getElevatorLocation() == 1) result.append(" E");
				for(int i = 0; i < building[1].length; i++) result.append("\t"+building[1][i]); 
				result.append("\n1");
				if(this.getElevatorLocation() == 0) result.append(" E");
				for(int i = 0; i < building[0].length; i++) result.append("\t"+building[0][i]);
			}
			
			return result.toString();
		}
		
	}
	
}
