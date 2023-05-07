package org.example.game.logic;

import java.util.LinkedList;
import java.util.Scanner;

import org.example.game.ai.GameSearch;
import org.example.game.ai.Move;
import org.example.game.ai.Position;

public class FoxAndHoundsGame extends GameSearch {
  static Scanner sc = new Scanner(System.in);
  static int MAXDEPTH = 4;

  @Override
  public boolean drawnPosition(Position p) {
    return false;
  }

  @Override
  public boolean wonPosition(Position p, boolean player) {
    FoxAndHoundsPosition pos = (FoxAndHoundsPosition) p;
    if (player) {
      for (int i = 0; i < pos.N; i++) if (pos.matrix[0][i] == 'f') return true;
      return false;
    } else {
      for (int x = 0; x < pos.N; x++)
        for (int y = 0; y < pos.N; y++)
          for (int dx = -1; dx <= 1; dx += 2)
            for (int dy = -1; dy <= 1; dy += 2)
              if (pos.matrix[x][y] == 'f' && x + dx >= 0 && x + dx < pos.N && y + dy >= 0 && y + dy < pos.N && pos.matrix[x + dx][y + dy] == ' ')
                return false;
      return true;
    }
  }

  @Override
  public float positionEvaluation(Position p, boolean player) {
    FoxAndHoundsPosition pos = (FoxAndHoundsPosition) p;
    if (wonPosition(p, player)) return player ? GameSearch.INFINITY : -GameSearch.INFINITY;
    for (int i = 0; i < pos.N; i++)
      for (int j = 0; j < pos.N; j++)
        if (pos.matrix[i][j] == 'f') return (player ? (float) pos.N - i - 1 : -(float) (pos.N - i - 1));
    return 0.0F;
  }

  @Override
  public void printPosition(Position p) {
    FoxAndHoundsPosition pos = (FoxAndHoundsPosition) p;
    for (int i = 0; i < pos.N; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < pos.N; j++) {
        System.out.print(pos.matrix[i][j] != ' ' ? pos.matrix[i][j] : '-');
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.print("  0 1 2 3 4 5 6 7\n");
  }

  @Override
  public Position[] possibleMoves(Position p, boolean player) {
    LinkedList<Position> children = new LinkedList<>();
    FoxAndHoundsPosition pos = (FoxAndHoundsPosition) p;
    Character actual = player ? 'f' : 'h';
    for (int x = 0; x < pos.N; x++)
      for (int y = 0; y < pos.N; y++)
        for (int dx = -1; dx <= 1; dx += 2)
          for (int dy = -1; dy <= 1; dy += 2) {
            if (pos.matrix[x][y] == actual && x + dx >= 0 && x + dx < pos.N && y + dy >= 0 && y + dy < pos.N && pos.matrix[x + dx][y + dy] == ' '
                    && (player || dx > 0)) {
              FoxAndHoundsPosition child = new FoxAndHoundsPosition();
              child.N = pos.N;
              child.matrix = new Character[pos.N][pos.N];
              for (int i = 0; i < pos.N; i++) for (int j = 0; j < pos.N; j++) child.matrix[i][j] = pos.matrix[i][j];
              child.matrix[x][y] = ' ';
              child.matrix[x + dx][y + dy] = actual;
              children.add(child);
            }
          }
    Position[] answer = new Position[children.size()];
    for (int i = 0; i < answer.length; i++) answer[i] = children.get(i);
    return answer;

  }

  @Override
  public Position makeMove(Position p, boolean player, Move m) {
    FoxAndHoundsPosition pos = (FoxAndHoundsPosition) p;
    FoxAndHoundsMove move = (FoxAndHoundsMove) m;
    Character actual = (player ? 'f' : 'h');
    if (pos.matrix[move.fromX][move.fromY] == actual && move.toX >= 0 && move.toX < pos.N && move.toY >= 0 && move.toY < pos.N &&
            pos.matrix[move.toX][move.toY] == ' ') {
      FoxAndHoundsPosition answer = new FoxAndHoundsPosition();
      answer.N = pos.N;
      answer.matrix = new Character[pos.N][pos.N];
      for (int i = 0; i < pos.N; i++) for (int j = 0; j < pos.N; j++) answer.matrix[i][j] = pos.matrix[i][j];
      answer.matrix[move.fromX][move.fromY] = ' ';
      answer.matrix[move.toX][move.toY] = actual;
      return answer;
    }
    return pos;
  }

  @Override
  public boolean reachedMaxDepth(Position p, int depth) {
    return depth >= MAXDEPTH;
  }

  @Override
  public Move createMove(Position p, boolean player) {
    int fromX;
    int fromY;
    int toX;
    int toY;
    FoxAndHoundsPosition pos = (FoxAndHoundsPosition) p;
    Character actual = (player ? 'f' : 'h');
    System.out.println("A te karaptered: ");
    System.out.println(actual == 'f' ? "RÓKA(f)" : "KUTYA(h)");
    boolean moveOK = false;
    do {
      System.out.println("Add meg jelenlegi poziciót!");
      fromX = sc.nextInt();
      fromY = sc.nextInt();
      System.out.println("Add meg hova lépjen!");
      toX = sc.nextInt();
      toY = sc.nextInt();
      if (fromX >= 0 && fromX < pos.N && fromY >= 0 && fromY < pos.N && toX >= 0 && toX < pos.N && toY >= 0 && toY < pos.N && java.lang.Math.abs(fromX - toX) == 1 && java.lang.Math.abs(fromY - toY) == 1 &&
              pos.matrix[fromX][fromY] == actual &&
              pos.matrix[toX][toY] == ' ')
        moveOK = true;
      else {
        System.out.println("Helytelen lepes: nem ures hely vagy ervenytelen lepes mod!");
      }
    }
    while (!moveOK);

    return new FoxAndHoundsMove(fromX, fromY, toX, toY);
  }

  public static void main(String[] a) {
    new FoxAndHoundsGame().playGame(new FoxAndHoundsPosition(), true);
    sc.close();
  }

}
