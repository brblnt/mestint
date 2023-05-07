package puzzles;

import static java.lang.Math.abs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.AbstractState;
import search.State;

public class PuzzleN extends AbstractState {

  enum Direction {N, E, S, W}
  static Integer N;
  int[][] table;

  public PuzzleN(String fileName) {
    try {
      List<String> lines = MapReader.readTextFile(fileName);
      N = Integer.parseInt(lines.get(0));
      table = new int[N][N];
      for(int i = 0; i < N; i++) {
        String[] array = lines.get(i+1).split(";");
        for (int j = 0; j < N; j++) {
          table[i][j] = Integer.parseInt(array[j]);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public PuzzleN(PuzzleN puzzleN, Direction dir) {
    super(puzzleN);
    this.table = new int[N][N];

    for(int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
          table[i][j] = puzzleN.table[i][j];



    int zeroRow = 0, zeroCol = 0;

    for(int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        if (table[i][j] == 0) { zeroRow = i; zeroCol = j; }


    switch (dir) {
      case N : {
        table[zeroRow][zeroCol] = table[zeroRow-1][zeroCol];
        table[zeroRow-1][zeroCol] = 0;
        break;
      }
      case E : {
        table[zeroRow][zeroCol] = table[zeroRow][zeroCol+1];
        table[zeroRow][zeroCol+1] = 0;
        break;
      }
      case S : {
        table[zeroRow][zeroCol] = table[zeroRow+1][zeroCol];
        table[zeroRow+1][zeroCol] = 0;
        break;
      }
      case W : {
        table[zeroRow][zeroCol] = table[zeroRow][zeroCol-1];
        table[zeroRow][zeroCol-1] = 0;
        break;
      }
      default:
        System.out.println("Illegal State");
    }

  }


  @Override
  public Iterable<State> getPossibleMoves() {
    int zeroRow = 0, zeroCol = 0;
    for (int row = 0; row < N; row++)
      for (int col = 0; col < N; col++) if (table[row][col] == 0) { zeroRow = row; zeroCol = col;}
    List<State> moves = new ArrayList<>();
    if (zeroRow > 0)  moves.add(new PuzzleN(this, Direction.N));
    if (zeroCol > 0)  moves.add(new PuzzleN(this, Direction.W));
    if (zeroRow < N-1)  moves.add(new PuzzleN(this, Direction.S));
    if (zeroCol < N-1)  moves.add(new PuzzleN(this, Direction.E));
    return moves;
  }

  @Override
  public boolean isSolution() {
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (table[row][col] != row*N+col) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public double getHeuristic() {
    double sum = 0;
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        sum += abs(table[row][col]/N-row)+abs(table[row][col]%N-col);
      }
    }
    return sum;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PuzzleN puzzleN = (PuzzleN) o;
    return Arrays.deepEquals(table, puzzleN.table);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(table);
  }

  @Override
  public String toString() {
    return "PuzzleN{" +
            "table=" + Arrays.deepToString(table) +
            '}';
  }
}
