package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

public class PrintMinimalPoint extends AbstractCommand {

      public PrintMinimalPoint() {
            this.setName("print_field_descending_minimal_point");
            this.setDescription("print  the  'minimalPoint'  field  values" +
                                "\n                                       " +
                                "of  all  elements   in  descending  order");
      }

      private static final long serialVersionUID = 1111111111000000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            if (user.getUsername() != null) {
                  transferPackage.setServerAnswer(collection.printMinimalPoints());
                  transferPackage.setServerMessage("Value of minimal points sorted.");
            } else {
                  transferPackage.setServerAnswer("You don't have permission to run this command.");
                  transferPackage.setServerMessage("User doesn't have permission to run this command!");
            }
            return transferPackage;
      }
}
