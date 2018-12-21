package org.movingpeople;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class MoveingPeopleTest 
    extends TestCase
{
    /**
     * Rigourous Test :-)
     */
	@Test
    public void testApp()
    {
		int N = 2;
		ArrayList<Integer> unionList= new ArrayList<Integer>(){
			{
				add(0);
				add(1);
				add(2);
				//add(3);
			}
		};
		int[][] worldMap = {{50, 30}, {30, 40}};
        assertEquals(10, MoveingPeople.diffNumber(20, 30));
        //assertEquals(36, MoveingPeople.movingPeople(unionList));
    }
}
