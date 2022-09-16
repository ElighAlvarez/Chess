import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of Bishop Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class Bishop extends GamePiece {

  private static final int POINT_VALUE = 3;
  private String pieceColor;
  private Vector2 currPos;

  /**
   * Creates a new Bishop of the specified ASCII color.
   */
  public Bishop(String pieceColor) {
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

    // Left Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX() - i, currPos.getY() - i);
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX() + i, currPos.getY() - i);
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Left Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX() - i, currPos.getY() + i);
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new Vector2(currPos.getX() + i, currPos.getY() + i);
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
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
    int i;

    // Left Down
    i = 1;
    tempAttack = new Vector2(currPos.getX() - i, currPos.getY() - i);
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new Vector2(currPos.getX() - i, currPos.getY() - i);
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right Down
    i = 1;
    tempAttack = new Vector2(currPos.getX() + i, currPos.getY() - i);
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new Vector2(currPos.getX() + i, currPos.getY() - i);
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Left Up
    i = 1;
    tempAttack = new Vector2(currPos.getX() - i, currPos.getY() + i);
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new Vector2(currPos.getX() - i, currPos.getY() + i);
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right Up
    i = 1;
    tempAttack = new Vector2(currPos.getX() + i, currPos.getY() + i);
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new Vector2(currPos.getX() + i, currPos.getY() + i);
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
   * Returns a String representation "B" in the ASCII color of this piece.
   * @return a String representation of this piece.
   */
  public String toString() {
    return pieceColor + "B" + Chess.DEFAULT_COLOR;
  }

  /**
   * Updates internal values associated with movement of this piece.
   * @param pos the position of this piece after it is moved in [x, y] format
   */
  public void move(Vector2 pos) {
    currPos = pos;
  }
}