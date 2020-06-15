package ru.ifmo.cs.pb.lab7.exception;

import java.io.IOException;

public class CommandFailedException extends IOException {
      public CommandFailedException() {
            super("Command '%s' failed!\n\n");
      }
}
