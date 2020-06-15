package ru.ifmo.cs.pb.lab7.object;

import java.io.Serializable;

public class Discipline implements Serializable {

      /**
       * Field cannot be null, String cannot be empty
       */
      private String name;

      public String getName() { return name; }

      public void setName(String name) { this.name = name; }

      /**
       * The field may be null
       */
      private Integer selfStudyHours;

      public Integer getSelfStudyHours() { return selfStudyHours; }

      public void setSelfStudyHours(Integer selfStudyHours) { this.selfStudyHours = selfStudyHours; }

      /**
       * The field may be null
       */
      private Long labsCount;

      public Long getLabsCount() { return labsCount; }

      public void setLabsCount(Long labsCount) { this.labsCount = labsCount; }

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = -1010100001000010111L;
}
