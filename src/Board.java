import java.util.ArrayList;

/**
 * Contains the necessary methods to track and manipulate the state of the board in a game of Chess.
 *
 * @author Eligh Alvarez
 */
public class Board {

  private Square[][] squares;
  private Vector2 activePos;

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
    activePos = new Vector2(0, 0);
  }

  /**
   * Returns whether the provided position is within the bounds of this Board.
   * @param posToCheck The position to check
   * @return True if the position is in bounds, False otherwise
   */
  public static boolean posInBounds(Vector2 posToCheck) {
    return posToCheck.getX() >= 1 && posToCheck.getX() <= 8
        && posToCheck.getY() >= 1 && posToCheck.getY() <= 8;
  }

  /**
   * Returns whether the provided position is empty.
   * @param pos
   * @return
   */
  public boolean posEmpty(Vector2 pos) {
    return getSquare(pos) != null && getSquare(pos).getPiece() == null;
  }

  /**
   * Returns a String representation of this board, including Squares, Pieces, and coordinate
   * labels (columns a-h and rows 1-8)
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
  public void putPiece(GamePiece piece, Vector2 pos) {
    getSquare(pos).setPiece(piece);
  }

  /**
   * Returns a reference to the Square at the specified position
   * @param pos The position to pull from.
   * @return The Square at the specified position
   */
  public Square getSquare(Vector2 pos) {
    if (!posInBounds(pos)) return null;
    else return squares[8 - pos.getY()][pos.getX() - 1];
  }

  /**
   * Sets the Square at the specified position as the active square
   * @param pos The new active position
   */
  public void setActiveSquare(Vector2 pos) {
    activePos = pos.deepCopy();
    updateHighlights();
  }

  /**
   * Returns the active position.
   * @return the active position.
   */
  public Vector2 getActivePos() {
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

    ArrayList<Vector2> moves = activeSquare.getPiece().getMoves(this);
    for (Vector2 move : moves) {
      getSquare(move).setColor(Chess.MOVE_SPACE_COLOR);
    }

    ArrayList<Vector2> attacks = activeSquare.getPiece().getAttacks(this);
    for (Vector2 attack : attacks) {
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
   * Returns a list of pieces attacked by the specified color.
   * @param pieceColor The color with which to determine attacked spaces.
   * @return a list of attacked pieces
   */
  public ArrayList<Vector2> getAllAttacks(String pieceColor) {
    ArrayList<Vector2> allAttacks = new ArrayList<>();
    GamePiece tempPiece = null;
    for (Square[] row : squares) {
      for (Square square : row) {
        tempPiece = square.getPiece();
        if (tempPiece != null && tempPiece.getColor().equals(pieceColor)) {
          allAttacks.addAll(tempPiece.getAttacks(this));
        }
      }
    }
    return allAttacks;
  }

  /**
   * Generates and returns a deep copy of this Board.
   * @return a deep copy of this Board.
   */
  public Board copy() {
    Board copy = new Board();

    // Copy active position
    copy.activePos = this.activePos.deepCopy();

    // Copy pieces
    Vector2 runnerPos = null;
    GamePiece runnerPiece = null;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        runnerPos = new Vector2(i, j);
        runnerPiece = this.getSquare(runnerPos).getPiece();
        if (runnerPiece != null) {
          copy.getSquare(runnerPos).setPiece(runnerPiece.copy());
        }
      }
    }

    return copy;
  }
}