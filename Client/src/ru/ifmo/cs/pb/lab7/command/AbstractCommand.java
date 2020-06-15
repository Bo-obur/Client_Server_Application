package ru.ifmo.cs.pb.lab7.command;

import ru.ifmo.cs.pb.lab7.exception.*;

import java.io.Serializable;
import java.util.Scanner;

public abstract class AbstractCommand implements Serializable {

      /**
       * Command name
       */
      private String name;

      public String getName() { return name; }

      public void setName(String name) { this.name = name; }

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

      /**
       *
       * @return
       */
      public abstract AbstractCommand buildCommand(String[] arguments, Scanner scanner, boolean isScript) throws NotNeedArgsException, CommandFailedException, NeedArgumentException, OverflowArgsException, InvalidArgumentException;

      /**
       * Get instance of this class
       *
       * @return  an object of <code>AbstractCommand</code>
       */
      public AbstractCommand getInstance() { return this; }
}
