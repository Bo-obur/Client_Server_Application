package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.CommandFailedException;
import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Help extends AbstractCommand {

      public Help() {
            this.setName("help");
      }

      private static final long serialVersionUID = 1111111111111000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException, CommandFailedException {
            Help help = new Help();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            return help;
      }
}
