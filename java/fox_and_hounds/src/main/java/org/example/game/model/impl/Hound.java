package org.example.game.model.impl;

import lombok.*;
import org.example.game.model.Figure;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hound implements Figure {
    private int row;
    private int col;

    
}
