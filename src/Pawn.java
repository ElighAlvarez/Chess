import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of Pawn Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class Pawn extends GamePiece {

  private static final int POINT_VALUE = 1;

  private String pieceColor;
  private boolean hasMoved;
  private int[] currPos;

  /**
   * Creates a new Pawn of the specified ASCII color.
   */
  public Pawn(String pieceColor) {
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
    int colorMultiplier = pieceColor.equals(Chess.WHITE_PIECE_COLOR) ? 1 : -1;
    int[] currPos = gameBoard.getActivePos();

    // One square forward
    int[] tempMove = new int[] {currPos[0], currPos[1] + colorMultiplier};
    if (!Board.posInBounds(tempMove)) return moves;
    if (gameBoard.getSquare(tempMove).getPiece() != null) return moves;
    moves.add(tempMove);

    // Two squares forward
    if (!hasMoved) {
      tempMove = new int[]{currPos[0], currPos[1] + (2 * colorMultiplier)};
      if (!Board.posInBounds(tempMove)) return moves;
      if (gameBoard.getSquare(tempMove).getPiece() != null) return moves;
      moves.add(tempMove);
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
    int colorMultiplier = pieceColor.equals(Chess.WHITE_PIECE_COLOR) ? 1 : -1;
    int[] currPos = gameBoard.getActivePos();

    // Left diagonal
    int[] tempAttack = new int[] {currPos[0] - 1, currPos[1] + colorMultiplier};
    if (Board.posInBounds(tempAttack) // Within bounds
        && gameBoard.getSquare(tempAttack).getPiece() != null // Piece exists
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) { // Color is opposite
      attacks.add(tempAttack);
    }

    // Right diagonal
    tempAttack = new int[] {currPos[0] + 1, currPos[1] + colorMultiplier};
    if (Board.posInBounds(tempAttack) // Within bounds
        && gameBoard.getSquare(tempAttack).getPiece() != null // Piece exists
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) { // Color is opposite
      attacks.add(tempAttack);
    }

    // TODO: Add En Passant

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
   * Returns a String representation "P" in the ASCII color of this piece.
   * @return a String representation of this piece.
   */
  public String toString() {
    return pieceColor + "P" + Chess.DEFAULT_COLOR;
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