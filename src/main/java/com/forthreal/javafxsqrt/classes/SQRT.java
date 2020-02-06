/*
 * Author: Lev Vanyan
 * Lev.Vanyan@forthreal.com
 */

package com.forthreal.javafxsqrt.classes;

import java.util.Arrays;
import java.util.stream.Stream;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.stream.IntStream;

public class SQRT
{
	/* find the next smaller nearest number from which we can take a square root
	 * without residue */
	public static Triplet<Boolean, Float, Float> findNextSquareRootable( float inputNumber )
	{
		boolean found = false;
		float newNumber = inputNumber;
		float mainNumber = 0.f;
		
		/* decrease while we don't find an SQRT that doesn't leave residue */
		do {
			Pair<Float, Float> values = sqrt( newNumber );
			
			if( values.getValue1() == 1.0 ) /* no residue */
			{
				//System.out.println("found " + newNumber);
				mainNumber = values.getValue0();
				found = true;
				break;
			}
			else
			{
				/* deacrease by 1 at each iteration and to check if
				 * we will be able to take a square root from it without residue */
				newNumber -= 1.0;
			}
		}
		while( newNumber > 0 );
		
		return Triplet.with( found , newNumber, mainNumber ); 
	}
	
	/* find a square root - advanced */
	public static Pair<Boolean, Double> sqrtAdv( float inputNumber )
	{
		Pair<Float, Float> values = sqrt( inputNumber );
		float priVal = 0;
		
		/* got to apply a stronger algorithm */
		if(values.getValue1() > 0)
		{
			float residue = values.getValue1();
			
			Triplet<Boolean, Float, Float> nextSquare = findNextSquareRootable( residue );
			
			/* found next nearest number to take square root from */
			if( nextSquare.getValue0() == true )
			{
				priVal = nextSquare.getValue2();

				double result = 0.0; 

				/* just to limit the number of iterations */
				int iteration = 0;
				/* we will have to know how many numbers after the dot we have added */
				long iterated = 1;
				/* here we will collect the value of the residue of subtraction of
				 * the newly calculated square root from the previous value */
				double newResidue = 0;
				double pcs = (residue - ( priVal * priVal )) * 100;
				/* currently accrued value that we calculate (without
				 * the fractional part delimiter) */
				double collected = priVal;
				
				do
				{
					/* just to limit the number of iterations */
					iteration++;
					/* we will have to know how many numbers after the dot we have added */
					iterated *= 10;
					
					/* for instance we have 1.7 as the currently calculated number.
					 * to make the next iteration, we will need to multiply 17 by 2
					 * and append such a third number after 34 that would make that number
					 * plus the appended number multiplied by the appended number to fit
					 * the residue multiplied by 100 */
					long iterationValue = 10 * 2 * (long) collected;
					/* how many times it fits the iterated number */
					long times = 0;
					
					int temporaryNumber = 9;
					/* see how many times we fit in the residue multiplied by 100 */
					do {
						
						/* if we don't fit with the times calculated previously because
						 * we added the times multiplier, find the value when we do fit */
						times = (long) pcs / ( iterationValue + temporaryNumber);
						temporaryNumber -- ;
						
					} while( times > ( (long) pcs / (iterationValue + times) ) );
					
					/* the value that will be subtracted from the previous residue * 100 */
					iterationValue += times;
					/* part of the mathematical algorithm, add the number of times
					 * from the previous operation to the tail of the accrued number */
					collected = collected * 10 + times;
					
					newResidue = (double) pcs - (double) ( iterationValue * times );

					/* multiply the residue left by 100 = part of the mathematical algorithm */
					pcs = newResidue * 100;
				}
				while( ( newResidue > 0.0 ) && (iteration < 6 ) );
				
				result = values.getValue0() * (collected / iterated);
				
				System.out.println("result " + result);

				return Pair.with(nextSquare.getValue0(), result);
			}
			else
			{
				/* unlikely to happen, but who knows */
				return Pair.with(nextSquare.getValue0(), 0.0);
			}
		}
		
		return Pair.with( true, (double) values.getValue0() );

		
	}
	
	/* simple SQRT */
	public static Pair<Float,Float> sqrt(float inputNumber)
	{
		boolean canDivide = true;
		int[] nums = {2, 3, 5, 7, 11, 13};
		int[] numVals = {0, 0, 0, 0, 0, 0};
		
		float[] ourNumber = {inputNumber, 0, 1, 1};
		
		/* do the division while it is possible */
		while( canDivide == true )
		{
			
			IntStream.
				range(0, nums.length).
				forEach( i -> {
					/* only do this once on the resulting number just once and then
					 * restart from 2 in the next iteration */
					if( ( ourNumber[0] % nums[i] == 0 ) && (ourNumber[1] == 0) )
					{
						ourNumber[0] = ourNumber[0] / nums[i];
						/* increase the counter of the number of
						 * found particular simple numbers */
						numVals[i]++;
						
						/* did the operation once, don't do this again
						 * before the sequence restarts */
						ourNumber[1] = 1;
						
						if(numVals[i] > 1)
						{
							/* accrue the number taken out of the square root */
							ourNumber[2] *= nums[i];
							numVals[i] = 0;
						}
					}
				});
			

			if(ourNumber[1] == 0)
			{
				canDivide = false;
			}
			/* we've got to set the algorithm to restart finding
			 * a combination from the smallest number of 2 */
			ourNumber[1] = 0;
		}
		
		IntStream.
			range(0, nums.length).
			forEach( i -> {
				//System.out.println(" \"" + nums[i] + "\" => " + numVals[i]);
				
				if(numVals[i] == 1)
				{
					ourNumber[3] *= nums[i];
				}
			} );
		
		float residue = ourNumber[3] * ourNumber[0];

		return Pair.with(ourNumber[2], residue);
	}
}
