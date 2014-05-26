package rbTree;

public class RBTree<V extends Comparable<V>> {

	int numElements = 0;
	RBNode<V> root;

	public RBTree() {
		root = null;
	}

	public void add(V element) {
		RBNode<V> addThis = new RBNode<V>(element);
		if (root == null) {
			root = addThis;
			root.isRed = false;
			root.left = new RBNode<V>();
			root.left.parent = root;
			root.right = new RBNode<V>();
			root.right.parent = root;
			return;
		}

		RBNode<V> position = findAdd(element);
		position.data = element;
		position.left = new RBNode<V>();
		position.left.parent = position;
		position.right = new RBNode<V>();
		position.right.parent = position;
		position.isRed = true;

		if (position.parent.isRed) {
			rotate(position.parent);
		}
	}

	private RBNode<V> findAdd(V element) {
		RBNode<V> index = root;
		// while not at a leaf
		while (index.data != null) {
			if (index.data.compareTo(element) > 0) {
				index = index.left;
				System.out.println("going to left");
			} else {
				index = index.right;
				System.out.println("going to right");
			}
			//if the children of the parent exist and
			if (index.left != null && index.right != null) {
				// if children of parent are both red
				if (index.left.isRed && index.right.isRed) {
					// make the parent red and its children black
					index.isRed = true;
					index.left.isRed = false;
					index.right.isRed = false;

					if (index.parent.isRed) {
						// single or double rotate
						/*
						 * if (isOutside(index)) { singleRotate(index); } else {
						 * doubleRotate(index); }
						 */
						rotate(index);
					}
				}
			}

		}
		return index;
	}

	private boolean isOutside(RBNode<V> node) {
		if (node == root) {
			return true;
		}
		if (node.parent == root) {
			return true;
		}
		if (node.parent.parent.left.left == node
				|| node.parent.parent.right.right == node) {
			return true;
		}
		return false;
	}

	private RBNode find(V element) {
		if (root.data == null) {
			return null;
		}
		if (root.data.compareTo(element) == 0) {
			return root;
		}
		RBNode<V> index = root;
		while (index.data != null) {
			if (index.data.compareTo(element) == 0) {
				return index;
			} else if (index.data.compareTo(element) < 0) {
				index = index.left;
			} else {
				index = index.right;
			}
		}
		return null;
	}

	private void rightRotate(RBNode<V> node) {
		RBNode<V> temp = node.parent;
		// if the parent node is the left child of the grandparent
		if (node.parent.parent.left == node.parent) {
			node.parent.parent.left = node;
		}
		// otherwise the parent node is the right child of the grandparent
		else {
			// right rotate on right subtree
			node.parent.parent.right = node;
		}
		// right child of node becomes left child of parent node
		temp.left = node.right;
		// set right child of node to ex parent
		node.right = temp;
		// recolor
		node.right.isRed = true;
		node.isRed = false;
	}

	private void leftRotate(RBNode<V> node) {
		RBNode<V> temp = node.parent;

	}

	private void rotate(RBNode<V> node) {
		switch (position(node)) {
		case 1:
			rightRotate(node);
			break;
		case 2:
			leftRotate(node);
			break;
		case 3:
			leftRightRotate(node);
			break;
		case 4:
			rightLeftRotate(node);
			break;
		default:
			break;

		}
	}

	private void leftRightRotate(RBNode<V> node) {
		leftRotate(node);
		rightRotate(node);
		node.isRed = false;
		node.left.isRed = true;
		node.right.isRed = true;
	}

	private void rightLeftRotate(RBNode<V> node) {
		rightRotate(node);
		leftRotate(node);
		node.isRed = false;
		node.left.isRed = true;
		node.right.isRed = true;
	}

	private int position(RBNode<V> node) {
		if (node == root) {
			return -1;
		}
		if (node == root.left) {
			return 1;
		}

		if (node == root.right) {
			return 2;
		}
		if(node.parent.parent.left.left == node){
			return 1;
		}
		if(node.parent.parent.right.right == node){
			return 2;
		}
		if (node == node.parent.left.right) {
			return 3;
		}

		return 4;
	}
	
	public void print(RBNode<V> node){
		if(node!= null){
			print(node.left);
			 	System.out.println(node.data);
			 	print(node.right);
		}
	}
}
