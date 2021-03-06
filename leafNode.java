import java.util.*;

class leafNode extends node{
    List<Integer> keys;
    List<Float> vals;
    leafNode leftNeighbor;
    leafNode rightNeighbor;

    public leafNode(int order) {
        super(order);
        keys = new ArrayList<Integer>();
        vals = new ArrayList<Float>();
        leftNeighbor = null;
        rightNeighbor = null;
    }

    public ArrayList<Float> search(int val1, int val2){
        ArrayList<Float> ans = new ArrayList<Float>();
        leafNode aNode = this;
        while(aNode != null){
            int i = 0;
            while (i<aNode.keys.size()){
                if (val2 < aNode.keys.get(i)) {
                    return ans;
                } else if (val1 <= aNode.keys.get(i)){
                    ans.add(aNode.vals.get(i));
                } 
                i++;
            }

            aNode = aNode.rightNeighbor;
        }
            
        return ans;

    }

    

    public indexNode insert(int key, Float val) {
        Boolean added = false;
        int i = 0;
        while (i<keys.size()){
            if (key <= keys.get(i)) {
                keys.add(i, key);
                vals.add(i, val);
                added = true;
                break;
            }
            i++;
        }

        if (added == false){
            keys.add(key);
            vals.add(val);
        }

        if (keys.size()> degree - 1){
            return split();
        }

        return null;
        

    }

    public indexNode split() {
        
        leafNode rightNode = new leafNode(degree);

        rightNeighbor = rightNode;
        rightNode.leftNeighbor = this;

        int midInd = Math.floorDiv(keys.size(), 2);

        rightNode.keys = new ArrayList<Integer>(keys.subList(midInd, keys.size()))        ;
        rightNode.vals = new ArrayList<Float>(vals.subList(midInd, vals.size()));

        keys = new ArrayList<Integer>(keys.subList(0, midInd));
        vals = new ArrayList<Float>(vals.subList(0, midInd));


        indexNode parent = new indexNode(degree);

        parent.keys.add(rightNode.keys.get(0));
        parent.children.add(rightNode);

        return parent;


        // need to add key to parent

        // keys = new ArrayList<Integer>();
        // keys.add(rightNode.keys.get(0));
        
    }

    // public void printNode(int level) {
    //     String isLeafStr;
        
    //     if (isLeaf == true){
    //         isLeafStr = "L";
    //     } else {
    //         isLeafStr = "T";
    //     }

    //     System.out.println(keys + isLeafStr);

    //     if (isLeaf == true) {
    //         System.out.println(vals);
    //     } else {
    //         for (int i = 0; i < pointers.size(); i++) {
    //             pointers.get(i).printNode(level + 1);
    //         }
    //     }
    // }
}
