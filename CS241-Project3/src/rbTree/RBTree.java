package rbTree;

public class RBTree<V extends Comparable<V>> {

    int numElements = 0;
    RBNode<V> root;
    
    public RBTree(){
	root = null;
    }
}
