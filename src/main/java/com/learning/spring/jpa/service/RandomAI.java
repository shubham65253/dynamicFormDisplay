package com.learning.spring.jpa.service;

import com.learning.spring.jpa.entities.Point;
import org.hibernate.internal.util.collections.IdentitySet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

public class RandomAI {
//    public int[][] board = new int[5][5];
    private Set<Point> pointSet;
    private List<Point> points = new ArrayList<>();
    public void init(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
//                points.add(new Point(i, j));
//                board[i][j]=0;
            }
        }
        pointSet = new HashSet<>(points);
    }
    public Point nextMove(Integer player, int[][]board){
        int sum = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)
                sum+=board[i][j];
        }
        if(sum<14){
            Random rand = new Random();
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(board[i][j]==0)
                        points.add(new Point(i, j));
                }
            }
            int index = rand.nextInt(points.size());
            Point targetPoint = new Point(points.get(index).getX(), points.get(index).getY());
            return targetPoint;
        }
        BotRunner botRunner = new BotRunner();
        int [][]cpBoard = new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)cpBoard[i][j] = board[i][j];
        }
        Point targetPoint = botRunner.makeMove(cpBoard,player);
        board[targetPoint.getX()][targetPoint.getY()] = player;
        return targetPoint;
    }
    public int checkWinner(Integer player, int[][]board){
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

    private int checkWindow(int[] currentWindow, int player) {
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

    public void printBoard(int [][] board) {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(board[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public String flattenBoard(int id, int winner, int firstPlayer, int [][] board) {
        String ret = "" + id;
        ret = ret + ", ";
        ret = ret + firstPlayer + ", ";
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                ret += (board[i][j] + ", ");
            }
        }
        return ret;
    }
}
