import java.util.ArrayList;

/**
 * Contains the necessary methods for the operation of Queen Chess pieces.
 *
 * @author Eligh Alvarez
 */
public class Queen extends GamePiece {

  private static final int POINT_VALUE = 9;
  private String pieceColor;
  private Vector2 currPos;

  /**
   * Creates a new Queen of the specified ASCII color.
   */
  public Queen(String pieceColor) {
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
    Rook tempRook = new Rook(this.getColor());
    Bishop tempBishop = new Bishop(this.getColor());
    moves.addAll(tempRook.getMoves(gameBoard));
    moves.addAll(tempBishop.getMoves(gameBoard));
    return moves;
  }

  /**
   * Returns a list of valid attacks for this piece in [x, y] format.
   * @param gameBoard The Board containing this piece.
   * @return A valid list of attacks for this piece.
   */
  public ArrayList<Vector2> getAttacks(Board gameBoard) {
    ArrayList<Vector2> attacks = new ArrayList<>();
    Rook tempRook = new Rook(this.getColor());
    Bishop tempBishop = new Bishop(this.getColor());
    attacks.addAll(tempRook.getAttacks(gameBoard));
    attacks.addAll(tempBishop.getAttacks(gameBoard));
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
   * Returns a String representation "Q" in the ASCII color of this piece.
   * @return a String representation of this piece.
   */
  public String toString() {
    return pieceColor + "Q" + Chess.DEFAULT_COLOR;
  }

  /**
   * Updates internal values associated with movement of this piece.
   * @param pos the position of this piece after it is moved in [x, y] format
   */
  public void move(Vector2 pos) {
    currPos = pos;
  }
}