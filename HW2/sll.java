/**
 * Your implementation of a circular singly linked list.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class sll<T> implements LinkedListInterface<T> {
	// Do not add new instance variables or modify existing ones.
	private LinkedListNode<T> head;
	private int size;

	@Override
	public void addAtIndex(T data, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
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
	public void addToFront(T data) { //Done
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
	public T removeAtIndex(int index) { //TODO
		LinkedListNode<T> pointer = head;
		LinkedListNode<T> returnNode = pointer;
		if (index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} else {
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
	public T removeFromFront() { //TODO
		LinkedListNode<T> temp = head;
		if (size == 0) {
			return null;
		} else {
			if (size == 1) {
				head = null;
				size--;
			} else {
				head = head.getNext();
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
				while (i < size) {
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
	public T removeLastOccurrence(T data) { //TODO
		LinkedListNode<T> pointer = head;
		LinkedListNode<T> storageNode = head;
		if (data == null) {
			throw new IllegalArgumentException("Data cannot be null");
		} else {

		}
		return null;
	}

	@Override
	public T get(int index) {
		LinkedListNode<T> temp = head;
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
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
	public Object[] toArray() { //TODO
		int count = 0;
		LinkedListNode<T> temp = head;
		Object[] x = new Object[size];
		while (count < size) {
			x[count] = temp.getData();
			temp = temp.getNext();
			count++;
		}
		return x;
	}

	@Override
	public boolean isEmpty() { //TODO
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() { //TODO
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

//	public static void main(String[] args) {
//		SinglyLinkedList<String> test = new SinglyLinkedList<>();
//		test.addAtIndex("take", 0);
//		test.addAtIndex("a", 0);
//		test.addAtIndex("seat", 0);
//		test.addAtIndex("three", 0);
//
//
//		System.out.println(test.removeLastOccurrence("take"));
//		System.out.println(test.head.getNext().getNext().getNext().getData());
//		System.out.println(test.size);
//		//System.out.println(test.getHead().getData());
//
//
//		//System.out.println(test.getHead().getNext().getNext().getData());
//
//
//	}
}