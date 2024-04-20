import java.util.LinkedList;
import java.util.Queue;

public class MazeSolver {
        //static int r, c;
        static int r=50;
        static int c=50;

        public static void printPath(String[][] grid, Node start, Node end)
        {
            Node endNode = end.parent;

            while (!endNode.equals(start))
            {
                grid[endNode.row][endNode.col] = String.valueOf('*');
                endNode = endNode.parent;
            }

            printGrid(grid);
        }

        public static boolean isInvalidPoint(String[][] grid, Node point)
        {
            //System.out.println("log:" +"row:"+ point.row+" col:"+ point.col);
            return point.row < 0 || point.row >= r || point.col < 0 || point.col >= c ||
                    grid[point.row][point.col] == "x" ||
                    grid[point.row][point.col] == "Z" ||
                    String.valueOf(grid[point.row][point.col]) == "Y";
        }

        public static void BFS(String[][] grid, int Hx,int Hy, int Ax,int Ay)
        {

            Point start = new Point(), end = new Point();
            start.set(Hx,Hy);
            end.set(Ax,Ay);

            System.out.println();

            Node head = new Node(start.row, start.col);
            Node apple = new Node(end.row, end.col);

            // BFS
            // Offsets used to generate neighboring cells
            int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            boolean[][] visited = new boolean[r][c]; // All cells marked to false initially
            Queue<Node> toVisit = new LinkedList<>();

            toVisit.add(head);
            visited[head.row][head.col] = true;

            while (!toVisit.isEmpty())
            {
                Node topNode = toVisit.poll();

                if (topNode.equals(apple))
                {
                    printPath(grid, head, topNode);
                    return;
                }

                for (int[] off : adj)
                {
                    Node nb = new Node(topNode.row + off[0], topNode.col + off[1]);

                    if (isInvalidPoint(grid, nb) || visited[nb.row][nb.col])
                        continue;

                    visited[nb.row][nb.col] = true;
                    toVisit.add(nb);
                    nb.parent = topNode;
                }
            }

            //System.out.println("No path to goal found");
        }

        public static void printGrid(String[][] grid)
        {
            //Logger.logIntMap(grid);
            /*for (String[] ints : grid)
            {
                for (int j = 0; j < c; j++)
                    System.out.print(ints[j] + " ");

                System.out.println();
            }*/
        }
}
