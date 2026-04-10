// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
	Wrapper class around a HashMap, serving only to map
	our custom Categories enumeration values to our custom
	QuestionTree binary decision tree objects. Generic choice
	of <T> allows mapping of categories to any number of objects,
	but that will never happen in actuality - the program
	will always be mapping Categories to QuestionTrees.
 */

import java.util.*;

public class CategoryTopicMap<T> implements Map<Categories, T> {
	protected Map<Categories, T> qTreeList;
	
	public CategoryTopicMap() {
		qTreeList = new HashMap<Categories, T>();
		
	}

	@Override
	public int size() {
		return qTreeList.size();
	}

	@Override
	public boolean isEmpty() {
		return (qTreeList.size() == 0);
	}

	@Override
	public boolean containsKey(Object key) {
		return qTreeList.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return qTreeList.containsValue(value);
	}

	@Override
	public T get(Object key) {
		return qTreeList.get(key);
	}

	@Override
	public T put(Categories key, T value) {
		T temp = value;
		qTreeList.put(key, value);
		return temp;
	}

	@Override
	public T remove(Object key) {
		T temp = null;
		if (qTreeList.containsKey(key)) {
			temp = qTreeList.remove(key);
		} else {
			//
		}
		return temp;
	}

	@Override
	public void putAll(Map<? extends Categories, ? extends T> m) {
		qTreeList.putAll(m);
	}

	@Override
	public void clear() {
		qTreeList.clear();
	}

	@Override
	public Set<Categories> keySet() {
		return qTreeList.keySet();
	}

	@Override
	public Collection<T> values() {
		return qTreeList.values();
	}

	@Override
	public Set<Entry<Categories, T>> entrySet() {
		return qTreeList.entrySet();
	}

	
}
