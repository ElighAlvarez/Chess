import java.util.Scanner;

/**
 * Contains the necessary methods to play a game of Chess.
 *
 * @author Eligh alvarez
 */
public class Chess {

  public static final String DEFAULT_COLOR = "\033[0m";
  public static final String BLACK_PIECE_COLOR = "\033[34m";
  public static final String WHITE_PIECE_COLOR = "\033[36m";
  public static final String ATTACK_SPACE_COLOR = "\033[31m";
  public static final String MOVE_SPACE_COLOR = "\033[32m";
  public static final String DARK_SPACE_COLOR = "\033[37m";
  public static final String LIGHT_SPACE_COLOR = "\033[0m";
  public static final String ACTIVE_SPACE_COLOR = "\033[33m";

  private Board gameBoard;
  private int whitePoints;
  private int blackPoints;
  private int[] pos;

  /**
   * Allows players to play a game of Chess with the standard game setup.
   * @param args (unused)
   */
  public static void main(String[] args) {
    printWelcome();
    Scanner userInput = new Scanner(System.in);
    Chess game = new Chess();
    game.pos = new int[2];
    boolean exit = false;

    while(!exit && game.whitePoints < 900 && game.blackPoints < 900) {
      switch (userInput.next()) {
        case "select":
          game.executeSelect(userInput);
          break;
        case "move":
          game.executeMove(userInput);
          break;
        case "adjust":
          game.enterAdjustMode(userInput);
          break;
        case "exit":
          exit = true;
          break;
        default:
          System.out.println("Command not recognized");
          userInput.nextLine();
      }
    }
    if (game.whitePoints > game.blackPoints) System.out.println("White wins!");
    else if (game.blackPoints > game.whitePoints) System.out.println("Black wins!");
    else System.out.println("Exiting...");
  }


  // Use this for testing and debugging
  // TODO: Comment out when not in use
  public Chess(Board gameBoard) {
    this.gameBoard = gameBoard;
    setup();
  }

  /**
   * Creates a new standard Chess game.
   */
  public Chess() {
    gameBoard = new Board();
    setup();
    display();
  }

  /**
   * Prints the welcome message to the console.
   */
  public static void printWelcome() {
    System.out.println("\nWelcome to Chess!\n");
    System.out.println("To select a piece, using the x and y positions of the desired piece,"
        + " type: \"select x y\"");
    System.out.println("To move the currently selected piece, using the target x and y positions,"
        + " type: \"move x y\"");
    System.out.println("To enter the adjustment mode, type: \"adjust\"");
    System.out.println("To exit the game, type: \"exit\"   WARNING: GAME DOES NOT SAVE\n");
  }

  /**
   * Selects a position and makes it active.
   * @param userInput The user input Scanner
   */
  public void executeSelect(Scanner userInput) {
    int userInt = letterToNumber(userInput);
    if (userInt == 0) {
      System.out.println("Selection command improperly formatted. Try again.");
      return;
    } else pos[0] = userInt;
    if (userInput.hasNextInt()) pos[1] = userInput.nextInt();
    else {
      System.out.println("Selection command improperly formatted. Try again.");
      userInput.nextLine();
      return;
    }
    selectPiece(pos);
    userInput.nextLine();
  }

  // TODO: Continue JavaDocs here!
  public void executeMove(Scanner userInput) {
    int userInt = letterToNumber(userInput);
    if (userInt == 0) {
      System.out.println("Movement command improperly formatted. Try again.");
      return;
    } else pos[0] = userInt;
    if (userInput.hasNextInt()) pos[1] = userInput.nextInt();
    else {
      System.out.println("Movement command improperly formatted. Try again.");
      userInput.nextLine();
      return;
    }
    moveCurrentPiece(pos);
    userInput.nextLine();
  }

  public void enterAdjustMode(Scanner userInput) {
    //System.out.println("\nYou have entered the adjustment mode. You may now add and remove "
    //    + "pieces "
    //    + "freely and adjust each color's accumulated points.");
    //System.out.println("");

    System.out.println("adjust functionality will be implemented later");
    userInput.nextLine();
  }

