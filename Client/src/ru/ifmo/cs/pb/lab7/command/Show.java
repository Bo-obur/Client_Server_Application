package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.CommandFailedException;
import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Show extends AbstractCommand {

      public Show() {
            this.setName("show");
      }

      private static final long serialVersionUID = 1111100000000000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException, CommandFailedException {
            Show show = new Show();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            return show;
      }
}
