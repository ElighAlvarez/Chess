public class TestChess {
  public static void main(String[] args) {
    Board gameBoard = new Board();
    Chess game = new Chess(gameBoard);
    game.selectPiece(new Vector2(7, 1));
    game.moveCurrentPiece(new Vector2(6, 3));
  }
}