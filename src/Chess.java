import java.util.Scanner;

/**
 * Contains the necessary methods to play a game of Chess.
 *
 * @author Eligh alvarez
 */
public class Chess {

  // ASCII Console Color Escape Characters
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

  /**
   * Allows players to play a game of Chess with the standard game setup.
   * @param args (unused)
   */
  public static void main(String[] args) {
    printWelcome();
    Scanner userInput = new Scanner(System.in);
    Chess game = new Chess();
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
    String[] userTokens = userInput.nextLine().trim().split(" ");
    int x, y;

    if (userTokens.length != 2) {
      System.out.println("Selection command improperly formatted. Try again.");
      return;
    }
    x = columnToInt(userTokens[0]);
    if (x == 0) {
      System.out.println("Selection command improperly formatted. Try again.");
      return;
    }
    try {
      y = Integer.parseInt(userTokens[1]);
    } catch (NumberFormatException e) {
      System.out.println("Selection command improperly formatted. Try again.");
      return;
    }

    selectPiece(new Vector2(x, y));
    display();
  }

  /**
   * Takes user input to move the selected piece.
   * @param userInput The user input Scanner
   */
  public void executeMove(Scanner userInput) {
    String[] userTokens = userInput.nextLine().trim().split(" ");
    int x, y;

    if (userTokens.length != 2) {
      System.out.println("Movement command improperly formatted. Try again.");
      return;
    }
    x = columnToInt(userTokens[0]);
    if (x == 0) {
      System.out.println("Movement command improperly formatted. Try again.");
      return;
    }
    try {
      y = Integer.parseInt(userTokens[1]);
    } catch (NumberFormatException e) {
      System.out.println("Movement command improperly formatted. Try again.");
      return;
    }

    moveCurrentPiece(new Vector2(x, y));
    display();
  }

  /**
   * Brings the user to adjustment mode, where the user can add and remove pieces and adjust the
   * number of earned points.
   * @param userInput The user input Scanner
   */
  public void enterAdjustMode(Scanner userInput) {
    //System.out.println("\nYou have entered the adjustment mode. You may now add and remove "
    //    + "pieces "
    //    + "freely and adjust each color's accumulated points.");
    //System.out.println("");

    System.out.println("adjust functionality will be implemented later");
    userInput.nextLine();
  }

  /**
   * Loads the standard Chess pieces in a standard configuration in this Chess game.
   */
  private void setup() {
    String white = WHITE_PIECE_COLOR;
    String black = BLACK_PIECE_COLOR;
    // White Rooks
    gameBoard.putPiece(new Rook(white), new Vector2(1, 1));
    gameBoard.putPiece(new Rook(white), new Vector2(8, 1));
    // White Knights
    gameBoard.putPiece(new Knight(white), new Vector2(2, 1));
    gameBoard.putPiece(new Knight(white), new Vector2(7, 1));
    // White Bishops
    gameBoard.putPiece(new Bishop(white), new Vector2(3, 1));
    gameBoard.putPiece(new Bishop(white), new Vector2(6, 1));
    // White King
    gameBoard.putPiece(new King(white), new Vector2(5, 1));
    // White Queen
    gameBoard.putPiece(new Queen(white), new Vector2(4, 1));
    // White Pawns
    for (int i = 1; i <= 8; i++) {
      gameBoard.putPiece(new Pawn(white), new Vector2(i, 2));
    }

    // Black Rooks
    gameBoard.putPiece(new Rook(black), new Vector2(1, 8));
    gameBoard.putPiece(new Rook(black), new Vector2(8, 8));
    // Black Knights
    gameBoard.putPiece(new Knight(black), new Vector2(2, 8));
    gameBoard.putPiece(new Knight(black), new Vector2(7, 8));
    // Black Bishops
    gameBoard.putPiece(new Bishop(black), new Vector2(3, 8));
    gameBoard.putPiece(new Bishop(black), new Vector2(6, 8));
    // Black King
    gameBoard.putPiece(new King(black), new Vector2(5, 8));
    // Black Queen
    gameBoard.putPiece(new Queen(black), new Vector2(4, 8));
    // Black Pawns
    for (int i = 1; i <= 8; i++) {
      gameBoard.putPiece(new Pawn(black), new Vector2(i, 7));
    }
  }

  /**
   * Prints the current Board state to the console.
   */
  public void display() {
    System.out.println(gameBoard);
  }

  /**
   * Selects the piece located at the provided position. Notifies the user if a piece is not at
   * that position.
   * @param pos The position to select in [x, y] format
   */
  public void selectPiece(Vector2 pos) {
    if (gameBoard.getSquare(pos) == null || gameBoard.getSquare(pos).getPiece() == null) {
      System.out.println("There is no piece at that position.");
      gameBoard.setActiveSquare(new Vector2(0, 0));
    } else {
      gameBoard.setActiveSquare(pos);
    }
  }

  /**
   * Moves the currently selected piece to the provided location. Moving to an empty Square
   * simply moves the piece. Moving to a Square containing a piece of the opposite color "takes"
   * the piece. Other movements are not allowed.
   * @param pos The target of a move
   */
  public void moveCurrentPiece(Vector2 pos) {
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

      current.getPiece().move(new Vector2(pos.getX(), pos.getY()));
      target.setPiece(current.getPiece());
      current.setPiece(null);
      gameBoard.setActiveSquare(new Vector2(0, 0));

    } else if (target.getColor().equals(MOVE_SPACE_COLOR)) {
      current.getPiece().move(new Vector2(pos.getX(), pos.getY()));
      target.setPiece(current.getPiece());
      current.setPiece(null);
      gameBoard.setActiveSquare(new Vector2(0, 0));

    } else {
      System.out.println("You cannot move the specified piece here.");
    }
  }

  /**
   * Translates a user-provided column indicator (letter) to its corresponding int value
   * @param col The letter representation of the column
   * @return The int value of the column
   */
  public static int columnToInt(String col) {

    switch (col) {
      case "a":
        return 1;
      case "b":
        return 2;
      case "c":
        return 3;
      case "d":
        return 4;
      case "e":
        return 5;
      case "f":
        return 6;
      case "g":
        return 7;
      case "h":
        return 8;
      default:
        return 0;
    }
  }
}