import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of Bishop Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class Bishop extends GamePiece {

  private static final int POINT_VALUE = 3;
  private String pieceColor;
  private int[] currPos;

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
  public ArrayList<int[]> getMoves(Board gameBoard) {
    ArrayList<int[]> moves = new ArrayList<>();
    int[] currPos = gameBoard.getActivePos();
    int[] tempMove;

    // Left Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] - i, currPos[1] - i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] + i, currPos[1] - i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Left Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] - i, currPos[1] + i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] + i, currPos[1] + i};
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
  public ArrayList<int[]> getAttacks(Board gameBoard) {
    ArrayList<int[]> attacks = new ArrayList<>();
    int[] currPos = gameBoard.getActivePos();
    int[] tempAttack;
    int i;

    // Left Down
    i = 1;
    tempAttack = new int[] {currPos[0] - i, currPos[1] - i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] - i, currPos[1] - i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right Down
    i = 1;
    tempAttack = new int[] {currPos[0] + i, currPos[1] - i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] + i, currPos[1] - i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Left Up
    i = 1;
    tempAttack = new int[] {currPos[0] - i, currPos[1] + i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] - i, currPos[1] + i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right Up
    i = 1;
    tempAttack = new int[] {currPos[0] + i, currPos[1] + i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] + i, currPos[1] + i};
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
  public void move(int[] pos) {
    currPos = pos;
  }
}