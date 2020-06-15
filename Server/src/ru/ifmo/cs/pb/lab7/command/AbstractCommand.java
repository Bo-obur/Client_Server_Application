package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.basics.TransferPackage;
import ru.ifmo.cs.pb.lab7.database.Collection;
import ru.ifmo.cs.pb.lab7.object.User;

import java.io.Serializable;

public abstract class AbstractCommand implements Serializable {

      /**
       * Command name
       */
      private String name;

      public String getName() { return name; }

      public void setName(String name) { this.name = name; }

      /**
       * Command description
       */
      private String description;

      public String getDescription() { return description; }

      public void setDescription(String description) { this.description = description; }

      /**
       * Command argument
       */
      public Object argument;

      public Object getArgument() { return argument; }

      public void setArgument(Object argument) { this.argument = argument; }

      /**
       * Command object
       */
      public Object object;

      public Object getObject() { return object; }

      public void setObject(Object object) { this.object = object; }

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = 1111111111111111111L;


      public abstract TransferPackage execute(Collection collection, User user);

      /**
       * Get instance of this class
       *
       * @return  an object of <code>AbstractCommand</code>
       */
      public AbstractCommand getInstance() {
            return this;
      }
}
