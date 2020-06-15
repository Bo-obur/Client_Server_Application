package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.database.DataBaseUser;
import ru.ifmo.cs.pb.lab7.object.User;

import java.sql.SQLException;

public class Clear extends AbstractCommand {

      public Clear() {
            this.setName("clear");
            this.setDescription("clear  collection");
      }

      private static final long serialVersionUID = 1111111111111111000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            try {
                  if (DataBaseUser.ROOT.equals(user.getUsername())) {
                        collection.clear();
                        collection.updateInitializationDate();
                        transferPackage.setServerAnswer("Collection successfully cleared.");
                        transferPackage.setServerMessage("Collection cleared by ROOT_USER.");
                  } else {
                        throw new SQLException();
                  }
            } catch (SQLException exception) {
                  transferPackage.setServerAnswer("You don't have permission to clear collection.");
                  transferPackage.setServerMessage("User doesn't have permission to clear collection.");
            }
            return transferPackage;
      }
}
