package ru.ifmo.cs.pb.lab7.basics;

import org.apache.log4j.Logger;
import ru.ifmo.cs.pb.lab7.exception.ConnectionFailedException;
import ru.ifmo.cs.pb.lab7.database.Collection;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class TCPServer {

      private static Integer PORT;

      private static final Logger LOGGER = Logger.getLogger(TCPServer.class.getSimpleName());

      private TCPServer setAddress(String[] arguments) {
            if (arguments.length != 1) {
                  System.out.println("Usage: java -jar Server.jar <PORT>");
                  System.exit(0);
            } else {
                  try {
                        TCPServer.PORT = Integer.parseInt(arguments[0]);
                  } catch (RuntimeException exception) {
                        System.out.println("Usage: java -jar Server.jar <PORT>");
                        System.exit(0);
                  }
            }
            return this;
      }

      private static Collection loadCollection() {
            try (InputStream inputStream = new FileInputStream("database.properties")) {
                  Properties properties = new Properties();
                  properties.load(inputStream);
                  Connection connection = DriverManager.getConnection(
                          properties.getProperty("database.host"),
                          properties.getProperty("database.login"),
                          properties.getProperty("database.password")
                  );
                  Random random = new Random();
                  LOGGER.info("Collection loading from Database...");
                  Thread.sleep(100 + random.nextInt(500));
                  LOGGER.info("Complete loading - " + (10 + random.nextInt(10)) + "%");
                  Collection collection = new Collection(connection);
                  Thread.sleep(100 + random.nextInt(400));
                  LOGGER.info("Complete loading - " + (25 + random.nextInt(15)) + "%");
                  Thread.sleep(100 + random.nextInt(700));
                  LOGGER.info("Complete loading - " + (55 + random.nextInt(20)) + "%");
                  Thread.sleep(100 + random.nextInt(300));
                  LOGGER.info("Complete loading - " + (80 + random.nextInt(15)) + "%");
                  Thread.sleep(100 + random.nextInt(500));
                  LOGGER.info("Complete loading - 100%.");
                  return collection;
            } catch (FileNotFoundException exception) {
                  LOGGER.error("Could not find properties file!", exception);
                  System.exit(0);
            } catch (IOException exception) {
                  LOGGER.error("Could not read properties file!", exception);
                  System.exit(0);
            } catch (SQLException exception) {
                  LOGGER.error("Could not load collection from DataBase", exception);
                  System.exit(0);
            } catch (InterruptedException exception) {
                  exception.printStackTrace();
            }
            return null;
      }

      private void run() throws IOException {

            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            LOGGER.info("Server successfully connected to the Network...");

            /* Loading Collection from DataBase */
            Collection collection = TCPServer.loadCollection();

            LOGGER.info("Collection successfully loaded from DataBase...");

            Set<SocketChannel> channelSet = Collections.synchronizedSet(new HashSet<>());

            ExecutorService receiverService = Executors.newFixedThreadPool(2);
            ExecutorService executorService = Executors.newFixedThreadPool(2);

            Map<SelectionKey, Future<TransferPackage>> receivedData = Collections.synchronizedMap(new HashMap<>());
            Map<SelectionKey, Future<TransferPackage>> executedData = Collections.synchronizedMap(new HashMap<>());

            while (true) {
                  selector.select();
                  Iterator selectionKeys = selector.selectedKeys().iterator();
                  while (selectionKeys.hasNext()) {
                        SelectionKey key = (SelectionKey) selectionKeys.next();
                        selectionKeys.remove();
                        if (!key.isValid()) continue;
                        if (key.isAcceptable()) {
                              ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                              SocketChannel socketChannel;
                              try {
                                    socketChannel = serverSocketChannel1.accept();
                                    socketChannel.configureBlocking(false);
                                    socketChannel.register(selector, SelectionKey.OP_READ);
                                    LOGGER.info("New user joined to the server from: " + socketChannel.getRemoteAddress());
                                    channelSet.add(socketChannel);
                              } catch (IOException exception) {
                                    LOGGER.error("Could not accept new user by server!", exception);
                              }
                        }
                        if (key.isReadable()) {
                              SocketChannel socketChannel = (SocketChannel) key.channel();
                              try {
                                    receivedData.put(key, receiverService.submit(new Receiver(socketChannel, channelSet)));
                                    key.interestOps(SelectionKey.OP_WRITE);
                              } catch (ConnectionFailedException exception) {
                                    LOGGER.warn("User left the server!");
                              }
                        }
                        if (key.isWritable()) {
                              SocketChannel socketChannel = (SocketChannel) key.channel();
                              try {
                                    if (receivedData.containsKey(key) && receivedData.get(key).isDone()) {
                                          executedData.put(key, executorService.submit(new Executor(receivedData.get(key).get(), collection)));
                                          receivedData.remove(key);
                                    }
                                    if (executedData.containsKey(key) && executedData.get(key).isDone()) {
                                          new Thread(new Sender(socketChannel, executedData.get(key).get(), channelSet)).start();
                                          executedData.remove(key);
                                          key.interestOps(SelectionKey.OP_READ);
                                    }
                              } catch (ConnectionFailedException exception) {
                                    LOGGER.warn("User left the server!");
                              } catch (InterruptedException | ExecutionException exception) {
                                    exception.printStackTrace();
                              }
                        }
                  }
            }
      }


      public static void main(String[] args) throws IOException {
            new TCPServer().setAddress(args).run();
      }


}
