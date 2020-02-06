/*
 * Author: Lev Vanyan
 * Lev.Vanyan@forthreal.com
 */

package com.forthreal.javafxsqrt;

import com.forthreal.javafxsqrt.classes.SQRT;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SQRTTest 
{
	@BeforeAll
    public static void suite()
    {

    }

    @Test
    @Order(0)
    public void testSQRT()
    {
    	double[] numberToTest = {57, 111, 89, 1111, 5531, 5, 2, 3, 11, 13, 17, 117, 272727};
    	
    	IntStream.range(0, numberToTest.length).forEach( i->
    	{
        	Pair<Boolean, Double> result = SQRT.sqrtAdv( (float) numberToTest[i] );
        	Double resultMulti = result.getValue1() * result.getValue1();
        	
        	Assertions.assertTrue
        		(
        		  ( (numberToTest[i] + 0.5) > resultMulti ) && 
        		  ( (numberToTest[i] - 0.5) < resultMulti )
        		);    		
    	});
    	
    }
}
