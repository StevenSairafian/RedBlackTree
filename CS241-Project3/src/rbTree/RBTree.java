package rbTree;

public class RBTree<V extends Comparable<V>> {

    int numElements = 0;
    RBNode<V> root;
    
    public RBTree(){
	root = null;
    }
    
    public void add(V element){
    	RBNode<V> addThis = new RBNode<V>(element);
    	if(root == null){
    		root = addThis;
    		root.isRed = false;
    		return;
    	}
    	
    }
    
    private RBNode find(V element){
    	if(root.data == null){
    	return null;
    	}
    	if(root.data.compareTo(element) == 0){
    		return root;
    	}
    	RBNode<V> index = root;
    	while(index.data != null){
    		if(index.data.compareTo(element) == 0){
    			return index;
    		}
    	else if(index.data.compareTo(element) < 0){
    			index = index.left;
    		}
    		else{
    			index = index.right;
    		}
    	}
    	return null;
    }
}
