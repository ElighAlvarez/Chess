import java.util.ArrayList;

/**
 * Contains the necessary methods to track and manipulate the state of the board in a game of Chess.
 *
 * @author Eligh Alvarez
 */
public class Board {

  private Square[][] squares;
  private int[] activePos;

  /**
   * Creates a new empty Chess board
   */
  public Board() {
    squares = new Square[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) squares[i][j] = new Square(null, Chess.LIGHT_SPACE_COLOR);
        else squares[i][j] = new Square(null, Chess.DARK_SPACE_COLOR);
      }
    }
    activePos = new int[] {0, 0};
  }

  /**
   * Returns whether the provided position is within the bounds of this Board.
   * @param posToCheck The position to check in [x, y] format
   * @return True if the position is in bounds, False otherwise
   */
  public static boolean posInBounds(int[] posToCheck) {
    return posToCheck[0] >= 1 && posToCheck[0] <= 8 && posToCheck[1] >= 1 && posToCheck[1] <= 8;
  }

  /**
   * Returns a String representation of this board, including Squares, Pieces, and coordinate
   * labels (a-h and 1-8)
   * @return a String representation of this board
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      str.append((8 - i) + " ");
      for (Square square : squares[i]) {
        str.append(square.toString());
      }
      str.append("\n");
    }
    str.append("   a  b  c  d  e  f  g  h\n");
    return str.toString();
  }

  /**
   * Puts a specified piece at the specified position on this Board.
   * @param piece The piece to be placed
   * @param pos The position at which to put the specified piece.
   */
  public void putPiece(GamePiece piece, int[] pos) {
    getSquare(pos).setPiece(piece);
  }

  /**
   * Returns a reference to the Square at the specified position
   * @param pos The position to pull from.
   * @return The Square at the specified position
   */
  public Square getSquare(int[] pos) {
    if (!posInBounds(pos)) return null;
    else return squares[8 - pos[1]][pos[0] - 1];
  }

  /**
   * Sets the Square at the specified position as the active square
   * @param pos The new active position in [x, y] format
   */
  public void setActiveSquare(int[] pos) {
    activePos = new int[] {pos[0], pos[1]};
    updateHighlights();
  }

  /**
   * Returns the active position in [x, y] format.
   * @return the active position in [x, y] format.
   */
  public int[] getActivePos() {
    return activePos;
  }

  /**
   * Returns the Square at the current active position
   * @return the Square at the current active position
   */
  public Square getActiveSquare() {
    return getSquare(activePos);
  }

  /**
   * Updates the contextual colors for moves and attacks based on the active position and the
   * piece at that position.
   */
  private void updateHighlights() {
    setBaseColors();
    if (!posInBounds(activePos)) return;

    Square activeSquare = getActiveSquare();
    activeSquare.setColor(Chess.ACTIVE_SPACE_COLOR);

    ArrayList<int[]> moves = activeSquare.getPiece().getMoves(this);
    for (int[] move : moves) {
      getSquare(move).setColor(Chess.MOVE_SPACE_COLOR);
    }

    ArrayList<int[]> attacks = activeSquare.getPiece().getAttacks(this);
    for (int[] attack : attacks) {
      getSquare(attack).setColor(Chess.ATTACK_SPACE_COLOR);
    }
  }

  /**
   * Sets all Square colors to their base colors (removes contextual colors)
   */
  private void setBaseColors() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) squares[i][j].setColor(Chess.LIGHT_SPACE_COLOR);
        else squares[i][j].setColor(Chess.DARK_SPACE_COLOR);
      }
    }
  }

  /**
   * Returns a list of positions attacked by the specified color in [x, y] format.
   * @param pieceColor The color with which to determine attacked spaces.
   * @return a list of attacked positions
   */
  // TODO: Implement this for determining checks for kings
  public ArrayList<int[]> getAttackedSquares(String pieceColor) {
    return null;
  }
}