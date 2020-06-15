package ru.ifmo.cs.pb.lab7.database;

public abstract class SqlPattern {

      public static final String LOAD_COLLECTION = "SELECT laboratory.id, laboratory.name, laboratory.creation_date, laboratory.minimal_point, laboratory.person_quality_min, laboratory.tuned_in_works, laboratory.difficulty, coordinates.x, coordinates.y, discipline.dis_name, discipline.self_study_hours, discipline.labs_count FROM laboratory INNER JOIN coordinates ON laboratory.id = coordinates.lab_id INNER JOIN discipline ON laboratory.id = discipline.lab_id";

      public static final String ADD_LABORATORY = "INSERT INTO laboratory(name, creation_date, minimal_point, person_quality_min, tuned_in_works, difficulty, username) VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";

      public static final String ADD_COORDINATES = "INSERT INTO coordinates(x, y, lab_id) VALUES(?, ?, ?)";

      public static final String ADD_DISCIPLINE = "INSERT INTO discipline(dis_name, self_study_hours, labs_count, lab_id) VALUES(?, ?, ?, ?)";

      public static final String ADD_NEW_USER = "INSERT INTO users(username, password) VALUES(?, ?)";

      public static final String CHECK_EXIST_USER = "SELECT users.password FROM users WHERE username = ?";

      public static final String CLEAR_COLLECTION = "DELETE FROM laboratory";

      public static final String GET_USERNAME_BY_ID = "SELECT laboratory.username FROM laboratory WHERE id = ?";

      public static final String DELETE_ITEM_BY_ID = "DELETE FROM laboratory WHERE id = ?";
}
