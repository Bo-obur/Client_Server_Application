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

      /**
       * Compares first object with the second
       *
       * @param difficulty1   enum types
       * @param difficulty2   enum types
       * @return              positive integer, if first object greater than second;
       *                      negative integer, if first object lower than second;
       *                      zero, if first object equals to second
       */
      public static int compare(Difficulty difficulty1, Difficulty difficulty2) {
            return Difficulty.getIntValueOf(difficulty1) - Difficulty.getIntValueOf(difficulty2);
      }

      /**
       * Gets integer value of enum types
       *
       * @param difficulty    enum types
       * @return              integer number
       */
      private static int getIntValueOf(Difficulty difficulty) {
            if (difficulty == null) return 0;
            switch (difficulty) {
                  case VERY_EASY:     return 1;
                  case NORMAL:        return 2;
                  case HARD:          return 3;
                  case IMPOSSIBLE:    return 4;
                  case TERRIBLE:      return 5;
                  default:            return -1;
            }
      }
}
