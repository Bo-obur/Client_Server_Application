package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

public class Exit extends AbstractCommand {

      public Exit() {
            this.setName("exit");
            this.setDescription("terminated    the    client   application");
      }

      @Override
      public TransferPackage execute(Collection collection, User user) {
            return null;
      }
}
