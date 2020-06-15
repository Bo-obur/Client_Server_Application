package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.Difficulty;
import ru.ifmo.cs.pb.lab7.object.User;

public class FilterGreaterDifficulty extends AbstractCommand {

      public FilterGreaterDifficulty() {
            this.setName("filter_greater_than_difficulty");
            this.setDescription("display   elements,   whose  'difficulty'" +
                                "\n                                       " +
                                "field  value is greater  than  the  given");
      }

      private static final long serialVersionUID = 1111111111111110000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            if (user.getUsername() != null) {
                  transferPackage.setServerAnswer(collection.printGreater((Difficulty) this.getArgument()));
                  if (transferPackage.getServerAnswer().trim().equals(""))
                        transferPackage.setServerAnswer("Could find item greater than the entered!");
                  transferPackage.setServerMessage("collection data sent to user.");
            } else {
                  transferPackage.setServerAnswer("You don't have permission to run this command");
                  transferPackage.setServerMessage("User doesn't have permissions to run this command");
            }
            return transferPackage;
      }
}
