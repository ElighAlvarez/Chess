import java.util.ArrayList;

/**
 * A framework for GamePieces in the game of Chess.
 *
 * @author Eligh Alvarez
 */
abstract class GamePiece {

  abstract public int getPoints();
  abstract public ArrayList<Vector2> getMoves(Board gameBoard);
  abstract public ArrayList<Vector2> getAttacks(Board gameBoard);
  abstract public String getColor();
  abstract public void move(Vector2 pos);
}