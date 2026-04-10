// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	This .java file manages our serializable game state
	objects (a binary tree for each category), so that the
	player may "save" his or her game (i.e. persistence).
	This means, should they exit the game and want to return
	at a later date, the program will have remembered everything
	it had learned from the user insofar. If no category tree
	objects are found on the game's first launch, this file
	will seed / generate new ones with a single root and only
	two starting yes and no responses. loadTrees() and
	writeTrees() do what you'd imagine; check for and load any
	found category tree objects, and save / write to category tree
	objects upon successful / proper termination of the program.
 */

import java.io.*;

public class ObjFileManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String[] categoryFiles;
	//protected CategoryTopicMap<QuestionTree> catMap;
	
	public ObjFileManager() {
		categoryFiles = new String[] {"qtree-animals.obj", "qtree-plants.obj", "qtree-objects.obj", "qtree-concepts.obj", "qtree-other.obj"};
		//catMap = new CategoryTopicMap<QuestionTree>();
		
	}
	
	public void serialize(QuestionTree qTree, String fileName) {
		FileOutputStream file = null;
        ObjectOutputStream out = null;
		try {
            //Saving of object in a file
            file = new FileOutputStream(fileName);
            out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(qTree);

            System.out.println("Object has been serialized");

        } catch(IOException IOex) {
            System.out.printf("IOException is caught: %s\n", IOex.toString());
            
        } finally {
        	try {
        		out.close();
        	} catch (Exception clsEx1) {
        		clsEx1.printStackTrace();
        	}
            try {
            	file.close();
            } catch (Exception clsEx2) {
            	clsEx2.printStackTrace();
            }
        	
        }
		
	}
	
	public QuestionTree seedTree(String fileStr) {
		QuestionTree tempTree = new QuestionTree(filenameToEnum(fileStr));
		QNode rNode = null;
		QNode yNode = null;
		QNode nNode = null;
		switch (fileStr) {
		case "qtree-animals.obj":
			//think up three QNodes to seed the tree with, initially
			//a root node, a yes leaf node, and a no leaf node
			//build the first three nodes of tree to start, push root node onto question tree
			rNode = new QNode("Does it have 4 legs?", "Animals");
			yNode = new QNode("Is it a dog?", "Dog"); 
			nNode = new QNode("Is it an ant?", "Ant");
			break;
		case "qtree-plants.obj":
			rNode = new QNode("Does it have bark?", "Plants");
			yNode = new QNode("Is it an oak (tree)?", "Oak (Tree)"); 
			nNode = new QNode("Is it corn?", "Corn");
			break;
		case "qtree-objects.obj":
			rNode = new QNode("Is it electric / electronic?", "Objects");
			yNode = new QNode("Is it a computer / laptop?", "Computer / Laptop"); 
			nNode = new QNode("Is it a pencil / pen?", "Pencil / Pen");
			break;
		case "qtree-concepts.obj":
			rNode = new QNode("Is it a concept which relates to the social sciences?", "Concepts");
			yNode = new QNode("Is it Jungian mysticism, archetypes, and psychology?", "Jungian Psychology"); 
			nNode = new QNode("Is it Moore's Law?", "Moore's Law");
			break;
		case "qtree-other.obj":
			rNode = new QNode("Is it a proper noun (person, place, thing)?", "Other");
			yNode = new QNode("Is it the Great Pyramid of Giza?", "Great Pyramid of Giza"); 
			nNode = new QNode("Is it the mailman?", "Mailman");
			break;
		}
		//then, add / push root node to question tree (questionTree == tempTree) 
		//yes and no leaf nodes already linked to root in switch
		//finally, return tempTree
		rNode.setNoAns(nNode);
		rNode.setYesAns(yNode);
		tempTree.setRoot(rNode);
		return tempTree;

	}
	
	public void loadTrees(CategoryTopicMap<QuestionTree> catMap) throws IOException {
		//check for .obj file of serialized save state for question trees
		for (int i = 0; i < categoryFiles.length; i++) {
			//convert category file sub i to a categories enumeration
			//takes file name, spits back category enumeration
			QuestionTree newTree = null;
			Categories enumedFile = filenameToEnum(categoryFiles[i]);
			File stdFile = new File(categoryFiles[i]);
			String fileStr = categoryFiles[i];
			//check for category files sub i, see if it is there
			if (enumedFile != null && stdFile.exists()) {
				//if it is there, deserialize the object file and load it
				//deserialize file to a QuestionTree object to instantiate the tree
				/*FileInputStream file = null;
				ObjectInputStream objInp = null;
				try {
					file = new FileInputStream(categoryFiles[i]);
					objInp = new ObjectInputStream(file);
					newTree = (QuestionTree) objInp.readObject();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					//use category enumeration to push Question Tree object onto Topic Map
					try {
						objInp.close();
					} catch (Exception clsEx1) {
						clsEx1.printStackTrace();
		        	}
		            try {
		            	file.close();
		            } catch (Exception clsEx2) {
		            	clsEx2.printStackTrace();
		            }
		            stdFile = null;
				}*/
				newTree = this.deserialize(fileStr);
			} else {
				newTree = seedTree(fileStr);
				
			}
			catMap.put(enumedFile, newTree);
			
		}
		
	}
	
	public void writeTrees(CategoryTopicMap<QuestionTree> catMap) {
		for (int i = 0; i < categoryFiles.length; i++) {
			Categories enumedFile = filenameToEnum(categoryFiles[i]);
			this.serialize(catMap.get(enumedFile), categoryFiles[i]);
			
		}
	}
	
	public QuestionTree deserialize(String fileStr) throws IOException {
		QuestionTree newTree = null;
		FileInputStream file = null;
		ObjectInputStream objInp = null;
		try {
			file = new FileInputStream(fileStr);
			objInp = new ObjectInputStream(file);
			newTree = (QuestionTree) objInp.readObject();
		} catch (Exception e1) {
			newTree = seedTree(fileStr);
			e1.printStackTrace();
		} finally {
			try {
				objInp.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				file.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		return newTree;
		
	}
	
	public Categories filenameToEnum(String filename) {
		switch (filename) {
		case "qtree-animals.obj":
			return Categories.ANIMAL;
		case "qtree-plants.obj":
			return Categories.PLANT;
		case "qtree-objects.obj":
			return Categories.OBJECT;
		case "qtree-concepts.obj":
			return Categories.CONCEPT;
		case "qtree-other.obj":
			return Categories.OTHER;
		}	
		return Categories.OTHER;
		
	}
	
	public String enumToFilename(Categories qtreeCategory) {
        switch (qtreeCategory) {
        case ANIMAL:
            return "qtree-animals.obj";
        case PLANT:
            return "qtree-plants.obj";
        case OBJECT:
            return "qtree-objects.obj";
        case CONCEPT:
            return "qtree-concepts.obj";
        case OTHER:
            return "qtree-other.obj";
        }
        return null;
    }
	
	public void clearGameState() {
		for (Categories item : Categories.values()) {
			File testFile = new File(enumToFilename(item));
			if (testFile.exists() && testFile.canWrite()) {
				try {
					testFile.delete();
				} catch (Exception rmExc) {
					rmExc.printStackTrace();
				}
			}
		}
		
	}
	
}