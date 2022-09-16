import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of King Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class King extends GamePiece {

  private static final int POINT_VALUE = 999;
  private static final Vector2[] VALID_MOVES = {
      new Vector2(-1, 1),
      new Vector2(0, 1),
      new Vector2(1, 1),
      new Vector2(-1, 0),
      new Vector2(1, 0),
      new Vector2(-1, -1),
      new Vector2(0, -1),
      new Vector2(1, -1)
  };

  private String pieceColor;
  private boolean hasMoved;
  private Vector2 currPos;

  /**
   * Creates a new King of the specified ASCII color.
   */
  public King(String pieceColor) {
    this.hasMoved = false;
    this.pieceColor = pieceColor;
  }

  /**
   * Returns the point value associated with this piece.
   * @return the point value associated with this piece.
   */
  public int getPoints() {
    return POINT_VALUE;
  }

  /**
   * Returns a list of valid moves for this piece in [x, y] format.
   * @param gameBoard The Board containing this piece.
   * @return A valid list of moves for this piece.
   */
  public ArrayList<Vector2> getMoves(Board gameBoard) {
    ArrayList<Vector2> moves = new ArrayList<>();
    Vector2 currPos = gameBoard.getActivePos();
    Vector2 tempMove;

    for (int i = 0; i < 8; i++) {
      tempMove = new Vector2(currPos.getX() + VALID_MOVES[i].getX(),
                             currPos.getY() + VALID_MOVES[i].getY());
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      }
    }

    return moves;
  }

  /**
   * Returns a list of valid attacks for this piece in [x, y] format.
   * @param gameBoard The Board containing this piece.
   * @return A valid list of attacks for this piece.
   */
  public ArrayList<Vector2> getAttacks(Board gameBoard) {
    ArrayList<Vector2> attacks = new ArrayList<>();
    Vector2 currPos = gameBoard.getActivePos();
    Vector2 tempAttack;

    for (int i = 0; i < 8; i++) {
      tempAttack = new Vector2(currPos.getX() + VALID_MOVES[i].getX(),
          currPos.getY() + VALID_MOVES[i].getY());
      if (Board.posInBounds(tempAttack)
          && gameBoard.getSquare(tempAttack).getPiece() != null
          && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
        attacks.add(tempAttack);
      }
    }

    return attacks;
  }

  /**
   * Returns the ASCII color of this piece.
   * @return the ASCII color of this piece.
   */
  public String getColor() {
    return pieceColor;
  }

  /**
   * Returns a String representation "K" in the ASCII color of this piece.
   * @return a String representation of this piece.
   */
  public String toString() {
    return pieceColor + "K" + Chess.DEFAULT_COLOR;
  }

  /**
   * Updates internal values associated with movement of this piece.
   * @param pos the position of this piece after it is moved in [x, y] format
   */
  public void move(Vector2 pos) {
    hasMoved = true;
    currPos = pos;
  }

  public boolean hasMoved() {
    return hasMoved;
  }
}