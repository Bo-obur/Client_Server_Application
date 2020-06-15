package ru.ifmo.cs.pb.lab7.exception;

import java.io.IOException;

public class OverflowArgsException extends IOException {
      public OverflowArgsException() {
            super("Command '%s' takes only one argument!\n\n");
      }
}
