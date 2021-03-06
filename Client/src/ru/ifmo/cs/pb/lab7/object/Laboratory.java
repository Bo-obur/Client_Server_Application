package ru.ifmo.cs.pb.lab7.object;

import java.io.Serializable;
import java.time.LocalDate;

public class Laboratory implements Serializable {

      /**
       * The field cannot be null, the value of the field must be greater than 0,
       * the value of this field must be unique, the value of this field must be generated automatically
       */
      private Long id;

      public Long getId() { return id; }

      public void setId(Long id) { this.id = id; }

      /**
       * Field cannot be null, String cannot be empty
       */
      private String name;

      public String getName() { return name; }

      public void setName(String name) { this.name = name; }

      /**
       * Field cannot be null
       */
      private Coordinates coordinates;

      public Coordinates getCoordinates() { return coordinates; }

      public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

      /**
       * The field cannot be null. The value of this field should be generated automatically
       */
      private LocalDate creationDate;

      public LocalDate getCreationDate() { return creationDate; }

      public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

      /**
       * Field cannot be null, Field value must be greater than 0
       */
      private Float minimalPoint;

      public Float getMinimalPoint() { return minimalPoint; }

      public void setMinimalPoint(Float minimalPoint) { this.minimalPoint = minimalPoint; }

      /**
       * Field cannot be null, Field value must be greater than 0
       */
      private Double personalQualitiesMinimum;

      public Double getPersonalQualitiesMinimum() { return personalQualitiesMinimum; }

      public void setPersonalQualitiesMinimum(Double personalQualitiesMinimum) {
            this.personalQualitiesMinimum = personalQualitiesMinimum;
      }

      /**
       * Field cannot be null
       */
      private Integer tunedInWorks;

      public Integer getTunedInWorks() { return tunedInWorks; }

      public void setTunedInWorks(Integer tunedInWorks) { this.tunedInWorks = tunedInWorks; }

      /**
       * Field may be null
       */
      private Difficulty difficulty;

      public Difficulty getDifficulty() { return difficulty; }

      public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

      /**
       * Field cannot be null
       */
      private Discipline discipline;

      public Discipline getDiscipline() { return discipline; }

      public void setDiscipline(Discipline discipline) { this.discipline = discipline; }

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = 1010100000100111001L;

      /**
       * Constructor, in building an object updates value of field {@code creationDate}
       */
      public Laboratory() { this.creationDate = LocalDate.now(); }
}
