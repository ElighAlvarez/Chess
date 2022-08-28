public class TestChess {
  public static void main(String[] args) {
    Board gameBoard = new Board();
    Chess game = new Chess(gameBoard);
    game.selectPiece(new int[] {7, 1});
    game.moveCurrentPiece(new int[] {6, 3});
  }
}