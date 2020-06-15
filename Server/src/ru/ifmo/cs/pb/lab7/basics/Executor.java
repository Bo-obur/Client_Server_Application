package ru.ifmo.cs.pb.lab7.basics;

import org.apache.log4j.Logger;
import ru.ifmo.cs.pb.lab7.command.AbstractCommand;
import ru.ifmo.cs.pb.lab7.database.Collection;

import java.util.concurrent.Callable;

public class Executor implements Callable<TransferPackage> {

      private final TransferPackage transferPackage;
      private final Collection collection;

      private static final Logger LOGGER = Logger.getLogger(Executor.class.getSimpleName());

      public Executor(TransferPackage transferPackage, Collection collection) {
            this.transferPackage = transferPackage;
            this.collection = collection;
      }

      @Override
      public TransferPackage call() {
            AbstractCommand command = transferPackage.getCommand();
            TransferPackage transferPackage1 = command.execute(collection, transferPackage.getUser());
            LOGGER.info(transferPackage1.getServerMessage());
            return transferPackage1;
      }
}
