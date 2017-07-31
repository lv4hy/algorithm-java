import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ctis-szx on 2017/7/26.
 */
public class TreeAlgorithmTestJunit {


    @Test
    public void printNodeAtLevel() throws Exception {
    }

    @Test
    public void printNodeByLevel() throws Exception {
    }

    @Test
    public void printNodeByLevelNoRecursive() throws Exception {
    }

    @Test
    public void rebuildTreeTest() throws Exception {
        char[] preOrder = {'a','b','d','c','e','f'};
        char[] inOrder = {'d','b','a','e','c','f'};
        TreeAlgorithm algorithm = new TreeAlgorithm();
        Node root = algorithm.rebuildTree(preOrder, inOrder);

        algorithm.printNodeByLevel(root);
        System.out.println(algorithm.getTreeHeight(root));
        System.out.println(algorithm.findMaxDistance(root));
    }

}