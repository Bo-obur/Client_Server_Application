package ru.ifmo.cs.pb.lab7.exception;

import java.io.IOException;

public class NotNeedArgsException extends IOException {
      public NotNeedArgsException() {
            super("Command '%s' does not need arguments!\n\n");
      }
}
