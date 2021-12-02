package com.learning.spring.jpa;

import com.learning.spring.jpa.entities.Point;
import com.learning.spring.jpa.service.BotRunner;
import com.learning.spring.jpa.service.RandomAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringDataJpaLearningApplication {

	@Autowired
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringDataJpaLearningApplication.class, args);

		List<String> printList = new ArrayList<>();
//		int[][] board = new int[][]{{0, 0, 0, 0, 0},{0, 0, 0, 0, 0},{0, 0, 1, 3, 2},{1, 3, 2, 1, 3},{2, 1, 3, 2, 1}};
//		RandomAI randomAI = new RandomAI();
//		Point movement = randomAI.nextMove(2, board);
//		System.out.println(movement.getX() + " " + movement.getY());

		for(int iter = 700001;iter<750000; iter++) {
			RandomAI randomAI = new RandomAI();
			randomAI.init();
			int firstPlayer = 1;
			int winner = 0;
			List<String> l1 = new ArrayList<>();
			List<String> l2 = new ArrayList<>();
			List<String> l3 = new ArrayList<>();
			String flatBoard = "";
			Point move;
			String moveString;
			int [][]board = new int[][]{{0, 0, 0, 0, 0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
			for (int i = 0; i < 9; i++) {
//				p1
				flatBoard = randomAI.flattenBoard(iter, 1, firstPlayer, board);
				move = randomAI.nextMove(1, board);
				board[move.getX()][move.getY()] = 1;
				moveString = "" + move.getX() + ", " + move.getY();
//				System.out.println(moveString + move.getX() + ", " + move.getY());
				String rowString = flatBoard + moveString;
				l1.add(rowString);

//				randomAI.printBoard(board);
				int currentWinner = randomAI.checkWinner(1, board);
				if (currentWinner > 0) {
					if(currentWinner!=1)break;
					winner = currentWinner;
					for(String str: l1){
						str += ", ";
						str += winner;
						printList.add(str);
					}
					break;
				}
				if (i == 9) break;

//				p2
				flatBoard = randomAI.flattenBoard(iter, 2, firstPlayer, board);
				move =null;
				move = randomAI.nextMove(2, board);
				board[move.getX()][move.getY()] = 2;
				moveString = "" + move.getX() + ", " + move.getY();
//				System.out.println(moveString + move.getX() + ", " + move.getY());
				rowString = flatBoard + moveString;
				l2.add(rowString);
//				randomAI.printBoard(board);
				currentWinner = randomAI.checkWinner(2, board);
				if (currentWinner > 0) {
					if(currentWinner!=2)break;
					winner = currentWinner;
					for(String str: l2){
						str += ", ";
						str += winner;
						printList.add(str);
					}
					break;
				}

//				p3
				flatBoard = randomAI.flattenBoard(iter, 3, firstPlayer, board);
				move = null;
				move = randomAI.nextMove(3, board);
				board[move.getX()][move.getY()] = 3;
				moveString = "" + move.getX() + ", " + move.getY();
//				System.out.println(moveString + move.getX() + ", " + move.getY());
				rowString = flatBoard + moveString;
				l3.add(rowString);
//				randomAI.printBoard(board);
				currentWinner = randomAI.checkWinner(3, board);
				if (currentWinner > 0) {
					if(currentWinner!=3)break;
					winner = currentWinner;
					for(String str: l3){
						str += ", ";
						str += winner;
						printList.add(str);
					}
					break;
				}

			}
//			System.out.println("winner: \n"+ winner);

		}
		System.out.println(printList.size());
//		write output
		String fileName = "C:\\Users\\shubham\\OneDrive\\Desktop\\spring-data-jpa-learning\\spring-data-jpa-learning\\src\\main\\resources\\output.json";
			FileWriter writer = new FileWriter(fileName, true);
			for(String str: printList) {
				try {
					writer.write(str + System.lineSeparator());
//					BufferedWriter out = new BufferedWriter(
//							new FileWriter(fileName, true));
//
//					// Writing on output stream
//					out.write(str);
//					// Closing the connection
//					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			writer.close();
		System.out.println(printList.size());
	}

}
