package com.learning.spring.jpa.service;

import com.learning.spring.jpa.entities.Point;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BotRunner {
    Map<Integer, Integer> prevMap = new HashMap<>();
    public Point makeMove(int[][] inputBoard, Integer player){
//        int [][] inputBoard = convertToBoard(boardString);
        prevMap.put(1, 3);
        prevMap.put(2, 1);
        prevMap.put(3, 2);
        Point move;
//        check for current player's win:
        for(int i = 0;i<5;i++){
            for(int j=0;j<5;j++){
                if(inputBoard[i][j]==0){
                    inputBoard[i][j]=player;
                    if(checkWinner(inputBoard, player) == player){
//                        return "" + i + j;
                        return new Point(i, j);
                    }
                    inputBoard[i][j]=0;
                }
            }
        }

//        check for prev player's win:
        for(int i = 0;i<5;i++){
            for(int j=0;j<5;j++){
                int nextPlayer = (player%3) + 1;
                int prevPlayer = prevMap.get(player);
                if(inputBoard[i][j]==0){
                    inputBoard[i][j]=nextPlayer;
                    if(checkWinner(inputBoard, nextPlayer) == nextPlayer){
//                        return "" + i + j;
                        inputBoard[i][j] = player;
                        if(checkWinner(inputBoard, prevPlayer) != prevPlayer){
                            return new Point(i, j);
                        }
                    }
                    inputBoard[i][j]=0;
                }
            }
        }
//        create weight matrix to make the optimal move
        move = makeOptimalMove(inputBoard, player);
        if(move.getX() < 0 || move.getY() < 0 ){
            move = makeRandomMove(inputBoard);
        }
        return move;
//        return ""+ move.getX() + move.getY();
    }

    public Point makeRandomMove(int[][] board) {
        List<Point> pointsAvailable = new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(board[i][j]==0){
                    Point point = new Point(i, j);
                    pointsAvailable.add(point);
                }
            }
        }
        Random rand = new Random();
        int index = rand.nextInt(pointsAvailable.size());
        Point targetPoint = new Point(pointsAvailable.get(index).getX(), pointsAvailable.get(index).getY());
        return targetPoint;
    }

    public Point makeOptimalMove(int[][] board, Integer player) {

        int nextPlayer = (player %3) + 1;


        int [][] weight = new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)weight[i][j] = 0;
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(board[i][j] == player || board[i][j] == nextPlayer){
                    weight = assignWeight(weight, 2, new Point(i, j));
                }else{
                    weight = assignWeight(weight, -1, new Point(i, j));
//                    weight = assignKnightweight(weight, 1, new Point(i, j));
                }
            }
        }
        Point move = new Point(-1, -1);
        int maxWt = -1000;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(weight[i][j] > maxWt && board[i][j]==0){
                    move.setX(i);
                    move.setY(j);
                }
            }
        }
        return move;

    }

    public int[][] assignKnightweight(int[][] weight, int wt, Point point) {
        int []xx = new int[]{ 2, 1, -1, -2, -2, -1, 1, 2 };
        int []yy = new int[]{ 1, 2, 2, 1, -1, -2, -2, -1 };
        int x = point.getX();
        int y = point.getY();

        for(int i=0; i<8; i++){
            int px = x + xx[i];
            int py = y + yy[i];
            if(px>=0 && px<5 && py>=0 && py<5){
                weight[px][py] += wt;
            }
        }
        return weight;
    }

    public int[][] assignWeight(int[][] weight, int wt, Point point) {
        int []xx = new int[]{ 1,-1, 0, 0, 1, 1, -1, -1 };
        int []yy = new int[]{ 0, 0, 1, -1, 1, -1, 1, -1 };
        int x = point.getX();
        int y = point.getY();

        for(int i=0; i<8; i++){
            int px = x + xx[i];
            int py = y + yy[i];
            if(px>=0 && px<5 && py>=0 && py<5){
                weight[px][py] += wt;
            }
        }
        for(int i=0; i<8; i++){
            int px = x + (xx[i] * 2);
            int py = y + (yy[i] * 2);
            if(px>=0 && px<5 && py>=0 && py<5){
                weight[px][py] += (wt/2);
            }
        }
        return weight;
    }

    private int[][] convertToBoard(String boardString) {
        int [][] inputBoard = new int[5][5];
        int cnt = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                inputBoard[i][j] = boardString.charAt(cnt) - '0';
                cnt++;
            }
        }
        return inputBoard;
    }
    int checkWinner(int[][] board, Integer player){
        int [] currentWindow = new int[4];
        for(int i = 0; i<5; i++){
            for(int j = 0; j<2;j++){
                currentWindow = new int[]{board[i][j],board[i][j+1], board[i][j+2], board[i][j+3]};
                int ans = checkWindow(currentWindow, player);
                if(ans>0)return ans;

            }
        }
//        check vertical
        for(int i = 0; i<5; i++){
            for(int j = 0; j<2;j++){
                currentWindow = new int[]{board[j][i],board[j+1][i], board[j+2][i], board[j+3][i]};
                int ans = checkWindow(currentWindow, player);
                if(ans>0)return ans;
            }
        }
//        check diagonal
        currentWindow = new int[]{board[0][0],board[1][1], board[2][2], board[3][3]};
        int ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[1][1], board[2][2], board[3][3], board[4][4]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[0][4], board[1][3], board[2][2], board[3][1]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[1][3], board[2][2], board[3][1], board[4][0]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[1][0],board[2][1], board[3][2], board[4][3]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[0][1], board[1][2], board[2][3], board[3][4]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[0][3], board[1][2], board[2][1], board[3][0]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        currentWindow = new int[]{board[1][4], board[2][3], board[3][2], board[4][1]};
        ans = checkWindow(currentWindow, player);
        if(ans>0)return ans;

        return 0;
    }
    int checkWindow(int[] currentWindow, int player) {
        Set<Integer> windowSet = new HashSet<>();
        for(int i: currentWindow)windowSet.add(i);

        if(windowSet.size()>2)return 0;
        if(windowSet.size()==1 && !windowSet.contains(0)){
            for(Integer i:windowSet)return i;
        }
        Map<Integer, Integer> prevMap = new HashMap<>();
        prevMap.put(1, 3);
        prevMap.put(2, 1);
        prevMap.put(3, 2);
        if(windowSet.size()==2 && !windowSet.contains(0)){
            if(windowSet.contains(player)){
                int nextPlayer = (player + 1) % 3;
                int prevPlayer = prevMap.get(player);
                if(windowSet.contains(nextPlayer))return player;
                else return prevPlayer;
            }
            for(Integer i:windowSet)return i;
        }
        return 0;
    }
}
