package ru.ifmo.cs.pb.lab7.basics;

import org.apache.log4j.Logger;
import ru.ifmo.cs.pb.lab7.exception.ConnectionFailedException;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.Callable;

public class Receiver implements Callable<TransferPackage> {

      private final SocketChannel channel;
      private final Set<SocketChannel> channelSet;

      private static final Logger LOGGER = Logger.getLogger(Receiver.class.getSimpleName());

      public Receiver(SocketChannel channel, Set<SocketChannel> channelSet) {
            this.channel = channel;
            this.channelSet = channelSet;
      }

      @Override
      public TransferPackage call() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(Short.MAX_VALUE);
            try {
                  channel.read(byteBuffer);
                  TransferPackage transferPackage = (TransferPackage) deserialize(byteBuffer.array());
                  LOGGER.info("Received new package from the user: " + channel.getRemoteAddress());
                  return transferPackage;
            } catch (IOException | ClassNotFoundException exception) {
                  SocketAddress socketAddress = null;
                  try {
                         socketAddress = channel.getRemoteAddress();
                         channelSet.remove(channel);
                         channel.close();
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
                  LOGGER.info("User disconnected by the server, address: " + socketAddress);
                  throw new ConnectionFailedException();
            }
      }

      private static Object deserialize(byte[] objectBytes) throws IOException, ClassNotFoundException {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
      }
}
