/**
 * A 2-dimensional vector for positional and movement information.
 *
 * @author Eligh Alvarez
 */
public class Vector2 {
  int x;
  int y;

  /**
   * Creates a 2-dimensional vector with the provided values
   * @param x the scalar of the first dimension
   * @param y the scalar of the second dimension
   */
  public Vector2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the scalar of the first dimension in this Vector2
   * @return the scalar of the first dimension in this Vector2
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the scalar of the second dimension in this Vector2
   * @return the scalar of the second dimension in this Vector2
   */
  public int getY() {
    return y;
  }

  public Vector2 deepCopy() {
    return new Vector2(x, y);
  }
}