  private void setup() {
    String white = WHITE_PIECE_COLOR;
    String black = BLACK_PIECE_COLOR;
    // White Rooks
    gameBoard.putPiece(new Rook(white), new int[] {1, 1});
    gameBoard.putPiece(new Rook(white), new int[] {8, 1});
    // White Knights
    gameBoard.putPiece(new Knight(white), new int[] {2, 1});
    gameBoard.putPiece(new Knight(white), new int[] {7, 1});
    // White Bishops
    gameBoard.putPiece(new Bishop(white), new int[] {3, 1});
    gameBoard.putPiece(new Bishop(white), new int[] {6, 1});
    // White King
    gameBoard.putPiece(new King(white), new int[] {5, 1});
    // White Queen
    gameBoard.putPiece(new Queen(white), new int[] {4, 1});
    // White Pawns
    for (int i = 1; i <= 8; i++) {
      gameBoard.putPiece(new Pawn(white), new int[] {i, 2});
    }

    // Black Rooks
    gameBoard.putPiece(new Rook(black), new int[] {1, 8});
    gameBoard.putPiece(new Rook(black), new int[] {8, 8});
    // Black Knights
    gameBoard.putPiece(new Knight(black), new int[]{2, 8});
    gameBoard.putPiece(new Knight(black), new int[]{7, 8});
    // Black Bishops
    gameBoard.putPiece(new Bishop(black), new int[]{3, 8});
    gameBoard.putPiece(new Bishop(black), new int[]{6, 8});
    // Black King
    gameBoard.putPiece(new King(black), new int[]{5, 8});
    // Black Queen
    gameBoard.putPiece(new Queen(black), new int[]{4, 8});
    // Black Pawns
    for (int i = 1; i <= 8; i++) {
      gameBoard.putPiece(new Pawn(black), new int[]{i, 7});
    }
  }

  public void display() {
    System.out.println(gameBoard);
  }

  public void selectPiece(int[] pos) {
    if (gameBoard.getSquare(pos) == null || gameBoard.getSquare(pos).getPiece() == null) {
      System.out.println("There is no piece at that position.");
      gameBoard.setActiveSquare(new int[] {0, 0});
    } else {
      gameBoard.setActiveSquare(pos);
    }
    display();
  }

  public void moveCurrentPiece(int[] pos) {
    Square target = gameBoard.getSquare(pos);
    Square current = gameBoard.getActiveSquare();
    if (current == null) {
      System.out.println("There is no currently selected piece.");
      return;
    }

    if (target == null) {
      System.out.println("This is not a valid square.");
      return;
    }

    if (target.getColor().equals(ATTACK_SPACE_COLOR)) {
      if (target.getPiece().getColor().equals(BLACK_PIECE_COLOR))
        whitePoints += target.getPiece().getPoints();
      else blackPoints += target.getPiece().getPoints();

      current.getPiece().move(new int[] {pos[0], pos[1]});
      target.setPiece(current.getPiece());
      current.setPiece(null);
      gameBoard.setActiveSquare(new int[] {0, 0});
    } else if (target.getColor().equals(MOVE_SPACE_COLOR)) {
      current.getPiece().move(new int[] {pos[0], pos[1]});
      target.setPiece(current.getPiece());
      current.setPiece(null);
      gameBoard.setActiveSquare(new int[] {0, 0});
    } else {
      System.out.println("You cannot move the specified piece here.");
    }

    display();
  }

  public static int letterToNumber(Scanner userInput) {
    if (!userInput.hasNext()) {
      userInput.nextLine();
      return 0;
    }
    switch (userInput.next().charAt(0)) {
      case 'a':
        return 1;
      case 'b':
        return 2;
      case 'c':
        return 3;
      case 'd':
        return 4;
      case 'e':
        return 5;
      case 'f':
        return 6;
      case 'g':
        return 7;
      case 'h':
        return 8;
      default:
        return 0;
    }
  }
}