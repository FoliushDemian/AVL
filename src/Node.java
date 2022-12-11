
public class Node {
    public Integer height;
    public Range range;
    public Node left;
    public Node right;

    public Integer data;

    public Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node rightRotate() {
        Node rightChild = this.right;
        Node beta = this.right.left;
        this.right.left = this;
        this.right = beta;
        return rightChild;
    }

    public Node leftRotate() {
        Node leftChild = this.left;
        Node beta = this.left.right;
        this.left.right = this;
        this.left = beta;
        return leftChild;
    }

    public int height() {

        int leftSubtreeHeight = 0;
        int rightSubtreeHeight = 0;

        if (this.left != null)
            leftSubtreeHeight = this.left.height() + 1;

        if (this.right != null) {
            rightSubtreeHeight = this.right.height() + 1;
        }

        return Math.max(leftSubtreeHeight, rightSubtreeHeight);
    }

    int findBalanceOfNode() {

        int leftHeight = -1;
        int rightHeight = -1;

        if (this.left != null) {
            leftHeight = this.left.height() +1;
        }

        if (this.right != null) {
            rightHeight = this.right.height() +1;
        }

        return leftHeight - rightHeight;
    }


    public Node balanceSubTree() {
        if (this.findBalanceOfNode() > 1) {
            if (this.left.findBalanceOfNode() < 0) {                       //left heavy
                this.left = this.left.rightRotate();                             //left right rotate
            }
            return this.leftRotate();                                 //right rotate
        }

        if (this.findBalanceOfNode() < -1) {
            if (this.right.findBalanceOfNode() > 0) {                //right heavy
                this.right = this.right.leftRotate();
            }
            return this.rightRotate();                                   //right rotate
        }

        return this;
    }

    public Node insertNode(Integer data) {
        if (this.data > data) {
            if (this.left == null) {
                this.left = new Node(data);
            } else {
                this.left = this.left.insertNode(data);
            }
        } else if (this.data < data) {
            if (this.right == null) {
                this.right = new Node(data);
            } else {
                this.right = this.right.insertNode(data);
            }
        } else {
            System.out.println("Already Inserted " + data);
        }

        return this.balanceSubTree();
    }

    public Node delete(Integer data) {
        if (data < this.data) {
            if (this.left == null) {
                System.out.println("Nothing to delete");
            } else {
                this.left = this.left.delete(data);
                this.balanceSubTree();
            }
        } else if (data > this.data) {
            if (this.right == null) {
                System.out.println("Nothing to delete");
            } else {
                this.right = this.right.delete(data);
                this.balanceSubTree();
            }

        } else {

            if (this.right == null && this.left == null) {
                return null;
            }

            if (this.right == null) {
                return this.left;
            }

            if (this.left == null) {
                return right;
            }

            Node largestLeftChild = this.left;
            while (largestLeftChild.right != null) {
                largestLeftChild = largestLeftChild.right;
            }


            this.data = largestLeftChild.data;
            this.left = this.left.delete(largestLeftChild.data);
        }
        return this;
    }

    public void setRanges(Range range) {
        this.range = range;

        if (this.left != null) {
            this.left.height = this.height + 1;
            this.left.setRanges(new Range(this.range.leftBorder, this.range.middle()));
        }

        if (this.right != null) {
            this.right.height = this.height + 1;
            this.right.setRanges(new Range(this.range.middle(), this.range.rightBorder));
        }


    }

    @Override
    public String toString() {
        return "Node data = " + data;
    }

}