// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	QNode serves as a wrapper for our atomic Question class;
	here, we add two pointers to our QNode (a yes and a no) to
	form the basis of our binary trees nodes. Every node will
	hold a Question to ask and the topic it represents - leaf nodes
	represent final answers, while internal nodes are branching
	questions to help the program narrow down the possibilities.
*/

import java.util.Comparator;
import java.io.Serializable;

public class QNode implements Comparator<Object>, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Question qst;
	protected QNode yesAns;
	protected QNode noAns;
	
	public QNode() {
		qst = null;
		yesAns = null;
		noAns = null;
		
	}
	
	public QNode(Question value) {
		qst = value;
		yesAns = null;
		noAns = null;
		
	}
	
	public QNode(String query, String topic) {
		qst = new Question(query, topic);
		yesAns = null;
		noAns = null;

	}
	
	public QNode(String query, String topic, QNode yes, QNode no) {
		qst = new Question(query, topic);
		yesAns = yes;
		noAns = no;

	}

	@Override
	public int compare(Object o1, Object o2) {
		QNode obj1 = (QNode) o1;
		QNode obj2 = (QNode) o2;
		return obj1.getQst().compare(obj1.getQst(), obj2.getQst());
	}
	
	public Question getQst() {
		return qst;
	}

	public void setQst(Question qst) {
		this.qst = qst;
	}

	public QNode getYesAns() {
		return yesAns;
	}

	public void setYesAns(QNode yesLink) {
		this.yesAns = yesLink;
	}

	public QNode getNoAns() {
		return noAns;
	}

	public void setNoAns(QNode noLink) {
		this.noAns = noLink;
	}
	
	public String getAns() {
		return "Is it a / an / the " + this.qst.topic + "?";
	}
	
	@Override
	public String toString() {
		return qst.toString();
		
	}
	
}