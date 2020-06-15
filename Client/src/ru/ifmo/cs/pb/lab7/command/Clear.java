package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Clear extends AbstractCommand {

      public Clear() {
            this.setName("clear");
      }

      private static final long serialVersionUID = 1111111111111111000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException {
            Clear clear = new Clear();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            return clear;
      }
}
