import java.util.*;

public class indexNode extends node {
    List<Integer> keys;
    List<node> children;

    public indexNode(int order) {
        super(order);
        keys = new ArrayList<Integer>();
        children = new ArrayList<node>();
       
    }

    public ArrayList<Float> search(int val1, int val2){
        int i = 0;
        while (i<keys.size()){
            if (val1 < keys.get(i)) {
                return children.get(i).search(val1, val2);
  
            }
            i++;
        }

        return children.get(i).search(val1, val2);

    }

    public indexNode insert(int key, Float val){
        indexNode toInsert = null;
        boolean traversed = false;
        int i = 0;
        while (i<keys.size()){
            if (key <= keys.get(i)) {
                toInsert = children.get(i).insert(key,val);
                traversed = true;
                break;
            }
            i++;
        }

        if (traversed == false){
            toInsert = children.get(i).insert(key, val);
        }

        if (toInsert != null){
            keys.add(i, toInsert.keys.get(0));
            children.add((i+1), toInsert.children.get(0));

            if (keys.size() > degree - 1){
                return split();
            }
        }

        return null;

        
    }


    public indexNode split(){

        indexNode rightNode = new indexNode(degree);

        int midInd = Math.floorDiv(keys.size(), 2);

        rightNode.keys = new ArrayList<Integer>(keys.subList(midInd + 1, keys.size()));
        rightNode.children = new ArrayList<node>(children.subList(midInd + 1, children.size()));

        int parentKey = keys.get(midInd);

        keys = new ArrayList<Integer>(keys.subList(0, midInd));
        children = new ArrayList<node>(children.subList(0, midInd+1));


        indexNode parent = new indexNode(degree);

        parent.keys.add(parentKey);
        parent.children.add(rightNode);

        return parent;

    }
    
}
