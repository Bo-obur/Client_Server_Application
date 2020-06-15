package ru.ifmo.cs.pb.lab7.basics;

import ru.ifmo.cs.pb.lab7.command.AbstractCommand;
import ru.ifmo.cs.pb.lab7.object.User;

import java.io.Serializable;

public class TransferPackage implements Serializable {

      /**
       * User
       */
      private User user;

      public User getUser() { return user; }

      public void setUser(User user) { this.user = user; }

      /**
       * Command
       */
      private AbstractCommand command;

      public AbstractCommand getCommand() { return command; }

      public void setCommand(AbstractCommand command) { this.command = command; }

      /**
       * Command execution result
       */
      private String answer;

      public String getAnswer() { return answer; }

      public void setAnswer(String answer) { this.answer = answer; }

      /**
       * Command execution result
       */
      private String serverAnswer;

      public String getServerAnswer() { return serverAnswer; }

      public void setServerAnswer(String serverAnswer) { this.serverAnswer = serverAnswer; }

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = -1010101010101010101L;
}
