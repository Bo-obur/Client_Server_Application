package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Exit extends AbstractCommand {

      public Exit() {
            this.setName("exit");
      }

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException {
            Exit exit = new Exit();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            return exit;
      }
}
