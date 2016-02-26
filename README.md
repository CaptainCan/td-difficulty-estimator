# td-difficulty-estimator

This project was started by students at USC's game pipe lab, forked from https://github.com/ashtonmendes/td-difficulty-estimator. The goal of the project is to aid and assist level designers of tower defense games, by providing some kind of feedback
as and when the level designers change certain parameters of the game. While designing levels for tower defense games, the level designers have to do it in such a way that the level is
neither very easy nor extremely difficult. For this, they have to try out various combinations of monsters, towers, tower positions, the map of the level, etc. As you can imagine, this is a very cumbersome and time-consuming process as there is some amount of trial and error involved. In an effort to help level designers, we provide some estimates, based on some statistical calculations, which tells the level designer how easy or difficult a level is.

What you have to do to make it work on your machine?

1. Update the absolute paths of src/test/resources/aae_TDDesignData.xlsm (not src/main/resources/aae_TDDesignData.xlsm) on your pc in src/java/main/gamepipelab/usc/tdgame/readers/LevelMapReader.java, ReadMonster.java, TowerStatsReader.java, WaveReader.java.
2. Test: Run the project with a configuration as a java application (Main class: gamepipelab.usc.tdgame.naiveEstimator.DifferenceCalculator) in eclipse. If everything you set is correct, the program should print something looks like: G:161%,A:350% G:60%,A:340% G:100%,A:2868% G:70%,A:3623% G:92%,A:2717% G:70%,A:100%
3. No worries about the meaning of the output. We gonna figure it out later.
4. Export the project to a Runnable JAR file: you can put it at where the 'absolutePathSol.jar' is
5. Test: Run the JAR file under cmd line program with "java -jar absolutePathSol.jar", check if it output looks same as the one in eclipse.
6. Open src/test/resources/aae_TDDesignData.xlsm, enable macro, edit macro of button "Calculate difficulty", upadte the JAR file absolute path in "RunProgram" function
7. Test: Run the macro to check the output (remember to enable debug toolbar and open immediate window), check the output. 
8. Back to the excel sheet, change amount and second of wave 1 or wave 2 etc on the top right corner in level 1, remember to save the excel sheet before click the "Calculate difficulty" button
9. Test: Click "Calculate difficulty" button to see if the macro works. Also, change other numbers for amount and second to see if your macro updates something.
