package ru.ifmo.cs.pb.lab7.object;

import java.io.Serializable;

public class Coordinates implements Serializable, Comparable<Coordinates> {

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

      /**
       * Compares this object with another object of current class
       *
       * @param coordinates object of class {@code Coordinates}
       * @return            positive integer, if this object greater than another;
       *                    negative integer, if this object lower than another;
       *                    zero, if this object equals to another
       */
      @Override
      public int compareTo(Coordinates coordinates) {
            return (int) this.x + (int) this.y - (int) coordinates.x - (int) coordinates.y;
      }

      @Override
      public String toString() {
            return
                    "COORDINATES\n" +
                    "\t\tx: " + this.x + "\n" +
                    "\t\ty: " + this.y;
      }
}
