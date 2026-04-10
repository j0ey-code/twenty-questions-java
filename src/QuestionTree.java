// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	A minimalist wrapper around a given root QNode, to
	create a binary-based decision tree. No traditional
	binary tree balancing or ordering of any kind, as the
	tree's structure is shaped by interaction with the
	user, growing and forming organically by running
	the 20 questions game and learning from the player.
 */

import java.io.*;

public class QuestionTree implements Serializable {
	private static final long serialVersionUID = 1L;
	private QNode root;
	private Categories treeCat;
	
	protected boolean found;
	protected QNode nodePtr;
	//protected Comparator<QNode> comp;
	protected QNodeStack qStack;
	
	public QuestionTree() {
		root = null;
		qStack = new QNodeStack();
		treeCat = null;
		/*comp = new Comparator<QNode>()
	    {
	       public int compare(QNode qstNode1, QNode qstNode2)
	       {
	         return ((Comparable)qstNode1).compareTo(qstNode2);
	       }
	    };*/
	}
	
	public QuestionTree(Categories category) {
		root = null;
		qStack = new QNodeStack();
		treeCat = category;
		/*comp = new Comparator<QNode>()
	    {
	       public int compare(QNode qstNode1, QNode qstNode2)
	       {
	         return ((Comparable)qstNode1).compareTo(qstNode2);
	       }
	    };*/
	}
	
	public QuestionTree(Categories category, QNode daRoot) {
		root = daRoot;
		qStack = new QNodeStack();
		treeCat = category;
		/*comp = new Comparator<QNode>()
	    {
	       public int compare(QNode qstNode1, QNode qstNode2)
	       {
	         return ((Comparable)qstNode1).compareTo(qstNode2);
	       }
	    };*/
	}
	
	//traversal method
	public QNode traversal(QNode startNode) {
		if (startNode.getYesAns() != null) {
			traversal(startNode.getYesAns());
		}
		if (startNode.getNoAns() != null) {
			traversal(startNode.getNoAns());
		}
		return startNode;
		
	}
	
	private QNode recAdd(QNode element, QNode node) {
		if (node == null) {
			node = element;
		} else if (element.compare(element, node) <= 0) {
			node.setYesAns(recAdd(element, node.getYesAns()));    // Add in left subtree
		} else {
			node.setNoAns(recAdd(element, node.getNoAns()));   // Add in right subtree
		}
		return node;
	}

	public boolean add (QNode element) {
		root = recAdd(element, root);
	    return true;
	}

	public int size() {
		return sizeFetch(root);
		
	}
	
	private int sizeFetch(QNode ctrNode) {
		if (ctrNode == null) {
			return 0;
		}
		else {
			return 1 + sizeFetch(ctrNode.getYesAns()) + sizeFetch(ctrNode.getNoAns());
		}
		
	}
	
	public boolean isFull() {
		return false;
	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	public QNode getRoot() {
		return root;
	}

	public void setRoot(QNode root) {
		this.root = root;
	}

	public QNodeStack getQStack() {
		return qStack;
	}

	public void setQStack(QNodeStack qStack) {
		this.qStack = qStack;
	}
	
	@Override
	public String toString() {
		String builtStr = "Q Tree: ";
		builtStr += traversal(root) + " ";
		return builtStr;
		
	}
	
}