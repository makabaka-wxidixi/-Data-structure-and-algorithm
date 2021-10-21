import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-07 13:47
 */
public class DijkstraDemo {
    private static final int N = Character.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}};
        Group group = new Group(vertex, matrix);
        group.printGroup();
    }
}

class Group {
    private char[] vertex;
    private int[][] matrix;
    private int numOfVertex;

    public Group(char[] vertex, int[][] matrix) {
        this.numOfVertex = vertex.length;
        this.vertex = new char[numOfVertex];
        this.matrix = new int[numOfVertex][numOfVertex];
        for (int i = 0; i < numOfVertex; i++) {
            this.vertex[i] = vertex[i];
            for (int j = 0; j < numOfVertex; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public void printGroup() {
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = 0; j < numOfVertex; j++) {
                System.out.printf("%-8d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
