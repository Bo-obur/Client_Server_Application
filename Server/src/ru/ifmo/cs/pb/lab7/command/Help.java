package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

import java.util.ArrayList;

public class Help extends AbstractCommand {

      public Help() {
            this.setName("help");
            this.setDescription("display  help   for  available   commands");
      }

      private static final long serialVersionUID = 1111111111111000000L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            StringBuilder stringBuilder = new StringBuilder();
            commands.forEach(command -> stringBuilder.append(String
                  .format("%-36s - %s.\n", command.getName(), command.getDescription())));
            transferPackage.setServerAnswer(stringBuilder.toString());
            transferPackage.setServerMessage("Sent list of available commands to user");
            return transferPackage;
      }


      static ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>() {
            {
                  add(new Add());
                  add(new AddIfMin());
                  add(new Clear());
                  add(new Exit());
                  add(new FilterGreaterDifficulty());
                  add(new Help());
                  add(new Info());
                  add(new Login());
                  add(new PrintMinimalPoint());
                  add(new Register());
                  add(new RemoveByID());
                  add(new Script());
                  add(new Show());
            }
      };
}
