package ru.ifmo.cs.pb.lab7.exception;

import java.io.IOException;

public class InvalidArgumentException extends IOException {
      public InvalidArgumentException() {
            super("Command '%s' with invalid arguments!\n\n");
      }
}
