import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import za.ac.wits.snake.DevelopmentAgent;
import za.ac.wits.snake.ZombieSnake;

class MyAgent extends DevelopmentAgent {

    public static void main(String args[]) {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public static void drawSnake(String snake, int snakeNo, String[][] Board) {
        for (int i = 1; i < snake.split(" ").length; i++) {
            Board = drawLine(Board, (snake.split(" "))[i], (snake.split(" "))[i - 1], snakeNo);
        }
    }

    public static void drawOtherSnake(String snake, String[][] Board) {
        for (int i = 1; i < snake.split(" ").length; i++) {
            Board = drawOtherLine(Board, (snake.split(" "))[i], (snake.split(" "))[i - 1]);
        }
    }

    public static void drawZombie(String snake, String[][] Board) {
        for (int i = 1; i < snake.split(" ").length; i++) {
            Board = drawZLine(Board, (snake.split(" "))[i], (snake.split(" "))[i - 1]);
        }
    }

    public static String[][] drawZLine(String[][] Board, String coord1, String coord2) {
        int x1, x2, y1, y2;
        // Splitting the coordinates up
        x1 = Integer.parseInt(coord1.split(",")[0]);
        x2 = Integer.parseInt(coord2.split(",")[0]);
        y1 = Integer.parseInt(coord1.split(",")[1]);
        y2 = Integer.parseInt(coord2.split(",")[1]);

        if (x1 == x2) {
            if (y1 > y2) {
                for (int i = y2; i <= y1; i++) {
                    Board[i][x1] = "Z";
                }
            } else {
                for (int i = y2; i >= y1; i--) {
                    Board[i][x1] = "Z";
                }
            }
        } else {
            if (x1 > x2) {
                for (int i = x2; i <= x1; i++) {
                    Board[y1][i] = "Z";
                }
            } else {
                for (int i = x2; i >= x1; i--) {
                    Board[y1][i] = "Z";
                }
            }
        }
        return Board;

    }

    public static String[][] drawLine(String[][] Board, String coord1, String coord2, int snakeNo) {
        int x1, x2, y1, y2;
        // Splitting the coordinates up
        x1 = Integer.parseInt(coord1.split(",")[0]);
        x2 = Integer.parseInt(coord2.split(",")[0]);
        y1 = Integer.parseInt(coord1.split(",")[1]);
        y2 = Integer.parseInt(coord2.split(",")[1]);

        if (x1 == x2) {
            if (y1 > y2) {
                for (int i = y2; i <= y1; i++) {
                    Board[i][x1] = Integer.toString(snakeNo);
                }
            } else {
                for (int i = y2; i >= y1; i--) {
                    Board[i][x1] = Integer.toString(snakeNo);
                }
            }
        } else {
            if (x1 > x2) {
                for (int i = x2; i <= x1; i++) {
                    Board[y1][i] = Integer.toString(snakeNo);
                }
            } else {
                for (int i = x2; i >= x1; i--) {
                    Board[y1][i] = Integer.toString(snakeNo);
                }
            }
        }
        return Board;

    }


    public static String[][] drawOtherLine(String[][] Board, String coord1, String coord2) {
        int x1, x2, y1, y2;
        // Splitting the coordinates up
        x1 = Integer.parseInt(coord1.split(",")[0]);
        x2 = Integer.parseInt(coord2.split(",")[0]);
        y1 = Integer.parseInt(coord1.split(",")[1]);
        y2 = Integer.parseInt(coord2.split(",")[1]);

        if (x1 == x2) {
            if (y1 > y2) {
                for (int i = y2; i <= y1; i++) {
                    Board[i][x1] = "Y";
                }
            } else {
                for (int i = y2; i >= y1; i--) {
                    Board[i][x1] = "Y";
                }
            }
        } else {
            if (x1 > x2) {
                for (int i = x2; i <= x1; i++) {
                    Board[y1][i] = "Y";
                }
            } else {
                for (int i = x2; i >= x1; i--) {
                    Board[y1][i] = "Y";
                }
            }
        }
        return Board;

    }

    private static void printBoard(String[][] Board) {
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if (j + 1 == Board[i].length) {
                    System.out.print(Board[i][j]);
                } else {
                    System.out.print(Board[i][j] + " ");
                }

            }
            System.out.println();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////


    public int getMove(Point from, Point to) {
        if (from.row != to.row)
            if (from.row < to.row) {
                return 1; //move down
            } else {
                return 0; //move up
            }
        else {
            if (from.col < to.col) {
                return 3; //move right
            } else {
                return 2; //move left
            }
        }
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            int applex1 =0;
            int appley1 =0;


            while (true) {
                String line = br.readLine();

                String[][] GameBoard = new String[50][50];

                if (line.contains("Game Over")) {
                    break;
                }

                String apple1 = line;

                //do stuff with apples
                //System.out.println("log" + apple1);
                applex1 = Integer.parseInt(apple1.split(" ")[0]);
                appley1 = Integer.parseInt(apple1.split(" ")[1]);
                GameBoard[appley1][applex1] = "a";

                for (int zombie=0; zombie<6; zombie++) {
                    String zombieLine=br.readLine();
                    drawZombie(zombieLine, GameBoard);
                }

                String MySnakePosition = "";
                String OtherSnakePosition = "";

                int MySnakeHeadx = 0;
                int MySnakeHeady = 0;

                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();

                    MySnakePosition = "";
//                    int MySnakeHeadx = 0;
//                    int MySnakeHeady = 0;

                    if (i == mySnakeNum) {
                        //hey! That's me :)
                        String[] MySnakeArray = snakeLine.split(" ");

                        String[] bufferSnake = new String[MySnakeArray.length - 3];

                        for (int c = 3; c < MySnakeArray.length; ++c) {
                            bufferSnake[c-3] = MySnakeArray[c];
                        }

                        MySnakePosition += bufferSnake[0];

                        for (int b = 1; b < bufferSnake.length; ++b) {
                            MySnakePosition += " " + bufferSnake[b];
                        }
                        //System.out.println("log" + SnakePosition);
                        //String MyStatus = snakeLine.split(" ")[0];
                        //System.out.println("log" + MyStatus);
                        //String MyLength = snakeLine.split(" ")[1];
                        //String MyKills = snakeLine.split(" ")[2];

                        //String MySnakePosition = snakeLine.split(" ")[3];
//                        for (String a : MySnakeArray){
//                            System.out.println("log" + a);
//                        }

                        //System.out.println("log" + MySnakeArray[2]);
                        //System.out.println("log" + MySnakePosition);
                        drawSnake(MySnakePosition,mySnakeNum,GameBoard);

                        //Getting Head
                        MySnakeHeadx = Integer.parseInt(MySnakeArray[3].split(",")[0]);
                        MySnakeHeady = Integer.parseInt(MySnakeArray[3].split(",")[1]);
                        //GameBoard[MySnakeHeady][MySnakeHeadx] = "D";
                        //System.out.println("log" + "1: "+GameBoard[MySnakeHeady][MySnakeHeadx]);
                    }

                    //do stuff with other snakes
                    String[] OtherSnakeArray = snakeLine.split(" ");
                    String OtherSnakeStatus = snakeLine.split(" ")[0];
                    //String OtherSnakePosition = snakeLine.split(" ")[1];
                    if (OtherSnakeStatus.equals("alive")){
                        OtherSnakePosition = "";

                        String[] bufferOtherSnake = new String[OtherSnakeArray.length - 3];

                        for (int c = 3; c < OtherSnakeArray.length; ++c) {
                            bufferOtherSnake[c-3] = OtherSnakeArray[c];
                        }

                        OtherSnakePosition += bufferOtherSnake[0];

                        for (int b = 1; b < bufferOtherSnake.length; ++b) {
                            OtherSnakePosition += " " + bufferOtherSnake[b];
                        }

                        drawOtherSnake(OtherSnakePosition,GameBoard);
//                        System.out.println("log" + "2X: "+MySnakeHeadx);
//                        System.out.println("log" + "2Y: "+MySnakeHeadx);
//                        System.out.println("log" + "2: "+GameBoard[MySnakeHeady][MySnakeHeadx]);

                    }
                    else{
                        continue;
                    }
                   GameBoard[MySnakeHeady][MySnakeHeadx] = "D";
                }


                //finished reading, calculate move:
                MazeSolver.BFS(GameBoard,MySnakeHeady,MySnakeHeadx,appley1,applex1);

                Point start = new Point(), end = new Point();
                start.set(MySnakeHeady,MySnakeHeadx);
                end.set(appley1,applex1);

                int move = getMove(start,end);


                //int move = new Random().nextInt(4);

                // Printing out the moves in text files
                Logger.logIntMap(GameBoard);

                System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}