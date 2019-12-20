package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private final Node<E> root;

    public Tree(E value) {
        root = new Node<E>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> node = findBy(parent);
            node.ifPresent(eNode -> eNode.add(new Node(child)));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            LinkedList<Node<E>> list = listTree();
            Iterator<Node<E>> it = list.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next().getValue();
            }

            private LinkedList<Node<E>> listTree() {
                LinkedList<Node<E>> data = new LinkedList<>();
                LinkedList<Node<E>> result = new LinkedList<>();
                data.add(Tree.this.root);
                while (!data.isEmpty()) {
                    Node<E> el = data.poll();
                    result.addFirst(el);
                    for (Node<E> child : el.leaves()) {
                        data.addFirst(child);
                    }
                }
                return result;
            }
        };
    }
}
