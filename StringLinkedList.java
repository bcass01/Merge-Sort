public class StringLinkedList {

    /**
     * A private Linked List Node class.
     */
    private class Node {
        String value = null;
        Node prev = null;
        Node next = null;

        /**
         * Constructor with a single value.
         *
         * @param value The String this node contains.
         */
        public Node(String value) {
            this.value = value;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    /**
     * Add a string to the list.
     *
     * @param string The string to add.
     */
    public void add(String string) {
        Node newNode = new Node(string);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.size++;
    }

    /**
     * Get an element of the list.
     *
     * This function assumes that the index is within range.
     *
     * @param index The index to get.
     * @return The string at that index.
     */
    public String get(int index) {
        Node curr = this.head;
        for (int i = 0 ; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }
    
    /**
     * Print the list, one element per line.
     */
    public void print() {
        Node node = this.head;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    /**
     * Sort the list with merge sort.
     */
    public void mergesort() {
        // FIXME
    	this.head = recursiveSort(this.head);
    	if (this.head != null) {
	    	Node curr = this.head;
	    	while (curr.next != null) {
	    		curr = curr.next;
	    	}
	    	this.tail = curr;
    	}
    }
    
    private static Node split(Node n) {
        Node fast = n, slow = n;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        temp.prev = null;
        return temp;
    }
    
    private static Node recursiveSort(Node n) {
    	if (n == null || n.next == null) {
    		return n;
    	}
    	Node n2 = split(n);
    	n = recursiveSort(n);
    	n2 = recursiveSort(n2);
    	return merge(n, n2);
    }
    
    private static Node merge(Node n, Node n2) {
    	if (n == null) {
    		return n2;
    	}
    	if (n2 == null) {
    		return n;
    	}
    	if (n.value.compareTo(n2.value) <= 0) {
            n.next = merge(n.next, n2);
            n.next.prev = n;
            n.prev = null;
            return n;

    	}
    	if (n2.value.compareTo(n.value) < 0) {
    		n2.next = merge(n, n2.next);
    		n2.next.prev = n2;
    		n2.prev = null;
    		return n2;
    	}
    	return null;

    }

    public static void main(String[] args) {
        String[] alphabet = {
            "uniform", "tango", "sierra", "papa", "november",
            "alfa", "whiskey", "quebec", "hotel", "bravo",
            "india", "oscar", "romeo", "x-ray", "delta",
            "echo", "golf", "charlie", "zulu", "yankee", 
            "juliett", "foxtrot", "mike", "lima", "victor", 
            "kilo"
        };

        StringLinkedList list = new StringLinkedList();

        for (int i = 0; i < alphabet.length; i++) {
            list.add(alphabet[i]);
        }

        list.mergesort();
        list.print();
        System.out.println(list.tail.value);
    }

}
