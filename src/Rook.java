import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of Rook Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class Rook extends GamePiece {

  private static final int POINT_VALUE = 5;
  private String pieceColor;
  private boolean hasMoved;
  private Vector2 currPos;

  /**
   * Creates a new Rook of the specified ASCII color.
   */
  public Rook(String pieceColor) {
    this.hasMoved = false;
    this.pieceColor = pieceColor;
    this.currPos = new Vector2(-1, -1);
  }

  /**
   * Returns the point value associated with this piece.
   * @return the point value associated with this piece.
   */
  public int getPoints() {
    return POINT_VALUE;
  }

  /**
   * Returns a list of valid moves for this piece.
   * @param gameBoard The Board containing this piece.
   * @return A valid list of moves for this piece.
   */
  public ArrayList<Vector2> getMoves(Board gameBoard) {
    ArrayList<Vector2> moves = new ArrayList<>();
    Vector2 currPos = gameBoard.getActivePos();
    Vector2 tempMove;

    // Left
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX() - i, currPos.getY());
      if (Board.posInBounds(tempMove) && gameBoard.posEmpty(tempMove)) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX() + i, currPos.getY());
      if (Board.posInBounds(tempMove) && gameBoard.posEmpty(tempMove)) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX(), currPos.getY() + i);
      if (Board.posInBounds(tempMove) && gameBoard.posEmpty(tempMove)) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX(), currPos.getY() - i);
      if (Board.posInBounds(tempMove) && gameBoard.posEmpty(tempMove)) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    return moves;
  }

  /**
   * Returns a list of valid attacks for this piece.
   * @param gameBoard The Board containing this piece.
   * @return A valid list of attacks for this piece.
   */
  public ArrayList<Vector2> getAttacks(Board gameBoard) {
    ArrayList<Vector2> attacks = new ArrayList<>();
    Vector2 currPos = gameBoard.getActivePos();
    Vector2 tempAttack;
    int i;

    // Left
    i = 1;
    tempAttack = new Vector2(currPos.getX() - i, currPos.getY());
    while (Board.posInBounds(tempAttack) && gameBoard.posEmpty(tempAttack)) {
      i++;
      tempAttack = new Vector2(currPos.getX() - i, currPos.getY());
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right
    i = 1;
    tempAttack = new Vector2(currPos.getX() + i, currPos.getY());
    while (Board.posInBounds(tempAttack) && gameBoard.posEmpty(tempAttack)) {
      i++;
      tempAttack = new Vector2(currPos.getX() + i, currPos.getY());
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Up
    i = 1;
    tempAttack = new Vector2(currPos.getX(), currPos.getY() + i);
    while (Board.posInBounds(tempAttack) && gameBoard.posEmpty(tempAttack)) {
      i++;
      tempAttack = new Vector2(currPos.getX(), currPos.getY() + i);
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Down
    i = 1;
    tempAttack = new Vector2(currPos.getX(), currPos.getY() - i);
    while (Board.posInBounds(tempAttack) && gameBoard.posEmpty(tempAttack)) {
      i++;
      tempAttack = new Vector2(currPos.getX(), currPos.getY() - i);
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
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
   * Returns a String representation "R" in the ASCII color of this piece.
   * @return a String representation of this piece.
   */
  public String toString() {
    return pieceColor + "R" + Chess.DEFAULT_COLOR;
  }

  /**
   * Updates internal values associated with movement of this piece.
   * @param pos the position of this piece after it is moved
   */
  public void move(Vector2 pos) {
    hasMoved = true;
    currPos = pos;
  }

  public boolean hasMoved() {
    return hasMoved;
  }

  /**
   * Returns a duplicate copy of this piece.
   * @return a duplicate copy of this piece.
   */
  public GamePiece copy() {
    Rook copy = new Rook(this.pieceColor);
    copy.currPos = this.currPos.deepCopy();
    copy.hasMoved = this.hasMoved;
    return copy;
  }
}