package ru.ifmo.cs.pb.lab7.exception;

import java.io.IOException;

public class NeedArgumentException extends IOException {
      public NeedArgumentException() {
            super("Command '%s' needs an argument!\n\n");
      }
}
