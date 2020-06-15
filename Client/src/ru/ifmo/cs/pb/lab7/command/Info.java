package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Info extends AbstractCommand {

      public Info() {
            this.setName("info");
      }

      private static final long serialVersionUID = 1111111111110000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException {
            Info info = new Info();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            return info;
      }
}
