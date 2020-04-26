import java.util.NoSuchElementException;

/**
 * Your implementation of a circular singly linked list.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    public LinkedListNode<T> head;
    public int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds. "
                    + "Index must be between 0 and " + size);
        } else if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            if (index == 0) {
                addToFront(data);
            } else if (index == size) {
                addToBack(data);
            } else {
                int i = 0;
                LinkedListNode<T> newNode = new LinkedListNode<T>(null, null);
                LinkedListNode<T> tempNode = head;
                while (i <= index) {
                    if (i == index) {
                        newNode.setNext(tempNode.getNext());
                        newNode.setData(tempNode.getData());
                        tempNode.setNext(newNode);
                        tempNode.setData(data);
                        size++;
                    } else {
                        tempNode = tempNode.getNext();
                    }
                    i++;
                }
            }
        }


    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            if (size == 0) {
                head = new LinkedListNode<T>(data, head);
                head.setNext(this.head);
                size++;
            } else {
                LinkedListNode<T> newNode = new LinkedListNode<T>(data, null);
                if (size == 1) {
                    newNode.setNext(head);
                    head.setNext(newNode);
                    head = newNode;
                    size++;
                } else {
                    newNode.setNext(head.getNext());
                    newNode.setData(head.getData());
                    head.setNext(newNode);
                    head.setData(data);
                    size++;
                }
            }
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            if (size == 0) {
                head = new LinkedListNode<T>(data, head);
                head.setNext(this.head);
                size++;
            } else {
                LinkedListNode<T> newNode = new LinkedListNode<T>(data, null);
                LinkedListNode<T> temp = head;
                if (size == 1) {
                    newNode.setNext(head);
                    head.setNext(newNode);
                    size++;
                } else {
                    newNode.setNext(head.getNext());
                    newNode.setData(head.getData());
                    head.setNext(newNode);
                    head.setData(data);
                    head = newNode;
                    size++;
                }
            }
        }
    }

    @Override
    public T removeAtIndex(int index) {
        LinkedListNode<T> pointer = head;
        LinkedListNode<T> returnNode = new LinkedListNode<T>(null);
        if (index >= size || index < 0 || size == 0) {
            throw new IndexOutOfBoundsException("Index out of bounds. "
                    + "Value must be between 0 and " + (size - 1));
        } else {
            returnNode.setData(head.getData());
            if (size == 1) {
                removeFromFront();
            } else if (index == 0) {
                removeFromFront();
            } else {
                int i = 0;
                while (i <= index) {
                    if (i == index - 1) {
                        returnNode = pointer.getNext();
                        pointer.setNext(pointer.getNext().getNext());
                        size--;
                        i++;
                    } else {
                        pointer = pointer.getNext();
                        i++;
                    }
                }
            }
        }
        return returnNode.getData();
    }

    @Override
    public T removeFromFront() {
        LinkedListNode<T> temp = new LinkedListNode<T>(null);
        if (size == 0) {
            return null;
        } else {
            if (size == 1) {
                temp = head;
                head = null;
                size--;
            } else {
                temp.setData(head.getData());
                head.setData(head.getNext().getData());
                head.setNext(head.getNext().getNext());
                size--;
            }
        }

        return temp.getData();
    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> temp = head;
        LinkedListNode<T> returnNode = head;
        if (size == 0) {
            return null;
        } else {
            if (size == 1) {
                removeFromFront();
            } else {
                int i = 0;
                while (i < size - 1) {
                    if (temp.getNext().getNext() == head) {
                        returnNode = temp.getNext();
                        temp.setNext(temp.getNext().getNext());
                        size--;
                    } else {
                        temp = temp.getNext();
                    }
                    i++;
                }
            }
        }
        return returnNode.getData();
    }

    @Override
    public T removeLastOccurrence(T data) {
        LinkedListNode<T> pointer = head;
        LinkedListNode<T> storageNode = null;
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (head == null) {
            return null;
        } else {
            if (size == 1 && pointer.getData().equals(data)) {
                removeFromFront();
                clear();
            } else if (size == 2 && head.getData().equals(data)) {
                pointer = head;
                removeFromFront();
            } else if (size == 2 && head.getNext().getData().equals(data)) {
                pointer = head.getNext();
                removeFromBack();
            } else {
                int i = 0;
                if (head.getData().equals(data)) {
                    storageNode = head;
                }
                while (i < size - 1) {
                    if (pointer.getNext().getData().equals(data)) {
                        storageNode = pointer;
                    }
                    pointer = pointer.getNext();
                    i++;
                }
                if (storageNode == head) {
                    pointer = storageNode;
                    removeFromFront();
                } else {
                    if (storageNode != null) {
                        pointer = storageNode.getNext();
                        storageNode.setNext(storageNode.getNext().getNext());
                        size--;
                    } else {
                        return null;
                    }
                }
            }
        }
        return pointer.getData();
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> temp = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds. "
                    + "Index must be between 0 and " + (size - 1));
        } else if (index == 0) {
            return head.getData();
        } else {
            int i = 0;
            while (i <= index) {
                if (i == index) {
                    return temp.getData();
                } else {
                    temp = temp.getNext();
                }
                i++;
            }
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        int count = 0;
        LinkedListNode<T> temp = head;
        Object[] toArray = new Object[size];
        while (count < size) {
            toArray[count] = temp.getData();
            temp = temp.getNext();
            count++;
        }
        return toArray;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    public void removeFirst(T data) {
        LinkedListNode<T> temp = head;
        if (size == 1 && head.getData().equals(data)) {
            head = null;
            size--;
        } else if (head.data.equals(data)) {
            head.data = head.next.data;
            head.next = head.next.next;
            size--;
        } else {
            int i = 0;
            while (i < size) {
                if (temp.getNext().getData().equals(data)) {
                    temp.setNext(temp.getNext().getNext());
                    size--;
                    return;
                } else {
                    temp = temp.getNext();
                    i++;
                }
                if(i == size - 1){
                    throw new NoSuchElementException();
                }
            }
        }
    }


    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addToFront(3);
        list.addToFront(2);
//        list.addToFront(1);
//        list.addToBack(4);
//        //list.addToBack(5);
//        list.addToBack(6);
//        list.addToBack(7);
//        list.addToBack(5);

        LinkedListNode<Integer> node = list.getHead();

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(node.getData());
            node = node.getNext();
        }

        System.out.println();

//        list.removeFirst(5);
//        list.removeFirst(6);
        list.removeFirst(3);

        System.out.println(list.getHead().getData());
        System.out.println(list.head.getNext().getData());
        System.out.println(list.size);

        System.out.println();

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(node.getData());
            node = node.getNext();
        }

    }

}