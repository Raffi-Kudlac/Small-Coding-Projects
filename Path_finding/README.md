---Path Finding Algorithm---

I decided to try out what path finding would be like. To my surprise the task is quite challenging. 
How my program works is as follows

My map is represented by a 2D array 10 heigh by 10 wide. The Program always starts in the bottom left corner and trys to get to a target area that is chosen randomly every time the program exicutes. There are three obsticals that are randomly placed every time the program runs. One a vertical column of five blocks, one horizontal row of five blocks and one 2 by 2 cube.

The algorithm used is a distance calculation. Every square has a distance to the finishing square and the program will take whatever square has the smallest distance. This does not always find the shortest route and under some rare cases will never find the finish as the program can not visit a sqaure it has already been to. 
If you run the program

S - Start
F - Finish
A - Path taken
B - Blocked cells/squares