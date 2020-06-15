package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.Builder;
import ru.ifmo.cs.pb.lab7.exception.CommandFailedException;
import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class AddIfMin extends AbstractCommand {

      public AddIfMin() {
            this.setName("add_if_min");
      }

      private static final long serialVersionUID = 1111111111111111100L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException, CommandFailedException {
            AddIfMin addIfMin = new AddIfMin();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            addIfMin.setObject(Builder.buildNewLaboratory(scanner, isScript));
            if (addIfMin.getObject() == null)
                  throw new CommandFailedException();
            return addIfMin;
      }
}
