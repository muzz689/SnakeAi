import java.io.*;

public class Logger {
    private static int count = 0;

    public static void log(String string) {
        if (string != null){
            BufferedWriter bw = null;
            try{
                File file = new File("logs/" + count + ".txt");
                if(!file.exists()){
                    file.createNewFile();
                    count++;
                }
                FileWriter writer = new FileWriter(file);
                bw = new BufferedWriter(writer);
                bw.write(string);
                bw.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void printBoard(int[][] map){
        String maps = "";
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (map[i][j] == 0) {
                    maps += " - ";
                }
                if (map[i][j] > 1) {
                    maps += " # ";
                } else if (map[i][j] == 1) {
                    maps += " M ";
                }
                if (map[i][j] == -3) {
                    maps += " * ";
                }
                if (map[i][j] == -1) {
                    maps += " G ";
                }
            }
            maps += "\n";
        }
        log(maps);
    }

    public static void logIntMap(String[][] board){
        String map = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j + 1 == board[0].length) {
                    if(board[i][j] == null){
                        map += "-" + " ";
                    }
                    else{
                        map += board[i][j];
                    }
                } else {
                    if(board[i][j] == null){
                        map += "-" + " " ;
                    }
                    else{
                        map += board[i][j] + " ";
                    }

                }
            }
            map += "\n";
        }
        log(map);
    }
}