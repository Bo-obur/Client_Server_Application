package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

public class Info extends AbstractCommand {

      public Info() {
            this.setName("info");
            this.setDescription("display information about  the collection" +
                                "\n                                       " +
                                "(type,  initialization  date,  number  of" +
                                "\n                                       " +
                                "elements, etc.) in  the  standard  output" +
                                "\n                                       " +
                                "stream");
      }

      private static final long serialVersionUID = 1111111111110000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            if (user.getUsername() != null) {
                  String stringBuilder = String.format("%-36s - %s\n", "type of collection", collection.getType()) +
                                         String.format("%-36s - %s\n", "number of elements", collection.getSize()) +
                                         String.format("%-36s - %s", "initialization date", collection.getInitializationDate());
                  transferPackage.setServerAnswer(stringBuilder);
                  transferPackage.setServerMessage("Command Info typed by user.");
            } else {
                  transferPackage.setServerAnswer("You don't have permission get information about collection.");
                  transferPackage.setServerMessage("User doesn't have permission to get information about collection.");
            }
            return transferPackage;
      }
}
