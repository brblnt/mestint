package org.example.game.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Move {
    private Direction direction;
    private Figure mover;
}
