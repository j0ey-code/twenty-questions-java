// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	This standard LIFO stack data structure enables the
	"( B )ack" feature of our game, and also allows our
	lrnMenu() function within the GameDriver.java file
	to monitor where exactly we are in the tree. Critical
	to successful and correct implementation of game's
	self-teaching / self-learning mechanism.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class QNodeStack implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<QNode> nodeStack;
	
	public QNodeStack() {
		nodeStack = new ArrayList<QNode>();
		
	}
	
	public void push(QNode newNode) {
		nodeStack.add(newNode);	
	}
	
	public void pop() {
		nodeStack.remove(this.size() - 1);
	}
	
	public QNode top() {
		return nodeStack.get(this.size() - 1);
	}
	
	public int size() {
		return nodeStack.size();
	}
	
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	public boolean isFull() {
		return (this.size() == 20);
	}
	
	public void clear() {
		nodeStack.clear();
	}

	@Override
	public String toString() {
		String builtStr = "QNodeStack: [ ";
		for (int i = 0; i < this.size(); i++) {
			builtStr += nodeStack.get(i).toString() + ", ";
		}
		builtStr += " ] ";
		return builtStr;
		//return "QNodeStack [nodeStack=" + nodeStack + "]";
	}
	
}