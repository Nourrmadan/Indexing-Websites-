public class AVLTree {


    //Class AVLTree.Node, used to construct the tree
    class Node {
        String websiteName;
        SLL ips;
        int height;
        Node left;
        Node right;
        Node(String websiteName, String ip) {
            this.websiteName = websiteName;
            this.ips = new SLL();
            ips.addNode(ip);
        }
        //Accessor and Modifier Methods for the Node Class
        public String getWebsiteName() {
            return websiteName;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public int getHeight() {
            return height;
        }

        public SLL getIps() {
            return ips;
        }

        public void setWebsiteName(String websiteName) {
            this.websiteName = websiteName;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setIps(SLL ips) {
            this.ips = ips;
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }
    //Helper Insertion Method for the recursive insertion method
    public void insert(String websiteName,String ip) {
        root = insert(root, websiteName,ip);
    }
    //This method first compares the website names to identify if the webpage has been placed before, and adds it to the
    //correct node. If not, it starts comparing the website names to place in the avl accordingly. when it's done, it
    //calls the balancing method to balance the avl tree after insertion if needed
    private Node insert(Node node, String websiteName ,String ip) {
        if (node == null) {
            return new Node(websiteName,ip);
        }
        int comparedValue = node.websiteName.compareTo(websiteName);
        if (comparedValue > 0) {
            node.left = insert(node.left, websiteName,ip);
        } else if (comparedValue < 0) {
            node.right = insert(node.right, websiteName,ip);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }
    //This method is used to search for a website name in the AVL tree
    public Node find(String insertedWebsite) {
        Node current = root;
        while (current != null) {
            if (current.websiteName.equals(insertedWebsite)) {
                break;
            }
            current = current.websiteName.compareTo(insertedWebsite) < 0 ? current.right : current.left;
        }
        return current;
    }
    //This method is used to rebalance the tree if needed
    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (height(node.right.right) > height(node.right.left)) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        } else if (balance < -1) {
            if (height(node.left.left) > height(node.left.right)) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        return node;
    }
    //This method is used to rotate right when balance is required
    private Node rotateRight(Node node) {
        Node left = node.left;
        Node temp = left.right;
        left.right = node;
        node.left = temp;
        updateHeight(node);
        updateHeight(left);
        return left;
    }
    //This method is used to rotate left when balance is required
    private Node rotateLeft(Node node) {
        Node right = node.right;
        Node temp = right.left;
        right.left = node;
        node.right = temp;
        updateHeight(node);
        updateHeight(right);
        return right;
    }
    //This method updates the height attribute in the node's attributes
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    //This method is able to return the height of the node
    private int height(Node node) {
        return node == null ? -1 : node.height;
    }
    //This method checks if the tree needs balancing
    public int getBalance(Node node) {
        return (node == null) ? 0 : height(node.right) - height(node.left);
    }
    //This method is used to print the AVL Tree in-order
    public void printInorder(Node root) {
        if(root != null) {
            printInorder(root.left);
            System.out.println(root.websiteName + ", " + " IP Addresses: " + root.ips.toString());
            printInorder(root.right);
        }
    }
    //Auxillary method used to check if the tree is empty
    public boolean isEmpty(){
        return root == null;
    }
    //This is a helper method used to search for an IP using the recursive Method
    public Node searchForIP (String IP ) {
        Node current = searchForIPRecursion(IP , root );
        if (current != null){
            return current;
        }else {
            return null ;
        }
    }
    //This is the recursive method, first it checks whether the root is the item in search. It returns the root if it's true,
    //if not, it starts checking the left-hand side of the tree and the right-hand side of the tree recursively

    public Node searchForIPRecursion (String IP  , Node root){
        if ( root == null){
            return root ;
        }
        boolean x = root.ips.search(IP);
        if (x){
            return root ;
        }
        Node lhs = searchForIPRecursion(IP , root.left );
        if ( lhs != null && lhs.ips.search(IP)){
            return lhs ;
        }
        Node rhs = searchForIPRecursion(IP , root.right);
        if ( rhs != null && rhs.ips.search(IP)) {
            return rhs;
        }
        return root ;
    }


}