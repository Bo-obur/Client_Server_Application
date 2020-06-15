package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.Builder;
import ru.ifmo.cs.pb.lab7.exception.CommandFailedException;
import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Register extends AbstractCommand {

      public Register() {
            this.setName("register");
      }
      
      private static final long serialVersionUID = 1111111110000000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException, CommandFailedException {
            Register register = new Register();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            register.setObject(Builder.buildNewUser(scanner, isScript));
            if (register.getObject() == null)
                  throw new CommandFailedException();
            return register;
      }
}
