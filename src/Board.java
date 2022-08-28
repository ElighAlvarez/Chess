import java.util.ArrayList;

public class Board {

  private Square[][] squares;
  private int[] activePos;

  public Board() {
    squares = new Square[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) squares[i][j] = new Square(null, Chess.LIGHT_SPACE_COLOR);
        else squares[i][j] = new Square(null, Chess.DARK_SPACE_COLOR);
      }
    }
    activePos = new int[] {0, 0};
  }

  public static boolean posInBounds(int[] posToCheck) {
    return posToCheck[0] >= 1 && posToCheck[0] <= 8 && posToCheck[1] >= 1 && posToCheck[1] <= 8;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      str.append((8 - i) + " ");
      for (Square square : squares[i]) {
        str.append(square.toString());
      }
      str.append("\n");
    }
    str.append("   a  b  c  d  e  f  g  h\n");
    return str.toString();
  }

  public void putPiece(GamePiece piece, int[] pos) {
    getSquare(pos).setPiece(piece);
  }

  public Square getSquare(int[] pos) {
    if (!posInBounds(pos)) return null;
    else return squares[8 - pos[1]][pos[0] - 1];
  }

  public void setActiveSquare(int[] pos) {
    activePos = new int[] {pos[0], pos[1]};
    updateHighlights();
  }

  public int[] getActivePos() {
    return activePos;
  }

  public Square getActiveSquare() {
    return getSquare(activePos);
  }

  private void updateHighlights() {
    setBaseColors();
    if (!posInBounds(activePos)) return;

    Square activeSquare = getActiveSquare();
    activeSquare.setColor(Chess.ACTIVE_SPACE_COLOR);

    ArrayList<int[]> moves = activeSquare.getPiece().getMoves(this);
    for (int[] move : moves) {
      getSquare(move).setColor(Chess.MOVE_SPACE_COLOR);
    }

    ArrayList<int[]> attacks = activeSquare.getPiece().getAttacks(this);
    for (int[] attack : attacks) {
      getSquare(attack).setColor(Chess.ATTACK_SPACE_COLOR);
    }
  }

  private void setBaseColors() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) squares[i][j].setColor(Chess.LIGHT_SPACE_COLOR);
        else squares[i][j].setColor(Chess.DARK_SPACE_COLOR);
      }
    }
  }

  // TODO: Implement this for determining checks for kings
  public ArrayList<int[]> getAttackedSquares(String pieceColor) {
    return null;
  }
}