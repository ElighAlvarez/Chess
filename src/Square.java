public class Square {
  private String color;
  private GamePiece piece;

  public Square(GamePiece piece, String color) {
    this.piece = piece;
    this.color = color;
  }

  public String toString() {
    if (piece == null) return color + "[ ]" + Chess.DEFAULT_COLOR;
    else return color + "[" + Chess.DEFAULT_COLOR + piece + color + "]" + Chess.DEFAULT_COLOR;
  }

  public void setPiece(GamePiece piece) {
    this.piece = piece;
  }

  public GamePiece getPiece() {
    return piece;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }
}
