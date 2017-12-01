package org.adventOfCode;

import org.apache.log4j.Logger;

/**
 * Generig testing method for excercises found at: https://adventofcode.com/2016/
 * @author Koen
 *
 */

public abstract class GenericExcercise {
	
	static protected Logger logger;
	
	public GenericExcercise() 
	{
		logger =  Logger.getLogger(this.getClass());
	}
	
	public abstract void executeTest() throws Exception;

}
