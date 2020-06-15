package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.Laboratory;
import ru.ifmo.cs.pb.lab7.object.User;

import java.sql.SQLException;

public class Add extends AbstractCommand {

      public Add() {
            this.setName("add");
            this.setDescription("add    new    element    to    collection");
      }

      private static final long serialVersionUID = 1111111111111111110L;

      @Override
      public TransferPackage execute(Collection collection, User user) {
            TransferPackage transferPackage = new TransferPackage();
            transferPackage.setUser(user);
            if (user.getUsername() != null) {
                  try {
                        collection.addNewItem((Laboratory) this.getObject(), user);
                        collection.updateInitializationDate();
                        transferPackage.setServerAnswer("Successfully added new item to the collection.");
                        transferPackage.setServerMessage("New item added to the collection by the user " + user.getUsername());
                  } catch (SQLException exception) {
                        exception.printStackTrace();
                  }
            } else {
                  transferPackage.setServerAnswer("You don't have permission to add new item to the collection!");
                  transferPackage.setServerMessage("User doesn't have permission to add new item to the collection.");
            }
            return transferPackage;
      }
}
