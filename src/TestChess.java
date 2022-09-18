import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestChess {
  public static void main(String[] args) {
    Board gameBoard = new Board();
    Chess game = new Chess(gameBoard);
    File testFile = new File("TestInput.txt");
    try (Scanner fileScanner = new Scanner(testFile)) {
      boolean exit = false;

      while(!exit) {
        switch (fileScanner.next()) {
          case "select":
            game.executeSelect(fileScanner);
            break;
          case "move":
            game.executeMove(fileScanner);
            break;
          case "adjust":
            game.enterAdjustMode(fileScanner);
            break;
          case "exit":
            exit = true;
            break;
          default:
            System.out.println("Command not recognized");
            fileScanner.nextLine();
        }
      }
      System.out.println("Exiting...");
    } catch (FileNotFoundException e) {
      System.out.println(testFile + " could not be located!");
    }
  }
}