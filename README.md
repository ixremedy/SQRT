# SQRT
Copyright (c) 2020, Lev Vanyan lev.vanyan@forthreal.com

An algorithm to extract a square root from any positive number.

This is an algorithm that I made based on a number of simple mathematical equations.

On large numbers (over a million) the proximity must be increased, but for other numbers it seems
to work perfectly well.

The test sequence of double[] numberToTest = {57, 111, 89, 1111, 5531, 5, 2, 3, 11, 13, 17, 117, 272727}
returns: 7.549834, 10.535653, 9.433981, 33.331666, 74.370693, 2.236067, 1.414213, 1.73205, 3.316624, 3.605551, 4.123105, 10.816653,
 522.232704

25.01.2020: Added a tiny javafx-based frontend to it

Use mvn assembly:single to build a jar

