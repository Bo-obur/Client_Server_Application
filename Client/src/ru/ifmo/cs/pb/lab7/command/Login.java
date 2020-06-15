package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.Builder;
import ru.ifmo.cs.pb.lab7.exception.CommandFailedException;
import ru.ifmo.cs.pb.lab7.exception.NotNeedArgsException;

import java.util.Scanner;

public class Login extends AbstractCommand {

      public Login() {
            this.setName("login");
      }

      private static final long serialVersionUID = 1111111111100000000L;

      @Override
      public AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript)
              throws NotNeedArgsException, CommandFailedException {
            Login login = new Login();
            if (arguments.length > 1)
                  throw new NotNeedArgsException();
            login.setObject(Builder.buildNewUser(scanner, isScript));
            if (login.getObject() == null)
                  throw new CommandFailedException();
            return login;
      }
}
