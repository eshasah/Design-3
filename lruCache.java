class DoublyLinkedListNode {
    int key;
    int val;
    DoublyLinkedListNode next;
    DoublyLinkedListNode prev;
    DoublyLinkedListNode(){}
    DoublyLinkedListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}

class LRUCache {
    DoublyLinkedListNode head;
    DoublyLinkedListNode tail;
    Map<Integer, DoublyLinkedListNode> keyMap;
    int capacity = 0;
    int count = 0;

    public LRUCache(int capacity) {
        head = new DoublyLinkedListNode(-1, -1);
        tail = new DoublyLinkedListNode(-1, -1);
        head.next = tail;
        tail.prev = head;

        keyMap = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(keyMap.containsKey(key)) {
            // remove from linked list and add to insertAtLast
            DoublyLinkedListNode node = keyMap.get(key);
            removeNodeFromMiddle(node);
            insertAtLast(node);
            keyMap.put(key, node);
            return keyMap.get(key).val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(keyMap.containsKey(key)) {
            // remove from linked list and add to insertAtLast
            DoublyLinkedListNode cur = keyMap.get(key);
            keyMap.remove(key);
            removeNodeFromMiddle(cur);
        }
        if(keyMap.size() == capacity) {
            // remove from head
            keyMap.remove(head.next.key);
            removeNodeFromMiddle(head.next);
        }
        // add new node to insertAtLast
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, value);
        insertAtLast(newNode);
        keyMap.put(key, newNode);
    }

    private void insertAtLast(DoublyLinkedListNode node) {
        DoublyLinkedListNode prev = tail.prev;
        prev.next = node;
        tail.prev = node;

        node.prev = prev;
        node.next = tail;
    }

    private void removeNodeFromMiddle(DoublyLinkedListNode node) {
        DoublyLinkedListNode prev = node.prev;
        DoublyLinkedListNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */