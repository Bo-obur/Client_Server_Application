package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

public class Script extends AbstractCommand {

      public Script() {
            this.setName("execute_script");
            this.setDescription("read  and  execute the  script  from  the" +
                                "\n                                       " +
                                "specified   file, the   script   contains" +
                                "\n                                       " +
                                "commands   in  the  same  form  in  which" +
                                "\n                                       " +
                                "they   are   entered    by    the    user" +
                                "\n                                       " +
                                "interactively");
      }

      @Override
      public TransferPackage execute(Collection collection, User user) {
            return null;
      }
}
