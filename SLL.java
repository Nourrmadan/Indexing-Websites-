public class SLL {
    //This is the SLL.Node class that is used in the construction of the SLL
    class Node{
        String Data;
        Node next;
        public Node(String Data){
            this.Data = Data;
            next = null;
        }
        //Accessor and Modifier Methods
        public Node getNext() {return next;}
        public void setData(String data) {Data = data;}
        public void setNext(Node next) {this.next = next;}
        public String getData() {return Data;}
    }
    Node head = null;
    Node tail = null;

    //This method adds a value into the SLL, and creates a new one if one isn't already created
    public void addNode(String Data){
        if(head == null){
            Node newNode = new Node(Data);
            head = newNode;
            tail = newNode;
            head.setNext(tail);
            return;
        }
        Node newNode = new Node(Data);
        tail.setNext(newNode);
        tail = newNode;
        tail.setNext(null);

    }
    //An Overridden method from the Object class, This method Converts the Data of the tree into an easy to print string
    @Override
    public String toString(){
        Node Current = head;
        String temp = "";
        if(head == tail){
            return Current.Data;
        }
        while(Current != null){
            temp += Current.Data + ", ";
            Current = Current.getNext();
        }
        return temp.substring(0, temp.length()-3);
    }
    //This is a helper method to assist in using the recursive search method
    public boolean search(String key){
        return Search_rec(head, key);
    }
    //This is a recursive method used to search an SLL for a certain key
    public boolean Search_rec(Node current, String key){
        if(current == null){
            return false;
        }else {
            if (current.Data.equals(key)) {
                return true;
            } else if (head != tail){
                return Search_rec(current.next, key);
            }else{
                return false;
            }
        }
    }

}
