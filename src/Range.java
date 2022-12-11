public class Range {
    int leftBorder;
    int rightBorder;

    Range(int leftBorder, int rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public int middle() {
        return (int) Math.floor( (leftBorder + rightBorder)/ 2 );

    }
}
