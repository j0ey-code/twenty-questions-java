// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	Important game and tree learning logic functions
	are all housed here. uiMenu() shows the game menu,
	collects input from the user. splashScreen() methods
	display basic information. selection() methods parse
	the user's input. The last four functions in this file
	[ isLeaf(), askQuestion(), lrnMenu(), and learn() ] are
	absolutely imperative in managing the tree's structure,
	and the program's self-learning mechanism. Pre-written
	game logic and data given by the player will determine
	the formation of the tree, and how / what is being learned.
 */

import java.util.*;
import java.io.*;

public class GameDriver {
	protected Scanner scn;
	protected ObjFileManager fileManager;
	protected QNodeStack qstStack;
	protected CategoryTopicMap<QuestionTree> qTreeList;
	protected BufferedReader bfrInp;
	
	public GameDriver() {
		scn = new Scanner(System.in);
		fileManager = new ObjFileManager();
		qstStack = new QNodeStack();
		qTreeList = new CategoryTopicMap<QuestionTree>();
		bfrInp = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	public void uiMenu() throws IOException {
		fileManager.loadTrees(qTreeList);
		char usrSlct = ' ';
		char catSlct = ' ';
		int ctr = -1;
		boolean blnDone = false;
		boolean blnSave = true;
		do {
			splashScreenMainMenu();
			String inpToken = scn.next();
			inpToken = inpToken.trim();
			usrSlct = inpToken.charAt(0);
			if (usrSlct == '0') {
				splashScreenAbout();
			} else if (usrSlct == '1') {
				//play, game loop / controls occur here!
				ctr = 0;
				System.out.println("Please select a category: ");
				System.out.println("Animal [0], Plant [1], Object [2], Concept [3], Other [4], Main Menu [5]");
				System.out.print(" :: ");
				String catToken = scn.next();
				catToken = catToken.trim();
				catSlct = catToken.charAt(0);
				blnDone = false;
				int catNum = Integer.valueOf(String.valueOf(catSlct));
				if (catNum > 4 || catNum < 0) {
					continue;
				} else {
					char qstAns = ' ';
					Categories chosen = categorySelection(catSlct);
					QuestionTree sampleTree = qTreeList.get(chosen);
					QNode askNode = sampleTree.getRoot();
					ctr++;
					do {
						qstAns = askQuestion(askNode, ctr);
						//win / loss / game over conditions
						if (isLeaf(askNode) && qstAns == 'Y') {
							System.out.println("I win!");
							blnDone = true;
							
						}
						if (askNode.getYesAns() == null && qstAns == 'Y') {
							blnDone = true;
						}
						if (askNode.getNoAns() == null && qstAns == 'N') {
							blnDone = true;
						}
						if (ctr >= 20) {
							System.out.println("20 questions reached!");
							blnDone = true;
						}
						if (!blnDone) {
							qstStack.push(askNode);
							if (qstAns == 'Y') {
								ctr++;
								askNode = askNode.getYesAns();
							} else if (qstAns == 'N'){
								ctr++;
								askNode = askNode.getNoAns();
							} else if (qstAns == 'B') {
								askNode = qstStack.top();
								ctr--;
								qstStack.pop();
							}
						} else if (blnDone == true && isLeaf(askNode) && qstAns == 'Y') {
							continue;
						} else {
							qstStack.push(askNode);
							if (qstAns == 'Y') {
								System.out.println("Endpoint reached, but 20 questions limit not.");
								System.out.println("Teach me then!");
								QNode learnedNode = lrnMenu(qstStack);
								if (learnedNode != null) {
									qstStack.top().setYesAns(learnedNode);
								}
							} 
							if (qstAns == 'N') {
								System.out.println("Endpoint reached, but 20 questions limit not.");
								System.out.println("Teach me then!");
								QNode learnedNode = lrnMenu(qstStack);
								if (learnedNode != null) {
									qstStack.top().setNoAns(learnedNode);
								}
							}
						}
						//is round done flag
					} while (!blnDone);
					qstStack.clear();
				}
				
			} else if (usrSlct == '2') {
				//iterate / traverse each category's question tree, print each topic out
				QuestionTree tempTree = null;
				String topicList = "Topic List: \n";
				String topics = "";
				for (int i = 0; i < 5; i++) {
					tempTree = qTreeList.get(categorySelection(i));
					topics += "(" + categorySelection(i) + "): ";
					QTreeIterator treeIter = new QTreeIterator(tempTree.getRoot());
					while (treeIter.hasNext()) {
						topics += treeIter.next().getQst().getTopic() + " | ";
					}
					topics += "\n";
				}
				topicList += topics;
				System.out.println(topicList);
				
			} else if (usrSlct == '9') {
				//delete / clear any serialized object files to refresh 20 questions game state to basic seed data
				System.out.println("Are you sure you would like to clear / reset the game's save state? [ Y / N ]?: ");
				String respToken = scn.next();
				respToken = respToken.trim().toUpperCase();
				char respCh = respToken.charAt(0);
				if (respCh == 'Y') {
					fileManager.clearGameState();
					System.out.println("Program status and current game data has been cleared. Reset to default, new game state condition.");
					System.out.println("Question trees will NOT be saved or serialized now upon exiting the game / program.");
					System.out.println("Please exit and save the game immediately after changing this setting to ensure deletion / wipe of the game state files.");
					blnSave = false;
				} else {
					blnSave = true;
					System.out.println("(DEFAULT) Question trees will be saved and written to object files for each category.");
				}
				
			}
			
		} while (usrSlct != '3');
		
		if (blnSave == true) {
			fileManager.writeTrees(qTreeList);
			qstStack.clear();
			scn.close();
		} else {
			qstStack.clear();
			scn.close();
		}
		
	}
	
	private void splashScreenMainMenu() {
		System.out.println();
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println("\t \tWelcome to Twenty Questions!! ");
		System.out.println(" Enter the number that corresponds with your desired action. ");
		System.out.println(" 0.) About ");
		System.out.println(" 1.) Play!! ");
		System.out.println(" 2.) List Topics ");
		System.out.println(" 3.) Save and Exit ~ ");
		System.out.println(" 9.) Clear Game State ");
		System.out.print(" :: ");
	}
	
	private void splashScreenAbout() {
		System.out.println("Welcome to the game of 20 questions, Java console version.");
		System.out.println("When first running the game, a set of generic 20 questions data will be seeded to the program.");
		System.out.println("You may then play, and select the category which matches the topic you're thinking of.");
		System.out.println("The game will then initiate its questioning, and will continue to do so until it reaches an endpoint.");
		System.out.println("When first downloading and running the game, it will not have much to go off of. The seeded data for each category is limited.");
		System.out.println("As a result, the first games will be short. But, the game will then begin prompting you to learn more, each time questioning ends.");
		System.out.println("The program will learn BASED OFF OF RESPONSES THAT YOU GIVE IT to these learning prompts.");
		System.out.println("Each category question-ing tree can continue building itself, down any avenue of the tree. So long as 20 questions have not been asked.");
		System.out.println("The game program will save your responses and new learned information to the category tree, by writing out each tree as a serialized object file in the project.");
		System.out.println("If you wish to clear these files, and wipe the data you've fed to the program by playing, you can press and enter 9 on the main menu.");
		System.out.println("This will default the program back to its original condition, with only the basic, 3 topic sets of seeded data in each category.");
		System.out.println("Otherwise, the game will save its state and continue to learn when you play the next time.");
	}
	
	private Categories categorySelection(char usrInp) {
		switch (usrInp) {
		case '0':
			return Categories.ANIMAL;
		case '1':
			return Categories.PLANT;
		case '2':
			return Categories.OBJECT;
		case '3':
			return Categories.CONCEPT;
		case '4':
			return Categories.OTHER;
		}	
		return Categories.OTHER;
	}
	
	private Categories categorySelection(int usrInp) {
		switch (usrInp) {
		case 0:
			return Categories.ANIMAL;
		case 1:
			return Categories.PLANT;
		case 2:
			return Categories.OBJECT;
		case 3:
			return Categories.CONCEPT;
		case 4:
			return Categories.OTHER;
		}	
		return Categories.OTHER;
	}
	
	public boolean isLeaf(QNode nodeCheck) {
		return (nodeCheck.getNoAns() == null && nodeCheck.getYesAns() == null);
	}
	
	public char askQuestion(QNode askNode, int qCtr) {
		String respToken = " ";
		char usrResp = ' ';
		do {
			System.out.println("Q" + qCtr + ": " + askNode.getQst().toDisplay());
			System.out.println("[ Y / N ], or ( B )ack? :: ");
			respToken = scn.next();
			respToken = respToken.trim().toUpperCase();
			usrResp = respToken.charAt(0);
		} while (usrResp != 'N' && usrResp != 'Y' && usrResp != 'B');
		return usrResp;
		
	}
	
	public QNode lrnMenu(QNodeStack qStack) {
		QNode newNode = null;
		String usrTopic = " ";
		String usrQst = " ";
		String usrAnsLine = " ";
		char usrAns = ' ';
		if (qStack.top().getNoAns() == null && qStack.top().getYesAns() != null) {
			System.out.println("Please tell me the topic you were thinking of then. It will become this previous question's new 'No'.");
			try {
				usrTopic = bfrInp.readLine();
			} catch (IOException lrnInpErr4) {
				lrnInpErr4.printStackTrace();
			}
			usrTopic = usrTopic.trim().toUpperCase();
			String query = "Is it a / an / the " + usrTopic + "?";
			newNode = new QNode(query, usrTopic);
			qStack.top().setNoAns(newNode);
			return newNode;
		}
		if (qStack.top().getYesAns() == null && qStack.top().getNoAns() != null) {
			System.out.println("Please tell me the topic you were thinking of. It will become this previous question's new 'Yes'.");
			try {
				usrTopic = bfrInp.readLine();
			} catch (IOException lrnInpErr5) {
				lrnInpErr5.printStackTrace();
			}
			usrTopic = usrTopic.trim().toUpperCase();
			String query = "Is it a / an / the " + usrTopic + "?";
			newNode = new QNode(query, usrTopic);
			qStack.top().setYesAns(newNode);
			return newNode;
		}
		System.out.println("Please tell me the topic you were thinking of.");
		try {
			usrTopic = bfrInp.readLine();
		} catch (IOException lrnInpErr1) {
			lrnInpErr1.printStackTrace();
		}
		usrTopic = usrTopic.trim().toUpperCase();
		System.out.println("Please provide a yes or no question that would differentiate the two topics.");
		try {
			usrQst = bfrInp.readLine();
		} catch (IOException lrnInpErr2) {
			lrnInpErr2.printStackTrace();
		}
		char first = usrQst.charAt(0);
		char capital = Character.toUpperCase(first);
		usrQst = usrQst.replace(usrQst.charAt(0), capital);
		usrQst += "?";
		do {
			System.out.println("Does your topic fall under the yes or no answer?");
			try {
				usrAnsLine = bfrInp.readLine();
			} catch (IOException lrnInpErr3) {
				lrnInpErr3.printStackTrace();
			}
			usrAnsLine = usrAnsLine.toUpperCase();
			usrAns = usrAnsLine.charAt(0);
			
		} while (usrAns != 'Y' && usrAns != 'N');
		newNode = learn(usrTopic, usrQst, usrAns);
		return newNode;
		
	}
	
	private QNode learn(String newTopic, String newQst, char yesOrNo) {
		QNode lrnNode = new QNode(newQst, newQst);
		QNode leafNode = new QNode("Is it a / an / the " + newTopic + "?", newTopic);
		if (yesOrNo == 'Y') {
			lrnNode.setYesAns(leafNode);
		} else {
			lrnNode.setNoAns(leafNode);
		} 
		return lrnNode;
		
	}
	
	
}
