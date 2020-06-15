package ru.ifmo.cs.pb.lab7.exception;

import java.io.IOException;

public class NoCommandException extends IOException {
      public NoCommandException() {
            super("Command '%s' does not exist!\n\n");
      }
}
