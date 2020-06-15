package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.InvalidArgumentException;
import ru.ifmo.cs.pb.lab7.exception.NeedArgumentException;
import ru.ifmo.cs.pb.lab7.exception.OverflowArgsException;
import ru.ifmo.cs.pb.lab7.object.Difficulty;

import java.util.Scanner;

public class FilterGreaterDifficulty extends AbstractCommand {

      public FilterGreaterDifficulty() {
            this.setName("filter_greater_than_difficulty");
      }

      private static final long serialVersionUID = 1111111111111110000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NeedArgumentException, OverflowArgsException, InvalidArgumentException {
            FilterGreaterDifficulty filterGreaterDifficulty = new FilterGreaterDifficulty();
            if (arguments.length == 1)
                  throw new NeedArgumentException();
            if (arguments.length > 2)
                  throw new OverflowArgsException();
            try {
                  filterGreaterDifficulty.setArgument(Difficulty
                          .parseDifficulty(arguments[1].toUpperCase()));
            } catch (RuntimeException exception) {
                  throw new InvalidArgumentException();
            }
            return filterGreaterDifficulty;
      }
}
