Joseph (Joey) Lincoln
20 Questions Binary Tree Game in Core Java 21+ LTS
===================================================================
Originally Completed in November 2024 (Sophomore) @ Northern Essex 
Community College in Haverhill, MA for Professor Michael Penta's 
Computer Science II (CIS252) Course [Intro. to Data Structures]
===================================================================
Revised and Edited for Upload to Public, Professional GitHub in 
March of 2026, while completing the last two years of my bachelor's 
degree in Computer Science at Illinois State University, after 
receiving my associate degree in Computer-Information Sciences
from Northern Essex Community College back home in New England. 
===================================================================
DESCRIPTION / INSTRUCTIONS:
===================================================================
This is a basic 20 questions game, coded / programmed in core Java
21+ LTS, serving as my proposed and completed project for CIS252. 
The game itself is not too flashy; it simply runs through the
console / terminal window of your operating system or IDE. 
The logic and backend behind the game is the interesting part.

As the culmination of a little over a year spent almost solely 
focused on object-oriented software programming, I made sure to 
fully emphasize each principle of object-orientation to the best 
of my ability throughout the project. In addition, a deeper 
exploration in certain data structures (binary trees, stacks), 
design principles, and serialization took place as well. 

The game / program is self-teaching, and will learn entirely
based on how and what kind of data and inputs the user feeds to it.
In this way, the project has also been a surface-level exploration 
in the foundational aspects of AI and machine learning as well. 

When the program starts, ObjFileManager immediately checks for
category trees to load and de-serialize. If none are found, he
seeds / generates stock category trees for an instantiated new game.
Five trees, for five categories, paired to the CategoryTopicMap. 
When the player chooses a category after selecting "Play!", 
GameDriver will find that category's tree and begin iterating 
through it, using QNodeStack to monitor its path through the tree.
Once the game reaches a dead end / leaf node, lrnMenu() will be 
called (which begins the "learning process"). The player inputs
a differentiating question and the topic / things they were thinking
of, and whether or not it fell under "Yes" or "No". The program
takes this data and adds the resulting node to the tree accordingly. 
Upon succcessful / proper exit from the game / program, all five
trees are serialized and written back to the disk ensuring everything -
- all learned information, questions, etc. is saved for next time. 

You may either compile and run the files as an executable .jar, 
or you may run them natively through your IDE's console window. 
To attempt to streamline the execution process for those downloading
this repo, a .jar file compiled on my machine will also be provided.
This .jar file is named "twenty-questions-binary-tree.jar" and can
be located in the "out/artifacts/twenty_questions_binary_tree_jar"
directory of the "twenty-questions-binary-tree" project directory.

To run the .jar directly, open your terminal and ensure you have
either the Java 21 JDK or a later version installed with the 
"java --version" command. Once you have verified, or installed, the
Java 21(+) JDK, navigate to the specified directory:
"out/artifacts/twenty_questions_binary_tree_jar", where the 
pre-provided "twenty-questions-binary-tree.jar" file is housed.
Then, run this command...
"java -jar twenty-questions-binary-tree.jar"


