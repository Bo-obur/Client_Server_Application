package ru.ifmo.cs.pb.lab7.object;

import java.io.Serializable;

public class Coordinates implements Serializable {

      /**
       * Maximum field value: 547
       */
      private long x;

      public long getX() { return x; }

      public void setX(long x) { this.x = x; }

      /**
       * Field value must be greater than -583
       */
      private double y;

      public double getY() { return y; }

      public void setY(double y) { this.y = y; }

      /**
       * Serial version Unique Identifier
       */
      private static final long serialVersionUID = -1111111111111111101L;
}
