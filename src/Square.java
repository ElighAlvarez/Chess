/**
 * Contains all necessary methods for the maintenance of an individual Square on a Board.
 *
 * @author Eligh Alvarez
 */
public class Square {
  private String color;
  private GamePiece piece;

  /**
   * Creates a new Square of the provided ASCII color and containing the provided piece
   * @param piece the piece to place on this Square
   * @param color the color of this Square
   */
  public Square(GamePiece piece, String color) {
    this.piece = piece;
    this.color = color;
  }

  /**
   * Returns a String representation of this Square as "[X]", where X is the piece
   */
  public String toString() {
    if (piece == null) return color + "[ ]" + Chess.DEFAULT_COLOR;
    else return color + "[" + Chess.DEFAULT_COLOR + piece + color + "]" + Chess.DEFAULT_COLOR;
  }

  /**
   * Sets the piece contained in this Square
   * @param piece
   */
  public void setPiece(GamePiece piece) {
    this.piece = piece;
  }

  /**
   * Returns the piece contained in this Square
   * @return the piece contained in this Square
   */
  public GamePiece getPiece() {
    return piece;
  }

  /**
   * Sets the color of this Square
   * @param color the new color of this Square
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Returns the ASCII color of this Square
   * @return the color of this Square
   */
  public String getColor() {
    return color;
  }
}
