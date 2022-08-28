import java.util.ArrayList;

public interface GamePiece {

  public int getPoints();
  public ArrayList<int[]> getMoves(Board gameBoard);
  public ArrayList<int[]> getAttacks(Board gameBoard);
  public String getColor();
  public void move(int[] pos);
}