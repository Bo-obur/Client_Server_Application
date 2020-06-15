package ru.ifmo.cs.pb.lab7.basics;

import org.apache.log4j.Logger;
import ru.ifmo.cs.pb.lab7.exception.ConnectionFailedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Sender implements Runnable {

      private final SocketChannel socketChannel;
      private final TransferPackage transferPackage;
      private final Set<SocketChannel> channelSet;

      private static final Logger LOGGER = Logger.getLogger(Sender.class.getSimpleName());

      public Sender(SocketChannel socketChannel, TransferPackage transferPackage, Set<SocketChannel> channelSet) {
            this.socketChannel = socketChannel;
            this.transferPackage = transferPackage;
            this.channelSet = channelSet;
      }

      @Override
      public void run() {
            try {
                  ByteBuffer byteBuffer = ByteBuffer.wrap(serialize(transferPackage));
                  socketChannel.write(byteBuffer);
                  LOGGER.info("Sent answer package to the client, address: " + socketChannel.getRemoteAddress());
            } catch (IOException exception) {
                  SocketAddress socketAddress = null;
                  try {
                        socketAddress = socketChannel.getRemoteAddress();
                        channelSet.remove(socketChannel);
                        socketChannel.close();
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
                  LOGGER.info("User disconnected by the server, address: " + socketAddress);
                  throw new ConnectionFailedException();
            }
      }

      private static byte[] serialize(Object object) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            byte[] objectBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            objectOutputStream.close();
            return objectBytes;
      }
}
