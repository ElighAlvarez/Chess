import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of Knight Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class Knight extends GamePiece {

  private static final int POINT_VALUE = 3;
  private String pieceColor;
  private static final int[][] VALID_MOVES = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                                              {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

  /**
   * Creates a new Knight of the specified ASCII color.
   */
  public Knight(String pieceColor) {
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

    for (int i = 0; i < 8; i++) {
      tempMove = new int[] {currPos[0] + VALID_MOVES[i][0], currPos[1] + VALID_MOVES[i][1]};
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
  public ArrayList<int[]> getAttacks(Board gameBoard) {
    ArrayList<int[]> attacks = new ArrayList<>();
    int[] currPos = gameBoard.getActivePos();
    int[] tempAttack;

    for (int i = 0; i < 8; i++) {
      tempAttack = new int[] {currPos[0] + VALID_MOVES[i][0], currPos[1] + VALID_MOVES[i][1]};
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
   * Returns a String representation "N" in the ASCII color of this piece.
   * @return a String representation of this piece.
   */
  public String toString() {
    return pieceColor + "N" + Chess.DEFAULT_COLOR;
  }

  /**
   * Updates internal values associated with movement of this piece.
   * @param pos the position of this piece after it is moved in [x, y] format
   */
  public void move(int[] notUsed) {}
}