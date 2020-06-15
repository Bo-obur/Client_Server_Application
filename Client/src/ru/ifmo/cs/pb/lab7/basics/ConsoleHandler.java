package ru.ifmo.cs.pb.lab7.basics;

import ru.ifmo.cs.pb.lab7.command.*;
import ru.ifmo.cs.pb.lab7.exception.*;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ConsoleHandler implements Runnable {

      /**
       * Counter of instance of <code>ConsoleHandler</code> class
       */
      private static int COUNTER = 1;

      private static SocketChannel channel;
      private static Selector selector;
      private static BlockingQueue<AbstractCommand> commands;

      /**
       * Constructor
       *
       * @param channel   channel for connection
       * @param selector  an object of <code>Selector</code> class
       * @param commands  a queue keeps commands
       */
      public ConsoleHandler(SocketChannel channel,
                            Selector selector,
                            BlockingQueue<AbstractCommand> commands) {
            ConsoleHandler.channel = channel;
            ConsoleHandler.selector = selector;
            ConsoleHandler.commands = commands;
      }

      /**
       * Constructor says that, if it will build more than 127 times
       * throw <code>StackOverflowException</code>
       */
      private ConsoleHandler() {
            if (COUNTER++ == Byte.MAX_VALUE)
                  throw new StackOverflowException();
      }

      /**
       * Scanner buffer for reading inputs
       */
      private Scanner scanner;
      private boolean isScript = false;

      /**
       * Sets value of scanner and isScript
       *
       * @param scanner  a <code>Scanner</code> buffer
       * @return         instance of this class
       */
      private ConsoleHandler setScanner(Scanner scanner) {
            this.scanner = scanner;
            this.isScript = true;
            return this;
      }

      /**
       *
       */
      @Override
      public void run() {

            if (!isScript) this.scanner = new Scanner(System.in);

            while (true) {
                  String[] arguments = readConsoleAsArray(scanner, isScript);
                  if (arguments[0].equals("")) continue;
                  try {
                        ConsoleHandler.checkAndRunExecute(arguments);
                        if (arguments[0].equals("execute_script")) continue;
                        commands.put(CommandInvoker.parseCommand(arguments, scanner, isScript));
                        Thread.sleep(500);
                  } catch (IOException exception) {
                        ConsoleHandler.writeOnConsole(exception.getMessage(), arguments[0]);
                  } catch (StackOverflowException exception) {
                        if (--COUNTER != 1) throw new StackOverflowException();
                        ConsoleHandler.writelnOnConsole("Stack Overflow!!!\n");
                  } catch (ScriptEndedException exception) {
                        break;
                  } catch (InterruptedException exception) {
                        exception.printStackTrace();
                  }
                  SelectionKey key = channel.keyFor(selector);
                  key.interestOps(SelectionKey.OP_WRITE);
                  selector.wakeup();
            }
      }

      /**
       * Read next line from buffer of <code>Scanner</code> and return it as array
       * of arguments
       *
       * @param scanner   a <code>Scanner</code> buffer
       * @param isScript  true - if it is script, false - else
       * @return          an array of strings
       */
      private static String[] readConsoleAsArray(Scanner scanner, boolean isScript) {
            String string;
            if (!isScript) {
                  ConsoleHandler.writeOnConsole("net:\\se\\ifmo\\ru\\console>");
                  string = readBuffer(scanner, isScript);
            } else {
                  string = readBuffer(scanner, isScript);
                  if (string == null) {
                        ConsoleHandler.writelnOnConsole("Script successfully executed.\n");
                        COUNTER--;
                        throw new ScriptEndedException();
                  } else {
                        ConsoleHandler.writelnOnConsole("net:\\se\\ifmo\\ru\\console>" + string);
                  }
            }
            return string.split("\\s+");
      }

      /**
       * Checks input for <code>ExecuteScript</code> command and if it's true
       * runs command 'execute_script'.
       *
       * @param arguments  input as array
       * @throws           NeedArgumentException if command needs to argument
       * @throws           OverflowArgsException if entered more than one arguments
       * @throws           IOException if something other I/O errors
       */
      private static void checkAndRunExecute(String[] arguments) throws IOException {
            if (arguments[0].toLowerCase().equals("execute_script")) {
                  if (arguments.length == 1)
                        throw new NeedArgumentException();
                  if (arguments.length > 2)
                        throw new OverflowArgsException();
                  try {
                        new ConsoleHandler().setScanner(
                                new Scanner(Paths.get(arguments[1]))).run();
                  } catch (IOException exception) {
                        COUNTER--;
                        ConsoleHandler.writeOnConsole(
                                "File with path '%s' could not find!\n\n", arguments[1]);
                  }
            }
      }

      /**
       * Reads next string line from the <code>Scanner</code>> buffer
       *
       * @param scanner   a <code>Scanner</code> buffer
       * @param isScript  true - if it is script, false - else
       * @return          next <code>String</code> line from the buffer
       */
      private static String readBuffer(Scanner scanner, boolean isScript) {
            if (!isScript)
                  return scanner.nextLine().trim();
            else if (scanner.hasNextLine())
                  return scanner.nextLine().trim();
            else return null;
      }

      /**
       * Writes next string  on console
       *
       * @param string  string, which is need to write on console
       */
      private static void writeOnConsole(String string) {
            System.out.print(string);
      }

      /**
       * Writes next string with pattern on console
       *
       * @param pattern  string pattern
       * @param string   string, which is need to write
       */
      private static void writeOnConsole(String pattern, String string) {
            System.out.printf(pattern, string);
      }

      /**
       * Writes next line on console
       *
       * @param string  string line, which is need to write on console
       */
      private static void writelnOnConsole(String string) {
            System.out.println(string);
      }


      /**
       * The static <code>CommandInvoker</code> class keeps on yourself all available
       * commands and help to find and parse it by their names
       */
      static final class CommandInvoker {

            /**
             * Map of all available commands by their names
             */
            static Map<String, AbstractCommand> commands = new HashMap<String, AbstractCommand>() {
                  {
                        command(new Add());
                        command(new AddIfMin());
                        command(new Clear());
                        command(new Exit());
                        command(new FilterGreaterDifficulty());
                        command(new Help());
                        command(new Info());
                        command(new Login());
                        command(new PrintMinimalPoint());
                        command(new Register());
                        command(new RemoveByID());
                        command(new Show());
                  }

                  /**
                   * Adds new command to the Map
                   *
                   * @param command  new object of <code>AbstractCommand</code>
                   */
                  void command(AbstractCommand command) {
                        put(command.getName(), command.getInstance());
                  }
            };

            /**
             * Parses an object of <code>AbstractCommand</code> class
             *
             * @param arguments  commands arguments
             * @param scanner    buffer for reading inputs
             * @param isScript   true - if it is script, false - else
             * @return           an object of <code>AbstractCommand</code> class
             * @throws           IOException  if something wrong with I/O
             */
            static AbstractCommand parseCommand(String[] arguments, Scanner scanner,
                                                boolean isScript) throws IOException {
                  if (commands.containsKey(arguments[0].toLowerCase()))
                        return commands.get(arguments[0].toLowerCase())
                                .buildCommand(arguments, scanner, isScript);
                  else throw new NoCommandException();
            }
      }

}
