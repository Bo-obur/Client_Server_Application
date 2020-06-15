package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

import java.sql.SQLException;

public class Login extends AbstractCommand {

      public Login() {
            this.setName("login");
            this.setDescription("login  to  the  server");
      }

      private static final long serialVersionUID = 1111111111100000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            try {
                  boolean bool = collection.checkUserIfExist((User) this.getObject());
                  if (bool) {
                        transferPackage.setUser((User) this.getObject());
                        transferPackage.setServerAnswer("Login successfully Done.");
                        transferPackage.setServerMessage("User '" + ((User) this.getObject()).getUsername()
                                                         + "' successfully login to the server.");
                  } else {
                        throw new SQLException();
                  }
            } catch (SQLException exception) {
                  transferPackage.setServerAnswer("Username or password is not correct!");
                  transferPackage.setServerMessage("User can't login to the server");
            }
            return transferPackage;
      }
}
