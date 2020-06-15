package ru.ifmo.cs.pb.lab7.database;

import ru.ifmo.cs.pb.lab7.object.*;

import java.sql.*;
import java.util.LinkedList;

public class DataBaseCollection {

      private static Connection connection;

      public static LinkedList<Laboratory> loadCollection(Connection connection) throws SQLException {
            DataBaseCollection.connection = connection;
            LinkedList<Laboratory> laboratories = new LinkedList<>();
            PreparedStatement preparedStatement = DataBaseCollection.connection.prepareStatement(SqlPattern.LOAD_COLLECTION);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  Laboratory laboratory = new Laboratory();
                  laboratory.setId(resultSet.getLong("id"));
                  laboratory.setName(resultSet.getString("name"));
                  laboratory.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate());
                  laboratory.setMinimalPoint(resultSet.getFloat("minimal_point"));
                  laboratory.setPersonalQualitiesMinimum(resultSet.getDouble("person_quality_min"));
                  laboratory.setTunedInWorks(resultSet.getInt("tuned_in_works"));
                  laboratory.setDifficulty(Difficulty.parseDifficulty(resultSet.getString("difficulty")));

                  Coordinates coordinates = new Coordinates();
                  coordinates.setX(resultSet.getLong("x"));
                  coordinates.setY(resultSet.getDouble("y"));
                  laboratory.setCoordinates(coordinates);

                  Discipline discipline = new Discipline();
                  discipline.setName(resultSet.getString("dis_name"));
                  discipline.setSelfStudyHours(resultSet.getInt("self_study_hours"));
                  discipline.setLabsCount(resultSet.getLong("labs_count"));
                  laboratory.setDiscipline(discipline);

                  laboratories.add(laboratory);
            }
            preparedStatement.close();
            return laboratories;
      }

      public static Long addNewLaboratory(Laboratory laboratory, User user) throws SQLException {
            PreparedStatement preparedStatement = DataBaseCollection.connection.prepareStatement(SqlPattern.ADD_LABORATORY);
            preparedStatement.setString(1, laboratory.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(laboratory.getCreationDate().atStartOfDay()));
            preparedStatement.setFloat(3, laboratory.getMinimalPoint());
            preparedStatement.setDouble(4, laboratory.getPersonalQualitiesMinimum());
            preparedStatement.setInt(5, laboratory.getTunedInWorks());
            preparedStatement.setString(6, (laboratory.getDifficulty() != null) ? String.valueOf(laboratory.getDifficulty()) : "");
            preparedStatement.setString(7, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            int ID = 0;
            while (resultSet.next())
                  ID = resultSet.getInt(1);
            preparedStatement = DataBaseCollection.connection.prepareStatement(SqlPattern.ADD_COORDINATES);
            preparedStatement.setLong(1, laboratory.getCoordinates().getX());
            preparedStatement.setDouble(2, laboratory.getCoordinates().getY());
            preparedStatement.setInt(3, ID);
            preparedStatement.executeUpdate();
            preparedStatement = DataBaseCollection.connection.prepareStatement(SqlPattern.ADD_DISCIPLINE);
            preparedStatement.setString(1, laboratory.getDiscipline().getName());
            preparedStatement.setInt(2, laboratory.getDiscipline().getSelfStudyHours());
            preparedStatement.setLong(3, laboratory.getDiscipline().getLabsCount());
            preparedStatement.setInt(4, ID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return (long) ID;
      }

      public static void deleteAll() throws SQLException {
            PreparedStatement preparedStatement = DataBaseCollection
                    .connection.prepareStatement(SqlPattern.CLEAR_COLLECTION);
            preparedStatement.executeUpdate();
            preparedStatement.close();
      }

      public static boolean deleteByID(Long ID, String username) throws SQLException {
            PreparedStatement preparedStatement = DataBaseCollection.connection
                                          .prepareStatement(SqlPattern.GET_USERNAME_BY_ID);
            int id = Math.toIntExact(ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String userName = null;
            while (resultSet.next())
                  userName = resultSet.getString("username");
            if (username.equals(userName)) {
                  preparedStatement = DataBaseCollection.connection
                          .prepareStatement(SqlPattern.DELETE_ITEM_BY_ID);
                  preparedStatement.setInt(1, id);
                  preparedStatement.executeUpdate();
                  return true;
            }
            throw new SQLException();
      }
}
