import java.util.ArrayList;

public class Pawn extends GamePiece {

  private static final int POINT_VALUE = 1;

  private String pieceColor;
  private boolean hasMoved;
  private int[] currPos;

  public Pawn(String pieceColor) {
    this.hasMoved = false;
    this.pieceColor = pieceColor;
  }

  public int getPoints() {
    return POINT_VALUE;
  }

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

  public String getColor() {
    return pieceColor;
  }

  public String toString() {
    return pieceColor + "P" + Chess.DEFAULT_COLOR;
  }

  public void move(int[] pos) {
    hasMoved = true;
    currPos = pos;
  }

  public boolean hasMoved() {
    return hasMoved;
  }
}