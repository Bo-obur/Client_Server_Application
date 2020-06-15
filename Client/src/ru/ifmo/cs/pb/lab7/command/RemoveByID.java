package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.*;

import java.util.Scanner;

public class RemoveByID extends AbstractCommand {

      public RemoveByID() {
            this.setName("remove_by_id");
      }

      private static final long serialVersionUID = 1111111100000000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NeedArgumentException, OverflowArgsException, InvalidArgumentException {
            RemoveByID removeByID = new RemoveByID();
            if (arguments.length == 1)
                  throw new NeedArgumentException();
            if (arguments.length > 2)
                  throw new OverflowArgsException();
            try {
                  removeByID.setArgument(Long.parseLong(arguments[1]));
            } catch (RuntimeException eXception) {
                  throw new InvalidArgumentException();
            }
            return removeByID;
      }
}
