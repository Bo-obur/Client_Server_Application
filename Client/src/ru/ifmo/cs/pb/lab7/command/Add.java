package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.Builder;
import ru.ifmo.cs.pb.lab7.exception.CommandFailedException;
import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Add extends AbstractCommand {

      public Add() {
            this.setName("add");
      }

      private static final long serialVersionUID = 1111111111111111110L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException, CommandFailedException {
            Add add  = new Add();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            add.setObject(Builder.buildNewLaboratory(scanner, isScript));
            if (add.getObject() == null)
                  throw new CommandFailedException();
            return add;
      }
}
