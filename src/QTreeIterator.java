// Joseph (Joey) Lincoln || November, 2024 (Sophomore)
// 20 Questions Binary Tree Game in Core Java 21+ LTS
// Revision of Project for Professional GitHub Repo

/*
    An in-order styled iterator to traverse the
    question trees effectively, utilizing an explicit
    Stack object proper. Allows visitation of every node
    in the tree; also useful to print out all our
    category trees and their current nodes.
 */

import java.util.*;

public class QTreeIterator {
    private Stack<QNode> stack;

    public QTreeIterator(QNode root) {
        stack = new Stack<>();
        pushLeft(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public QNode next() {
        QNode node = stack.pop();
        pushLeft(node.getYesAns());
        return node;
    }

    private void pushLeft(QNode node) {
        while (node != null) {
            stack.push(node);
            node = node.getNoAns();
        }
    }
}