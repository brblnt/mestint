package org.example.game.logic;

import lombok.*;
import org.example.game.ai.Position;
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoxAndHoundsPosition extends Position {
    public int N;
    public Character[][] matrix;
         {N=8; matrix = new Character[][] {
                 {' ','h',' ','h',' ','h',' ','h'},
                 {'f',' ',' ',' ',' ',' ',' ',' '},
                 {' ',' ',' ',' ',' ',' ',' ',' '},
                 {' ',' ',' ',' ',' ',' ',' ',' '},
                 {' ',' ',' ',' ',' ',' ',' ',' '},
                 {' ',' ',' ',' ',' ',' ',' ',' '},
                 {' ',' ',' ',' ',' ',' ',' ',' '},
                 {' ',' ',' ',' ',' ',' ',' ',' '}
            };
         }


    @Override
    public String toString() {
        StringBuilder sb = new  StringBuilder("\n");
        for (int row=0; row<N; row++) {
            sb.append(row+" ");
            for (int col=0; col<N; col++) {
                sb.append(this.matrix[row][col]==' '?'-':matrix[row][col]);
                sb.append(" ");
            }
        sb.append("\n");
        }
        sb.append("  0 1 2 3 4 5 6 7");
        return sb.toString();

    }
}
