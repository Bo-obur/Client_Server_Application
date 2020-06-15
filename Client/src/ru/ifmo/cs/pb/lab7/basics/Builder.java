package ru.ifmo.cs.pb.lab7.basics;

import ru.ifmo.cs.pb.lab7.object.Coordinates;
import ru.ifmo.cs.pb.lab7.object.Difficulty;
import ru.ifmo.cs.pb.lab7.object.Discipline;
import ru.ifmo.cs.pb.lab7.object.Laboratory;
import ru.ifmo.cs.pb.lab7.object.User;

import java.util.Scanner;

public final class Builder {

      /**
       * Helper link for recently strings
       */
      private static String stringHelper;

      /**
       * Builds an objects of {@code Laboratory} class asking fields' value
       *
       * @param scanner  a {@code Scanner} buffer
       * @param isScript  true - if it is script, false - else
       * @return  an object of {@code Laboratory} class
       */
      public static Laboratory buildNewLaboratory(Scanner scanner, boolean isScript) {
            Laboratory laboratory = new Laboratory();
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'string' not less 4 chars\nname: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        if (stringHelper.equals("") || stringHelper.length() < 4 || stringHelper.length() > 255)
                              throw new RuntimeException();
                        laboratory.setName(stringHelper);
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            laboratory.setCoordinates(buildNewCoordinates(scanner, isScript));
            if (laboratory.getCoordinates() == null) return null;
            while (true) {
                  Builder.writeOnConsoleIf("Enter positive 'float' number\nminimal point: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  stringHelper = stringHelper.replace(',', '.');
                  try {
                        laboratory.setMinimalPoint(Float.parseFloat(stringHelper));
                        if (laboratory.getMinimalPoint() <= 0)
                              throw new RuntimeException();
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter positive 'double' number\npersonal qualities minimum: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  stringHelper = stringHelper.replace(',', '.');
                  try {
                        laboratory.setPersonalQualitiesMinimum(Double.parseDouble(stringHelper));
                        if (laboratory.getPersonalQualitiesMinimum() <= 0)
                              throw new RuntimeException();
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'integer' number\ntuned in works: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        laboratory.setTunedInWorks(Integer.parseInt(stringHelper));
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'enum' types, for INFO type '?'\ndifficulty -> ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  if (stringHelper.equals("?")) {
                        Builder.writeOnConsoleIf("" +
                              " - " + Difficulty.VERY_EASY + "\n" +
                                    " - " + Difficulty.NORMAL + "\n" +
                                          " - " + Difficulty.HARD + "\n" +
                                                " - " + Difficulty.IMPOSSIBLE + "\n" +
                                                      " - " + Difficulty.TERRIBLE + "\n", isScript);
                  } else try {
                        laboratory.setDifficulty(Difficulty.parseDifficulty(stringHelper.toUpperCase()));
                        if (laboratory.getDifficulty() == null) throw new RuntimeException();
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            laboratory.setDiscipline(buildNewDiscipline(scanner, isScript));
            if (laboratory.getDiscipline() == null) return null;
            return laboratory;
      }

      /**
       * Builds an objects of {@code Coordinates} class asking fields' value
       *
       * @param scanner  a {@code Scanner} buffer
       * @param isScript  true - if it is script, false - else
       * @return  an object of {@code Coordinates} class
       */
      public static Coordinates buildNewCoordinates(Scanner scanner, boolean isScript) {
            Coordinates coordinates = new Coordinates();
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'long' less or equal to 547\ncoordinate x: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        coordinates.setX(Long.parseLong(stringHelper));
                        if (coordinates.getX() > 547) throw new RuntimeException();
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'double' greater than -583\ncoordinate y: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  stringHelper = stringHelper.replace(',', '.');
                  try {
                        coordinates.setY(Double.parseDouble(stringHelper));
                        if (coordinates.getY() <= -583) throw new RuntimeException();
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            return coordinates;
      }

      /**
       * Builds an objects of {@code Discipline} class asking fields' value
       *
       * @param scanner  a {@code Scanner} buffer
       * @param isScript  true - if it is script, false - else
       * @return  an object of {@code Discipline} class
       */
      public static Discipline buildNewDiscipline(Scanner scanner, boolean isScript) {
            Discipline discipline = new Discipline();
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'string' not less 4 chars\ndiscipline name: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        if (stringHelper.equals("") || stringHelper.length() < 4 || stringHelper.length() > 255)
                              throw new RuntimeException();
                        discipline.setName(stringHelper);
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'integer' number\nself study hours: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        discipline.setSelfStudyHours(Integer.parseInt(stringHelper));
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'long' number\nlabs count: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        discipline.setLabsCount(Long.parseLong(stringHelper));
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            return discipline;
      }

      /**
       * Builds an objects of {@code User} class asking fields' value
       *
       * @param scanner  a {@code Scanner} buffer
       * @param isScript  true - if it is script, false - else
       * @return  an object of {@code User} class
       */
      public static User buildNewUser(Scanner scanner, boolean isScript) {
            User user = new User();
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'string' not less 4 chars\nusername: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        if (stringHelper.equals("") || stringHelper.length() < 4 || stringHelper.length() > 255)
                              throw new RuntimeException();
                        user.setUsername(stringHelper);
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            while (true) {
                  Builder.writeOnConsoleIf("Enter 'string' not less 6 chars\npassword: ", isScript);
                  if ((stringHelper = readBuffer(scanner, isScript)) == null) return null;
                  try {
                        if (stringHelper.equals("") || stringHelper.length() < 6 || stringHelper.length() > 255)
                              throw new RuntimeException();
                        user.setPassword(stringHelper);
                        break;
                  } catch (RuntimeException exception) {
                        Builder.writeOnConsoleIf("INCORRECT!\n", isScript);
                  }
            }
            return user;
      }

      /**
       * Reads next string line from the {@code Scanner} buffer
       *
       * @param scanner  a {@code Scanner} buffer
       * @param isScript  true - if it is script, false - else
       * @return  next {@code String} line from the buffer
       */
      private static String readBuffer(Scanner scanner, boolean isScript) {
            if (!isScript)
                  return scanner.nextLine().trim();
            else if (scanner.hasNextLine())
                  return scanner.nextLine().trim();
            else return null;
      }

      /**
       * Writes next string on console, if it isn't script
       *
       * @param string  string line to print on console
       * @param isScript  true - if it is script, false - else
       */
      private static void writeOnConsoleIf(String string, boolean isScript) {
            if (!isScript) System.out.print(string);
      }
}
