package ru.ifmo.cs.pb.lab7.object;

import java.io.Serializable;

public class User implements Serializable {

      /**
       * Name of the user
       */
      private String username;

      public String getUsername() { return username; }

      public void setUsername(String username) { this.username = username; }

      /**
       * Password of the user to DataBase
       */
      private String password;

      public String getPassword() { return password; }

      public void setPassword(String password) { this.password = password; }

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = -1010101010101001110L;
}
