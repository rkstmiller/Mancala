package mancala;

import java.util.ArrayList;

import guiapplication.GameWindow;

public class Mancala {
	private ArrayList<Bin> binList;
	private int player;
	private final int PLAYER1_STORE = 6;
	private final int PLAYER2_STORE = 13;
	
	public Mancala(){
		newGame();
	}
	
	public void newGame(){
		player = 1;  // Sets player 1 to take first turn
		
		binList = new <Bin>ArrayList();
		
		// Adding bins/stores to the binList
		for(int i = 0; i < 6; i++){
			binList.add(new Bin(4, false, 1));
		}
		binList.add(new Bin(0, true, 1));
		for(int i = 7; i < 13; i++){
			binList.add(new Bin(4, false, 2));
		}
		binList.add(new Bin(0, true, 2));
	}
	
	public void move(int selectedBin){
		
		// standard move procedure
		int i = 1;
		for( ;i <= binList.get(selectedBin).getPieces(); i++){
			if(binList.get((selectedBin+i)%14).getOwner() == this.player || !binList.get((selectedBin+i)%14).isStore())
				binList.get((selectedBin+i)%14).addPiece();
		}
		binList.get(selectedBin).empty();
		
		// Testing if capture is needed
		if(getCurrentPlayer() == 1){
			if(binList.get((selectedBin+i-1)%14).getPieces() == 1 && ((selectedBin+i-1)%14 < 6) && ((selectedBin+i-1)%14 >= 0)){
				capture((selectedBin+i-1)%14);
			}
		}
		else{
			if(binList.get((selectedBin+i-1)%14).getPieces() == 1 && ((selectedBin+i-1)%14 > 6) && ((selectedBin+i-1)%14 < 13)){
				capture((selectedBin+i-1)%14);
			}
		}
		
		// Setting next player unless player gets to repeat turn
		if(!((selectedBin+i-1)%14 == 13) && !((selectedBin+i-1)%14 == 6)){
			setNextPlayer();
		}
		
		// testing if game is over and taking necessary steps if it is
		if(isEndGame()){
			GameWindow.board.displayWinner(getWinner());
		}
	}
	
	public int getWinner(){
		for(int i = 0; i < 6; i++){
			binList.get(PLAYER1_STORE).addPieces(binList.get(i).getPieces());
			binList.get(i).empty();
			GameWindow.board.middleBins[i+6].clearMarbles();
		}
		for(int i = 7; i < 13; i++){
			binList.get(PLAYER2_STORE).addPieces(binList.get(i).getPieces());
			binList.get(i).empty();
			GameWindow.board.middleBins[12-i].clearMarbles();
		}
		if(getScore(1) > getScore(2))
			return 1;
		else
			return 2;
	}
	
	public boolean isBinEmpty(int index){
		if(binList.get(index).getPieces() == 0)
			return true;
		return false;
	}
	
	public void capture(int currentBin){
		int oppositeBin = 12 - currentBin;
		if(binList.get(oppositeBin).getPieces() != 0){
			binList.get(currentBin).empty();
			if(player == 1)
				binList.get(PLAYER1_STORE).addPieces(binList.get(oppositeBin).getPieces()+1);
			else
				binList.get(PLAYER2_STORE).addPieces(binList.get(oppositeBin).getPieces()+1);
			binList.get(oppositeBin).empty();
			if(oppositeBin > 6){
				GameWindow.board.middleBins[12-oppositeBin].clearMarbles();
			}
			else
				GameWindow.board.middleBins[oppositeBin + 6].clearMarbles();
		}
	}
	
	/**
     * This method takes an input, index, and returns the number of pieces in the
     * corresponding Bin.
     * @param int index
     */
	public int getPieces(int index){
		return binList.get(index).getPieces();
	}
	
    /** This checks if either players non-scoring bins are empty.
     * 
     * @return boolean
     */
    public boolean isEndGame(){
        // set temporary variables to store the sum of player Bins
        int p1BinSum=0;
        int p2BinSum=0;
        // Loop through the bins and sum up number of pieces per player
        for(int i=0;i<6;i++){
            p1BinSum+=binList.get(i).getPieces();
            p2BinSum+=binList.get(12-i).getPieces();   
        }
        //if the sum of non-scoring bins=0 for either player this will return true
        if(p1BinSum ==0 || p2BinSum==0){
            return true;
        }
        return false;
    }
    
    /**
     * Changes player from 1 to 2 or vice versa
     * @return void
     */
    public void setNextPlayer(){  
        player=2-player+1;   
    }
    
    /**Returns current Player
     * @return integer
     */
    public int getCurrentPlayer(){
        return player;
    }
    
    /**Returns score of current player
     * @param integer player
     * @return integer score
     */
    public int getScore(int player){
        return binList.get(player*7-1).getPieces();
    }
    
    // This method is only for testing purposes
    public void print(){
    	System.out.println(binList.get(13).getPieces());
    	System.out.println(binList.get(0).getPieces() + "\t" + binList.get(12).getPieces());
    	System.out.println(binList.get(1).getPieces() + "\t" + binList.get(11).getPieces());
    	System.out.println(binList.get(2).getPieces() + "\t" + binList.get(10).getPieces());
    	System.out.println(binList.get(3).getPieces() + "\t" + binList.get(9).getPieces());
    	System.out.println(binList.get(4).getPieces() + "\t" + binList.get(8).getPieces());
    	System.out.println(binList.get(5).getPieces() + "\t" + binList.get(7).getPieces());
    	System.out.println(binList.get(6).getPieces());
    }
}
