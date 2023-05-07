package org.example.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum Direction {
  NORTHWEST(-1, -1),
  NORTHEAST(-1, 1),
  SOUTHWEST(1, -1),
  SOUTHEAST(1, 1);

  final private int rowStep;
  final private int colStep;


}
