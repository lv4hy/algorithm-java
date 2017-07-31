import java.util.*;
import java.util.function.Consumer;

/**
 * Created by ctis-szx on 2017/7/26.
 */
public class TreeAlgorithm {

    private boolean canBuild = true;

    /**
     * 打印二叉树某一层的节点
     */
    public int printNodeAtLevel(Node root, int level){
        if(root == null || level < 0){
            return 0;
        }
        if(level == 0){
            System.out.print(root.getData());
            return 1;
        }
        return printNodeAtLevel(root.getLeft(), level - 1) + printNodeAtLevel(root.getRight(), level - 1);
    }

    /**
     * 递归打印每一层的节点
     * @param root
     */
    public void printNodeByLevel(Node root){
        for(int i = 0; ; i++){
            if(printNodeAtLevel(root, i) == 0){
                break;
            }
            System.out.println();
        }
    }

    /**
     * 非递归实现，借助于List存储
     * @param root
     */
    public void printNodeByLevelNoRecursive(Node root){
        if(root == null)
            return;
        List<Node> nodeList = new ArrayList<>();

        nodeList.add(root);
        int cur = 0;
        int last = 1;
        while(cur < nodeList.size()){
            last = nodeList.size();
            while(cur < last){
                Node currentNode = nodeList.get(cur);
                System.out.print(nodeList.get(cur));
                if(currentNode.getLeft() != null){
                    nodeList.add(currentNode);
                }
                if(currentNode.getRight() != null){
                    nodeList.add(currentNode);
                }
                cur ++;
            }
            System.out.println();

        }
    }

    /**
     *根据前序和中序遍历序列重新构造二叉树
     * @param preOrder 前序遍历数组
     * @param inOrder 中序遍历数组
     * @return
     */
    public Node rebuildTree(char[] preOrder, char[] inOrder){
        if(preOrder.length == 0 || inOrder.length == 0){
            return null;
        }
        char value = preOrder[0];

        Node root = new Node(value);


        int rootIndex = 0;

        while(rootIndex < inOrder.length){
            if(value == inOrder[rootIndex]){
                break;
            }
            rootIndex++;
        }
        if(rootIndex >= inOrder.length){
            return null;
        }

        char[] leftPre = Arrays.copyOfRange(preOrder, 1, rootIndex + 1);
        char[] leftIn = Arrays.copyOfRange(inOrder, 0, rootIndex);

        char[] rightPre = Arrays.copyOfRange(preOrder, rootIndex + 1, preOrder.length);
        char[] rightIn = Arrays.copyOfRange(inOrder, rootIndex + 1, inOrder.length);

        root.setLeft(rebuildTree(leftPre, leftIn));
        root.setRight(rebuildTree(rightPre, rightIn));

        return root;

    }

    /**
     * 求树的高度
     * @param root
     * @return
     */
    public int getTreeHeight(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight = getTreeHeight(root.getLeft());
        int rightHeight = getTreeHeight(root.getRight());
        return Math.max(leftHeight,rightHeight) + 1;
    }

    /**
     * 求二叉树中节点的最大距离
     * @param root
     * @return
     */
    public int findMaxDistance(Node root){
        if(root == null){
            return 0;
        }
        int leftMaxDistance = findMaxDistance(root.getLeft());
        int rightMaxDistance = findMaxDistance(root.getRight());
        int leftHeight = 0;
        int rightHeight = 0;
        if(root.getLeft() != null){
            leftHeight = getTreeHeight(root.getLeft());
        }
        if(root.getRight() != null){
            rightHeight = getTreeHeight(root.getRight());
        }
        return Math.max(Math.max(leftMaxDistance, rightMaxDistance), leftHeight+rightHeight);

    }

    public static void main(String[] args) {
        Collection<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        long a = integers.stream().filter(integer ->  integer % 2 == 0).count();
        System.out.println(a);
    }




}
