package PathFindingProgram;



class ListNode {
    int[] data;
    ListNode next;

    ListNode(int[] data) {
        this.data = data;
        this.next = null;
    }
}


public class CustomLinkedList {
    private ListNode head;
    private ListNode tail;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
    }



    public void insert(int[] data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }



    public boolean isEmpty() {
        return head == null;
    }



    public int[] returnData() {
        if (isEmpty()) {
            System.out.print("List is empty");
        }
        int[] data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }
}