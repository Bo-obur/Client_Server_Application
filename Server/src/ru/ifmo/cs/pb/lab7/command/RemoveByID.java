package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

import java.sql.SQLException;

public class RemoveByID extends AbstractCommand {

      public RemoveByID() {
            this.setName("remove_by_id");
            this.setDescription("remove an element from  the collection by" +
                                "\n                                       " +
                                "its 'id'");
      }

      private static final long serialVersionUID = 1111111100000000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            if (user.getUsername() != null) {
                  try {
                        collection.deleteIfOwner((Long) this.getArgument(), user.getUsername());
                        transferPackage.setServerAnswer("Successfully removed item from the collection.");
                        transferPackage.setServerMessage("User deleted item from the collection.");
                  } catch (SQLException exception) {
                        transferPackage.setServerAnswer("Item with this ID does not exist or you don't " +
                                                        "\nhave permission to delete it.");
                        transferPackage.setServerMessage("Could not remove item from the collection");
                  }
            } else {
                  transferPackage.setServerAnswer("You don't have permission to run this command.");
                  transferPackage.setServerMessage("User doesn't have permission to run this command!");
            }
            return transferPackage;
      }
}
