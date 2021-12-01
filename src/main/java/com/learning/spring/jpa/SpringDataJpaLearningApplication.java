package com.learning.spring.jpa;

import com.learning.spring.jpa.entities.Point;
import com.learning.spring.jpa.service.RandomAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		for(int iter = 0;iter<10; iter++) {
			RandomAI randomAI = new RandomAI();
			randomAI.init();
			int firstPlayer = 1;
			int winner = 0;
			List<String> l1 = new ArrayList<>();
			List<String> l2 = new ArrayList<>();
			List<String> l3 = new ArrayList<>();
			String flatBoard = "";
			Point move;
			for (int i = 0; i < 9; i++) {
				flatBoard = randomAI.flattenBoard(iter, 1, firstPlayer);
				move = randomAI.nextMove(1);
				String moveString = "" + move.getX() + ", " + move.getY();
				String rowString = flatBoard + moveString;
				l1.add(rowString);

				randomAI.printBoard();
				int currentWinner = randomAI.checkWinner(1);
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
				flatBoard = randomAI.flattenBoard(iter, 2, firstPlayer);
				move = randomAI.nextMove(2);
				moveString = "" + move.getX() + ", " + move.getY();
				rowString = flatBoard + moveString;
				l2.add(rowString);
				randomAI.printBoard();
				currentWinner = randomAI.checkWinner(2);
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
				flatBoard = randomAI.flattenBoard(iter, 3, firstPlayer);
				randomAI.nextMove(3);
				moveString = "" + move.getX() + ", " + move.getY();
				rowString = flatBoard + moveString;
				l3.add(rowString);
				randomAI.printBoard();
				currentWinner = randomAI.checkWinner(3);
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
			System.out.println("winner: \n"+ winner);

		}

//		write output
			FileWriter writer = new FileWriter("C:\\Users\\shubham\\OneDrive\\Desktop\\spring-data-jpa-learning\\spring-data-jpa-learning\\src\\main\\resources\\output.json");
			for(String str: printList) {
				try {
					writer.write(str + System.lineSeparator());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			writer.close();

	}

}
