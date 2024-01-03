package game2048;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Board {
	int[][] board;
	private int size;
	Scanner sc;
	
	public Board() {
		this.size = 5;
		this.board = new int[this.size][this.size];
		sc = new Scanner(System.in);
	}
	
	public Board(int size) {
		this.size = size;
		this.board = new int[this.size][this.size];
		sc = new Scanner(System.in);
	}
	
	public void spawnRandom2() {
		Random rn = new Random();
		int i = Math.abs(rn.nextInt()) % this.size;
		int j = Math.abs(rn.nextInt()) % this.size;
		while(this.board[i][j] != 0) {
			i = Math.abs(rn.nextInt()) % this.size;
			j = Math.abs(rn.nextInt()) % this.size;
		}
		this.board[i][j] = 2;
	}
	
	// 0 - game not over
	// 1 - game over (loss)
	// 2 - game over (win)
	public int gameOver() {
		int result = 1;
		for(int i=0; i<this.board.length; i++) {
			for(int j=0; j<this.board.length; j++) {
				if(this.board[i][j]==0) result=0;
				else if(this.board[i][j]==2048) return 2;
			}
		}
		return result;
	}
	
	public void printBoardSimple() {
		String line="";
		int spaces = 0;
		for(int i=0; i<this.board.length; i++) {
			line = "|";
			for(int j=0; j<this.board.length; j++) {
				line+=(((this.board[i][j]!=0)?this.board[i][j]:" ") + "\t");
			}
//			if(adjust spaces so that the \t can be eliminated)
			line=line.substring(0,line.length()-1)+"\t";
			line+="|\n|\t\t\t\t|";
			if(i==this.board.length-1) {
				System.out.println(line.substring(0, line.length()-7));
			}	else {
				System.out.println(line);
		
			}
		}
	}
	
	public void adjustLeft() {
		for(int i=0; i<this.board.length; i++) {
			for(int j=0; j<this.board.length; j++) {
				if(this.board[i][j] != 0) {
					for(int k=j+1; k<this.board.length; k++) {
						if(this.board[i][j]==this.board[i][k]) {
							this.board[i][j] = this.board[i][j] + this.board[i][k];
							this.board[i][k] = 0;
						}
					}
				}
			}
		}
		for(int i=0; i<this.board.length; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int j=0; j<this.board.length; j++) {
				if(this.board[i][j]!=0) {
					arr.add(this.board[i][j]);
				}
			}
			for(int j=0; j<this.board.length; j++) {
				this.board[i][j] = (j<arr.size()) ? arr.get(j) : 0;
			}
		}
	}
	
	public void adjustUp() {
		for(int i=0; i<this.board.length; i++) {
			for(int j=0; j<this.board.length; j++) {
				if(this.board[j][i] != 0) {
					for(int k=j+1; k<this.board.length; k++) {
						if(this.board[j][i]==this.board[k][i]) {
							this.board[j][i] = this.board[j][i] + this.board[k][i];
							this.board[k][i] = 0;
						}
					}
				}
			}
		}
		for(int i=0; i<this.board.length; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int j=0; j<this.board.length; j++) {
				if(this.board[j][i]!=0) {
					arr.add(this.board[j][i]);
				}
			}
			for(int j=0; j<this.board.length; j++) {
				this.board[j][i] = (j<arr.size()) ? arr.get(j) : 0;
			}
		}
	}
	
	public void adjustRight() {
		for(int i=0; i<this.board.length; i++) {
			for(int j=this.board.length-1; j>=0; j--) {
				if(this.board[i][j] != 0) {
					for(int k=j-1; k>=0; k--) {
						if(this.board[i][j]==this.board[i][k]) {
							this.board[i][j] = this.board[i][j] + this.board[i][k];
							this.board[i][k] = 0;
						}
					}
				}
			}
		}
		for(int i=0; i<this.board.length; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int j=this.board.length-1; j>=0; j--) {
				if(this.board[i][j]!=0) {
					arr.add(this.board[i][j]);
				}
			}
			for(int j=this.board.length-1; j>=0; j--) {
				this.board[i][j] = ((this.board.length-j-1)<arr.size()) ? arr.get(this.board.length-j-1) : 0;
			}
		}
	}
	
	public void adjustDown() {
		for(int i=0; i<this.board.length; i++) {
			for(int j=this.board.length-1; j>=0; j--) {
				if(this.board[j][i] != 0) {
					for(int k=j-1; k>=0; k--) {
						if(this.board[j][i]==this.board[k][i]) {
							this.board[j][i] = this.board[j][i] + this.board[k][i];
							this.board[k][i] = 0;
						}
					}
				}
			}
		}
		for(int i=0; i<this.board.length; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int j=this.board.length-1; j>=0; j--) {
				if(this.board[j][i]!=0) {
					arr.add(this.board[j][i]);
				}
			}
			for(int j=this.board.length-1; j>=0; j--) {
				this.board[j][i] = ((this.board.length-j-1)<arr.size()) ? arr.get(this.board.length-j-1) : 0;
			}
		}
	}
	
	public char getNextMove() {
		char c;
		System.out.print("Enter you next move (a/A=Left, d/D=Right, w/W=Up, s/S=Down):");
		c = this.sc.next().toLowerCase().charAt(0);
		return c;
	}
	
	public void makeMove(char c) throws Exception {
		switch(c) {
		case 'w':
			this.adjustUp();
			break;
		case 's':
			this.adjustDown();
			break;
		case 'a':
			this.adjustLeft();
			break;
		case 'd':
			this.adjustRight();
			break;
		default:
			throw new Exception("Exception in making move");
		}
	}
	
	public void loadLosingGameDemo() {
		this.board[0][0] = 2;
		this.board[0][1] = 4;
		this.board[0][2] = 2;
		this.board[0][3] = 4;
		this.board[1][0] = 2;
		this.board[1][1] = 4;
		this.board[1][2] = 2;
		this.board[1][3] = 4;
		this.board[2][0] = 2;
		this.board[2][1] = 4;
		this.board[2][2] = 2;
		this.board[2][3] = 4;
		this.board[3][0] = 2;
		this.board[3][1] = 4;
		this.board[3][2] = 2;
		this.board[3][3] = 4;
	}
	
	public void loadWinningGameDemo() {
		this.board[0][1] = 2048;
	}
	
	public static void main(String[] args) {
		Board b = new Board(4);
		b.spawnRandom2();
//		b.loadLosingGameDemo();
//		b.loadWinningGameDemo();
		b.printBoardSimple();
		int status=0;
		while((status=b.gameOver())==0) {
			try {
				b.makeMove(b.getNextMove());
			} catch (Exception e) {
				System.out.println("Please input a valid move");
				continue;
			}
			b.spawnRandom2();
			b.printBoardSimple();
		}
		if(status==2) System.out.println("Congratulations! YOU WIN!");
		else System.out.println("Game Over");
		b.sc.close();
	}

	

}
