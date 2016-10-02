package mancala;

public class Bin {
	private int pieces;
	private boolean store;
	private int owner;
	
	public Bin(int pieces, boolean store, int owner){
		this.pieces = pieces;
		this.store = store;
		this.owner = owner;
	}
	
	public void empty(){
		pieces = 0;
	}
	
	public int getPieces(){
		return pieces;
	}
	
	public void addPiece(){
		pieces++;
	}
	
	public void addPieces(int num){
		for(int i = 0; i < num; i++){
			addPiece();
		}
	}
	
	public int getOwner(){
		return owner;
	}
	
	public boolean isStore(){
		return store;
	}
	
	public void setPieces(int numPieces){
		pieces = numPieces;
	}
	
	public void setAsStore(){
		store = true;
	}
}

