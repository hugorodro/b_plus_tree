import java.util.*;

public class tree {

    public static int m;
    node root;

    public tree(int degree) {
        m = degree;
        root = new leafNode(degree);
    }

    public void insertVal(int key, Float val) {
        indexNode aNode = root.insert(key, val);
        if (aNode != null) {
            aNode.children.add(0, root);
            root = aNode;
        }

    }

    public String search(int val1, int val2) {
        ArrayList<Float> searchResult = root.search(val1, val2);
        String resultStr = "Null";
        

        if (searchResult.size() > 0) {
            resultStr = "";
        
            for (int i = 0; i < searchResult.size(); i++){
                resultStr += Float.toString(searchResult.get(i));

                if (i < searchResult.size() - 1){
                    resultStr += ",";
                }
            }
            
        }

        return resultStr;

        
    }

    

}
