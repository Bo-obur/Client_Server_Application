package ru.ifmo.cs.pb.lab7.basics;

import ru.ifmo.cs.pb.lab7.command.AbstractCommand;
import ru.ifmo.cs.pb.lab7.object.User;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class TCPClient {

      private static String HOST;
      private static Integer PORT;

      private static User user = new User();

      private TCPClient setAddress(String[] arguments) {
            if (arguments.length != 1) {
                  System.out.println("Usage: java -jar Client.jar <HOST>:<PORT>");
                  System.exit(0);
            } else {
                  arguments = arguments[0].trim().split(":");
                  if (arguments.length == 2) {
                        try {
                              TCPClient.HOST = arguments[0];
                              TCPClient.PORT = Integer.parseInt(arguments[1]);
                        } catch (RuntimeException exception) {
                              System.out.println("Usage: java -jar Client.jar <HOST>:<PORT>");
                              System.exit(0);
                        }
                  } else {
                        System.out.println("Usage: java -jar Client.jar <HOST>:<PORT>");
                        System.exit(0);
                  }
            }
            return this;
      }

      private void run() throws IOException {

            Selector selector = Selector.open();
            SocketChannel channel = SocketChannel.open();

            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_CONNECT);
            channel.connect(new InetSocketAddress(HOST, PORT));

            BlockingQueue<AbstractCommand> commands = new ArrayBlockingQueue<>(2);

            Thread thread = new Thread(new ConsoleHandler(channel, selector, commands));
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();

            while (true) {

                  selector.select();
                  for (SelectionKey key : selector.selectedKeys()) {

                        if (!key.isValid()) continue;

                        if (key.isConnectable()) {
                              channel.finishConnect();
                              key.interestOps(SelectionKey.OP_WRITE);
                        }

                        if (key.isReadable()) {
                              ByteBuffer byteBuffer = ByteBuffer.allocate(Short.MAX_VALUE);
                              TransferPackage transferPackage = new TransferPackage();
                              try {
                                    channel.read(byteBuffer);
                                    transferPackage = (TransferPackage) Serializer.deserialize(byteBuffer.array());
                              } catch (IOException | ClassNotFoundException exception) {
                                    TCPClient.writelnOnConsole("Connection lost!!!");
                                    System.exit(0);
                              }
                              TCPClient.user = transferPackage.getUser();
                              System.out.println(transferPackage.getServerAnswer());
                              System.out.println();
                              key.interestOps(SelectionKey.OP_WRITE);
                        }

                        if (key.isWritable() && commands.size() != 0) {
                              TransferPackage transferPackage = new TransferPackage();
                              transferPackage.setUser(user);
                              transferPackage.setCommand(commands.poll());
                              if (transferPackage.getCommand().getName().equals("exit"))
                                    System.exit(0);
                              try {
                                    channel.write(ByteBuffer.wrap(Serializer.serialize(transferPackage)));
                              } catch (IOException exception) {
                                    TCPClient.writelnOnConsole("Connection lost!!!");
                                    System.exit(0);
                              }
                              key.interestOps(SelectionKey.OP_READ);
                        }
                  }
            }
      }



      public static void main(String[] args) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            try {
                  new TCPClient().setAddress(args).run();
            } catch (ConnectException exception) {
                  System.out.println("Server is offline...");
            } catch (IOException exception) {
                  System.out.println("Usage: java -jar Client.jar <HOST>:<PORT>");
            }
      }

      /**
       * Writes next line on console
       *
       * @param string  string, which is need to write on console
       */
      private static void writelnOnConsole(String string) {
            System.out.println(string);
      }



      static class Serializer {

            private static byte[] serialize(Object object) throws IOException {
                  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                  ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                  objectOutputStream.writeObject(object);
                  byte[] objectBytes = byteArrayOutputStream.toByteArray();
                  byteArrayOutputStream.flush();
                  objectOutputStream.close();
                  return objectBytes;
            }

            private static Object deserialize(byte[] objectBytes) throws IOException, ClassNotFoundException {
                  ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytes);
                  ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                  Object object = objectInputStream.readObject();
                  objectInputStream.close();
                  return object;
            }
      }

}
