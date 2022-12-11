import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public Node root;

    public void insert(Integer data) {
        if (this.root == null) {
            this.root = new Node(data);
        } else {
            this.root = this.root.insertNode(data);
        }

    }

    public void delete(Integer data) {
        if (this.root == null)
            System.out.println("Empty Tree delete");
        else {
            this.root = this.root.delete(data);
        }

    }

    public void printTree() {
        this.root.height = 0;
        this.root.setRanges(new Range(0, (int) Math.pow(2, this.root.height() + 1) - 1));
        LinkedList<Node> output = this.createOutputArray();

        int previousHeight = 0;
        int indentation = 0;
        int ind;

        int cellLength = 4;
        char space = ' ';
        for (Node node : output) {
            if (previousHeight != node.height){
                System.out.println();
                previousHeight = node.height;
                indentation=0;
            }


            ind = indentation;
            for (int i = 0; i < node.range.middle() - ind; i++) {
                for (int j = 0; j < cellLength; j++) {
                    System.out.print(space);
                }
                indentation++;
            }

            for (int i = 0; i < cellLength - (node.data+"").length(); i++) {
                System.out.print(space);
            }

            indentation++;
            System.out.print(node.data);
        }

    }

    private LinkedList<Node> createOutputArray() {
        LinkedList<Node> result = new LinkedList<>();
        Queue<Node> next = new LinkedList<>();

        Node current;
        next.add(this.root);

        while (!next.isEmpty()) {
            current = next.poll();
            result.add(current);


            if (current.left != null) {
                next.add(current.left);
            }

            if (current.right != null) {
                next.add(current.right);
            }

        }

        return result;
    }

}


