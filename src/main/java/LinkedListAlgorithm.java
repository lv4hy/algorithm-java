/**
 * Created by ctis-szx on 2017/7/31.
 */
public class LinkedListAlgorithm {

    public class LinkedNode<T>{
        T value;
        LinkedNode<T> next;

        public LinkedNode(T value) {
            this.value = value;
        }
    }

    /**
     * 给定一个数组,初始化一个链表
     * @param input 给定的数组
     *
     */
    public <T> LinkedNode<T> initLinkedList(T[] input){
        LinkedNode<T> head;
        LinkedNode<T> pre;
        if(input == null && input.length == 0){
            return null;
        }

        head = new LinkedNode<>(input[0]);
        pre = head;
        for(int i = 1; i < input.length; i++){
            LinkedNode<T> node = new LinkedNode<>(input[i]);
            pre.next = node;
            pre = node;
        }
        return head;
    }

    /**
     * 打印链表节点
     * @param head 链表的头结点
     * @param <T>
     */
    public <T> void printLinkedList(LinkedNode<T> head){
        if(head == null){
            return;
        }
        LinkedNode<T> current = head;
        while(current != null){
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 链表反转
     * @param head 链表的头节点
     * @return 反转后的链表头结点
     */
    public <T> LinkedNode<T> reverseLinkedList(LinkedNode<T> head){
        if(head == null){
            return null;
        }
        LinkedNode<T> pre, current, next;
        pre = null;
        current = head;
        next = current.next;
        while(current != null){
            next = current.next;//这个地方，最好是先根据current，来得到next，因为如果先操作了current,current可能会变成null，引发空指针异常
            current.next = pre;
            pre = current;
            current = next;
        }
        current = pre;
        return current;
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        String[] array = {"a","b","c","d","e"};
        LinkedListAlgorithm algorithm = new LinkedListAlgorithm();
        LinkedNode<String> head = algorithm.initLinkedList(array);
        algorithm.printLinkedList(head);
        LinkedNode<String> head2 = algorithm.reverseLinkedList(head);
        algorithm.printLinkedList(head2);
    }
}
