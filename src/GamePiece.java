import java.util.ArrayList;

abstract class GamePiece {

  abstract public int getPoints();
  abstract public ArrayList<int[]> getMoves(Board gameBoard);
  abstract public ArrayList<int[]> getAttacks(Board gameBoard);
  abstract public String getColor();
  abstract public void move(int[] pos);
}