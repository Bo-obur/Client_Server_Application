package ru.ifmo.cs.pb.lab7.object;

import java.io.Serializable;

public enum Difficulty implements Serializable {

      VERY_EASY, NORMAL, HARD, IMPOSSIBLE, TERRIBLE;

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = -1001010000100000100L;

      /**
       * Parses string value of the difficulty to it's enum value,
       * If string is not valid, so throws {@link RuntimeException}
       *
       * @param difficulty  a string value
       * @return            an enum value of the difficulty
       */
      public static Difficulty parseDifficulty(String difficulty) {
            if (difficulty == null || difficulty.equals("")) return null;
            switch (difficulty) {
                  case "VERY_EASY":   return VERY_EASY;
                  case "NORMAL":      return NORMAL;
                  case "HARD":        return HARD;
                  case "IMPOSSIBLE":  return IMPOSSIBLE;
                  case "TERRIBLE":    return TERRIBLE;
                  default: throw new RuntimeException();
            }
      }
}
