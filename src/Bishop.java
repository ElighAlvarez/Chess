import java.util.ArrayList;

public class Bishop implements GamePiece {

  private static final int POINT_VALUE = 3;
  private String pieceColor;
  private int[] currPos;

  public Bishop(String pieceColor) {
    this.pieceColor = pieceColor;
  }

  public int getPoints() {
    return POINT_VALUE;
  }

  public ArrayList<int[]> getMoves(Board gameBoard) {
    ArrayList<int[]> moves = new ArrayList<>();
    int[] currPos = gameBoard.getActivePos();
    int[] tempMove;

    // Left Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] - i, currPos[1] - i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] + i, currPos[1] - i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Left Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] - i, currPos[1] + i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] + i, currPos[1] + i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    return moves;
  }

  public ArrayList<int[]> getAttacks(Board gameBoard) {
    ArrayList<int[]> attacks = new ArrayList<>();
    int[] currPos = gameBoard.getActivePos();
    int[] tempAttack;
    int i;

    // Left Down
    i = 1;
    tempAttack = new int[] {currPos[0] - i, currPos[1] - i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] - i, currPos[1] - i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right Down
    i = 1;
    tempAttack = new int[] {currPos[0] + i, currPos[1] - i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] + i, currPos[1] - i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Left Up
    i = 1;
    tempAttack = new int[] {currPos[0] - i, currPos[1] + i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] - i, currPos[1] + i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right Up
    i = 1;
    tempAttack = new int[] {currPos[0] + i, currPos[1] + i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] + i, currPos[1] + i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    return attacks;
  }

  public String getColor() {
    return pieceColor;
  }

  public String toString() {
    return pieceColor + "B" + Chess.DEFAULT_COLOR;
  }

  public void move(int[] pos) {
    currPos = pos;
  }
}