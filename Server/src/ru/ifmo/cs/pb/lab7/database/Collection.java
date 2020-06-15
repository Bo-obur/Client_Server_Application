package ru.ifmo.cs.pb.lab7.database;

import ru.ifmo.cs.pb.lab7.object.Difficulty;
import ru.ifmo.cs.pb.lab7.object.Laboratory;
import ru.ifmo.cs.pb.lab7.object.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;

public class Collection {

      /**
       * Connection to DataBase
       */
      private final Connection connection;

      /**
       * Link to keep collection
       */
      private final LinkedList<Laboratory> laboratories;

      /**
       * Collection initialization date
       */
      private LocalDate initializationDate;

      /*
       * Helper methods
       */
      public LocalDate getInitializationDate() { return this.initializationDate; }

      public long getSize() { return this.laboratories.size(); }

      public String getType() { return this.laboratories.getClass().getName(); }

      /**
       * Updates initialization date
       */
      public void updateInitializationDate() { this.initializationDate = LocalDate.now(); }

      /**
       * Constructor
       *
       * @param connection  connection to DataBase
       * @throws            SQLException if something wrong with SQL data
       */
      public Collection(Connection connection) throws SQLException {
            this.laboratories = DataBaseCollection.loadCollection(connection);
            this.initializationDate = LocalDate.now();
            this.connection = connection;
      }

      public synchronized void addNewUserToDB(User user) throws SQLException {
            DataBaseUser.addNewUser(user, connection);
      }

      public synchronized boolean checkUserIfExist(User user) throws SQLException {
            return DataBaseUser.checkExistUser(user, connection);
      }

      /**
       * Clear collection
       */
      public void clear() throws SQLException {
            synchronized (laboratories) {
                  DataBaseCollection.deleteAll();
                  laboratories.clear();
            }
      }

      public void addNewItem(Laboratory laboratory, User user) throws SQLException {
            synchronized (laboratories) {
                  Long ID = DataBaseCollection.addNewLaboratory(laboratory, user);
                  laboratory.setId(ID);
                  laboratories.add(laboratory);
            }
      }

      public String printMinimalPoints() {
            StringBuilder stringBuilder = new StringBuilder();
            if (!laboratories.isEmpty()) {
                  laboratories.stream().sorted(Comparator.comparing(Laboratory::getMinimalPoint).reversed())
                          .forEach(lab -> stringBuilder.append("\n").append(lab.getMinimalPoint()));
            } else {
                  stringBuilder.append("Collection is empty!");
            }
            return stringBuilder.toString();
      }

      public String show() {
            StringBuilder stringBuilder = new StringBuilder();
            if (!laboratories.isEmpty()) {
                  laboratories.stream().skip(Math.max(0, laboratories.size() - 50))
                          .forEach(lab -> stringBuilder.append(lab.toString()).append("\n"));
            } else {
                  stringBuilder.append("Collection is empty!");
            }
            return stringBuilder.toString();
      }

      public boolean isLower(Laboratory laboratory) {
            if (laboratories.size() == 0) return true;
            for (Laboratory laboratory1 : laboratories) {
                  if (laboratory.compareTo(laboratory1) >= 1) return false;
            }
            return true;
      }

      public String printGreater(Difficulty difficulty) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!laboratories.isEmpty()) {
                  laboratories.stream().sorted(Laboratory::compareTo)
                          .skip(Math.max(0, laboratories.size() - 50))
                          .forEach(laboratory -> stringBuilder
                          .append((laboratory.getDifficulty()
                          .compareTo(difficulty)) > 0 ?
                           laboratory.toString() + "\n" : ""));
            } else {
                  stringBuilder.append("Collection is empty!");
            }
            return stringBuilder.toString();
      }

      public void deleteIfOwner(Long ID, String username) throws SQLException {
            if (DataBaseCollection.deleteByID(ID, username)) {
                  laboratories.removeIf(laboratory -> laboratory.getId().equals(ID));
            }
      }
}
