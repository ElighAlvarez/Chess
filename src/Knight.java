import java.util.ArrayList;

public class Knight extends GamePiece {

  private static final int POINT_VALUE = 3;
  private String pieceColor;
  private static final int[][] VALID_MOVES = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                                              {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

  public Knight(String pieceColor) {
    this.pieceColor = pieceColor;
  }

  public int getPoints() {
    return POINT_VALUE;
  }

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

  public String getColor() {
    return pieceColor;
  }

  public String toString() {
    return pieceColor + "N" + Chess.DEFAULT_COLOR;
  }

  public void move(int[] notUsed) {}
}