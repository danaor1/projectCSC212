public class PriorityLinkedQueue<T> {

    class Node<T> {
        public T data;
        public float priority;
        public Node<T> nextNode;

        public Node() {
            nextNode = null;}

        public Node(T value, float priority) {
            this.data = value;
            this.priority = priority;
    }

        public T getValue() {
            return data;}
        
        public void setValue(T value) {
            this.data = value;}
        
        public float getPriority() {
            return priority;}
        
        public Node<T> getNextNode() {
            return nextNode;}
        
        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;}
        
    }

    private int count;
    private Node<T> head;

    public PriorityLinkedQueue() {
        head = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isFull() {
        return false;
    }

    public void enqueue(T value, float priority) {
        Node<T> newNode = new Node<T>(value, priority);
        if ((count == 0) || (priority > head.priority)) {
            newNode.nextNode = head;
            head = newNode;
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            while ((current != null) && (priority <= current.priority)) {
                previous = current;
                current = current.nextNode;
            }
            newNode.nextNode = current;
            previous.nextNode = newNode;
        } count++;
    }

    public QueueElement<T> serve() {
        Node<T> node = head;
        QueueElement<T> element = new QueueElement<T>(node.data, node.priority);
        head = head.nextNode;
        count--;
        return element;}
    
}
