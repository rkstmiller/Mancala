package mancala;

import guiapplication.*;

public class MancalaLauncher {
	public static void main(String [] args){
		Mancala m = new Mancala();
		GameWindow window = new GameWindow();
		window.setMancala(m);
		window.main(args);
		//while(!m.isEndGame()){
			// get selectedBin from GUI
			//m.move(selectedBin);
			//m.setNextPlayer();
		//}
		//gui.displayWinner()
	}
}
