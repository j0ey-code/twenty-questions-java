// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	The most primitive, atomic unit of our game's data structure
	and knowledge base. A pairing of the query and determined
	topic for a given question within our 20 questions binary tree / game.
*/

import java.util.Comparator;
import java.io.Serializable;

public class Question implements Comparator<Object>, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String query;
	protected String topic;
	
	public Question() {
		query = null;
		topic = null;
		
	}
	
	public Question(String qstAsk, String topicSlct) {
		query = qstAsk;
		topic = topicSlct;
		
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		Question objQ1 = (Question) o1;
		Question objQ2 = (Question) o2;
		return objQ1.getTopic().compareTo(objQ2.getTopic());
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String toDisplay() {
		return "Question: " + this.query;
	}
	
	@Override
	public String toString() {
		return "Topic: " + this.topic + ", Question: " + this.query;
	}

	
}