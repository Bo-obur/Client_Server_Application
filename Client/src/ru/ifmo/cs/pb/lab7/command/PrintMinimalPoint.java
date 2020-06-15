package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class PrintMinimalPoint extends AbstractCommand {

      public PrintMinimalPoint() {
            this.setName("print_field_descending_minimal_point");
      }

      private static final long serialVersionUID = 1111111111000000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException {
            PrintMinimalPoint printMinimalPoint = new PrintMinimalPoint();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            return printMinimalPoint;
      }
}
