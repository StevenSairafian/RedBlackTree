package rbTree;

public class RBTree<V extends Comparable<V>> {

	int numElements = 0;
	RBNode<V> root;
	RBNode<V> leaf = new RBNode<V>();

	public RBTree() {
		root = null;
	}

	public void add(V element) {
		RBNode<V> addThis = new RBNode<V>(element);
		if (root == null) {
			root = addThis;
			root.isRed = false;
			root.left = leaf;
			root.right = leaf;
			return;
		}

	}

	private void findAdd(V element) {
		RBNode<V> index = root;
		// while not at a leaf
		while (index.data != null) {
			if (index.data.compareTo(element) < 0) {
				index = index.left;
			} else {
				index = index.right;
			}
			// if children of parent are both red
			if (index.left.isRed && index.right.isRed) {
				// make the parent red and its children black
				index.isRed = true;
				index.left.isRed = false;
				index.right.isRed = false;
			}

			if (index.parent.isRed) {
				// single or double rotate
				if (isOutside(index)) {
					singleRotate(index);
				} else {
					doubleRotate(index);
				}
			}

		}
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
	
	private void rightRotate(RBNode<V> node){
		RBNode<V> temp = node.parent;
		//if the parent node is the left child of the grandparent
		if(node.parent.parent.left == node.parent){
			node.parent.parent.left = node;
		}
		//otherwise the parent node is the right child of the grandparent
		else{
			//right rotate on right subtree
			node.parent.parent.right = node;
		}
			//right child of node becomes left child of parent node
			temp.left = node.right;
			//set right child of node to ex parent
			node.right = temp;
			//recolor
			node.right.isRed = true;
			node.isRed = false;
	}
	
	private void doubleRotate(RBNode<V> node){
		
	}
}
