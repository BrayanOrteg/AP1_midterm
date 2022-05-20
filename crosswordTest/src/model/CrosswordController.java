package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {
		crossword= new Cell [puzzle.length] [puzzle[0].length];
		int number=0;

		for (int i=0; i<puzzle.length;i++){
			for(int j=0;j<puzzle[0].length;j++){
				number++;
				if (puzzle[i][j].equals(" ")){
					number++;
					
					crossword[i][j]= new Cell (CellType.BLACK, puzzle[i][j],number);

				}else{
					crossword[i][j]= new Cell (CellType.CLOSED, puzzle[i][j],number);
				}
			}
		}
		
		
	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {
		boolean find=false;

		String out=" Sorry the crossword dont have any word with the letter: "+ letter;

		for (int i=0; i<crossword.length && find==false;i ++){
			for (int j=0; j<crossword[0].length && find==false; j ++){
				if(crossword[i][j].getState()==CellType.CLOSED && crossword[i][j].getLetter().equals(letter)){
					crossword[i][j].setState(CellType.OPEN);
					find=true;

					out="The crossword have a word with the letter: " + letter + " in the cell: " + crossword[i][j].getNumber();
				}

			}

		}
		return out;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		
		return null;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else if((actual.getState()==CellType.CLOSED)) {
						numbers += " "+actual.getNumber() +"   ";
						letters += "      ";
					}else{
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else if((actual.getState()==CellType.CLOSED)) {
						numbers += " "+actual.getNumber() +"    ";
						letters += "      ";
					}
					else{
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
