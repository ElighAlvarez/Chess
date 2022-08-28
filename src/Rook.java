import java.util.ArrayList;

public class Rook implements GamePiece {

  private static final int POINT_VALUE = 5;
  private String pieceColor;
  private boolean hasMoved;
  private int[] currPos;

  public Rook(String pieceColor) {
    this.hasMoved = false;
    this.pieceColor = pieceColor;
  }

  public int getPoints() {
    return POINT_VALUE;
  }

  public ArrayList<int[]> getMoves(Board gameBoard) {
    ArrayList<int[]> moves = new ArrayList<>();
    int[] currPos = gameBoard.getActivePos();
    int[] tempMove;

    // Left
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] - i, currPos[1]};
      // TODO: make an isEmpty() method instead of gameBoard.getSquare(tem........
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Right
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0] + i, currPos[1]};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Up
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0], currPos[1] + i};
      if (Board.posInBounds(tempMove) && gameBoard.getSquare(tempMove).getPiece() == null) {
        moves.add(tempMove);
      } else {
        break;
      }
    }

    // Down
    for (int i = 1; i <= 7; i++) {
      tempMove = new int[] {currPos[0], currPos[1] - i};
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

    // Left
    i = 1;
    tempAttack = new int[] {currPos[0] - i, currPos[1]};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] - i, currPos[1]};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Right
    i = 1;
    tempAttack = new int[] {currPos[0] + i, currPos[1]};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0] + i, currPos[1]};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Up
    i = 1;
    tempAttack = new int[] {currPos[0], currPos[1] + i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0], currPos[1] + i};
    }
    if (Board.posInBounds(tempAttack)
        && !getColor().equals(gameBoard.getSquare(tempAttack).getPiece().getColor())) {
      attacks.add(tempAttack);
    }

    // Down
    i = 1;
    tempAttack = new int[] {currPos[0], currPos[1] - i};
    while (Board.posInBounds(tempAttack)
        && gameBoard.getSquare(tempAttack).getPiece() == null) {
      i++;
      tempAttack = new int[] {currPos[0], currPos[1] - i};
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
    return pieceColor + "R" + Chess.DEFAULT_COLOR;
  }

  public void move(int[] pos) {
    hasMoved = true;
    currPos = pos;
  }

  public boolean hasMoved() {
    return hasMoved;
  }
}