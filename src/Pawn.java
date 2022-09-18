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
  private Vector2 currPos;

  /**
   * Creates a new Pawn of the specified ASCII color.
   */
  public Pawn(String pieceColor) {
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
    int colorMultiplier = pieceColor.equals(Chess.WHITE_PIECE_COLOR) ? 1 : -1;
    Vector2 currPos = gameBoard.getActivePos();

    // One square forward
    Vector2 tempMove = new Vector2(currPos.getX(), currPos.getY() + colorMultiplier);
    if (!Board.posInBounds(tempMove)) return moves;
    if (!gameBoard.posEmpty(tempMove)) return moves;
    moves.add(tempMove);

    // Two squares forward
    if (!hasMoved) {
      tempMove = new Vector2(currPos.getX(), currPos.getY() + (2 * colorMultiplier));
      if (!Board.posInBounds(tempMove)) return moves;
      if (!gameBoard.posEmpty(tempMove)) return moves;
      moves.add(tempMove);
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
    int colorMultiplier = pieceColor.equals(Chess.WHITE_PIECE_COLOR) ? 1 : -1;
    Vector2 currPos = gameBoard.getActivePos();

    // Left diagonal
    Vector2 tempAttack = new Vector2(currPos.getX() - 1, currPos.getY() + colorMultiplier);
    if (Board.posInBounds(tempAttack) // Within bounds
        && !gameBoard.posEmpty(tempAttack) // Piece exists
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) { // Color is opposite
      attacks.add(tempAttack);
    }

    // Right diagonal
    tempAttack = new Vector2(currPos.getX() + 1, currPos.getY() + colorMultiplier);
    if (Board.posInBounds(tempAttack) // Within bounds
        && !gameBoard.posEmpty(tempAttack) // Piece exists
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
    Pawn copy = new Pawn(this.pieceColor);
    copy.currPos = this.currPos.deepCopy();
    copy.hasMoved = this.hasMoved;
    return copy;
  }
}