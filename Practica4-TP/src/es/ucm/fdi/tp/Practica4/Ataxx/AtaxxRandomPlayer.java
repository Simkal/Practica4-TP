package es.ucm.fdi.tp.Practica4.Ataxx;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxRandomPlayer extends Player {
	/**
	 * Numero de serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		GameMove movimiento = null;
		List<GameMove>movimientoValido = new ArrayList<GameMove>();		
		if(board.isFull()){
			throw new GameError("El tablero esta lleno, no se puede mover mas fichas");
		}
		else{
			try{ // Da una excepcion cuando no hay piezas, supongo que es porque no tengo en esa opcion en el Pair, pero mejor revisarlo
				if(board.getPieceCount(p)!= 0/*pieces.contains(p)*/){
					movimientoValido = rules.validMoves(board, pieces, p);
					if(movimientoValido.size() != - 1){
						int opcion = Utils.randomInt(movimientoValido.size());
						movimiento = movimientoValido.get(opcion);
					}
				}
			}catch(NumberFormatException e){
				throw new NumberFormatException("No hay fichas en el tablero");
			}
			
		}
		return movimiento;
	}
	
	/**
	 * Metodo que crea un nuevo movimiento al que se le pasa las filas, columnas y ficha
	 * @param row valor entero de la fila en la que se encuentra la ficha antes de moverse
	 * @param col valor entero de la columna en la que se encuentra la ficha antes de moverse
	 * @param filaDestino valor entero de la fila en la que se encuentra la ficha despues de ser movida
	 * @param columnaDestino valor entero de la columna en la que se encuentra la ficha despues de ser movida
	 * @param p ficha que se le pasa por parametros
	 * @return movimiento con los parametros de filas, columnas y ficha
	 */	 
	protected GameMove createMove(int row, int col, int filaDestino, int columnaDestino, Piece p) {
		return new AtaxxMove(row, col, filaDestino, columnaDestino, p);
	}

}
