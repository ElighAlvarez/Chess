import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of King Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class King extends GamePiece {

  private static final int POINT_VALUE = 999;
  private static final int[][] VALID_MOVES = {{-1, 1}, {0, 1}, {1, 1},
                                              {-1, 0},  /*K*/  {1, 0},
                                              {-1, -1}, {0, -1}, {1, -1}};
  private String pieceColor;
  private boolean hasMoved;
  private int[] currPos;

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
  public void move(int[] pos) {
    hasMoved = true;
    currPos = pos;
  }

  public boolean hasMoved() {
    return hasMoved;
  }
}