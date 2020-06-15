package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

public class Show extends AbstractCommand {

      public Show() {
            this.setName("show");
            this.setDescription("output  to  the  standard  output  stream" +
                                "\n                                       " +
                                "all the elements of  the collection  in a" +
                                "\n                                       " +
                                "string representation");
      }

      private static final long serialVersionUID = 1111100000000000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            if (user.getUsername() != null) {
                  transferPackage.setServerAnswer(collection.show());
                  transferPackage.setServerMessage("collection data sent to user.");
            } else {
                  transferPackage.setServerAnswer("You don't have permission to run command 'show'");
                  transferPackage.setServerMessage("User doesn't have permissions to run command 'show'");
            }
            return transferPackage;
      }
}
