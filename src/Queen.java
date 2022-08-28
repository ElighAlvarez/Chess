import java.util.ArrayList;

public class Queen extends GamePiece {

  private static final int POINT_VALUE = 9;
  private String pieceColor;
  private int[] currPos;

  public Queen(String pieceColor) {
    this.pieceColor = pieceColor;
  }

  public int getPoints() {
    return POINT_VALUE;
  }

  public ArrayList<int[]> getMoves(Board gameBoard) {
    ArrayList<int[]> moves = new ArrayList<>();
    Rook tempRook = new Rook(this.getColor());
    Bishop tempBishop = new Bishop(this.getColor());
    moves.addAll(tempRook.getMoves(gameBoard));
    moves.addAll(tempBishop.getMoves(gameBoard));
    return moves;
  }

  public ArrayList<int[]> getAttacks(Board gameBoard) {
    ArrayList<int[]> attacks = new ArrayList<>();
    Rook tempRook = new Rook(this.getColor());
    Bishop tempBishop = new Bishop(this.getColor());
    attacks.addAll(tempRook.getAttacks(gameBoard));
    attacks.addAll(tempBishop.getAttacks(gameBoard));
    return attacks;
  }

  public String getColor() {
    return pieceColor;
  }

  public String toString() {
    return pieceColor + "Q" + Chess.DEFAULT_COLOR;
  }

  public void move(int[] pos) {
    currPos = pos;
  }
}