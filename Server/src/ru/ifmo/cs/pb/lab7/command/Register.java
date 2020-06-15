package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

import java.sql.SQLException;

public class Register extends AbstractCommand {

      public Register() {
            this.setName("register");
            this.setDescription("registration  new  user  to  the server");
      }

      private static final long serialVersionUID = 1111111110000000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            try {

                  collection.addNewUserToDB((User) this.getObject());
                  transferPackage.setServerAnswer("Registration successfully Done.");
                  transferPackage.setServerMessage("User '" + this.getObject()
                                                    + "' successfully registered to the server.");
            } catch (SQLException exception) {
                  transferPackage.setServerAnswer("User with this username already exists!");
                  transferPackage.setServerMessage("Registration failed!");
            }
            return transferPackage;
      }
}
