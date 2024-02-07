package main.java.com.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HelloWorldServer {
    public static void main(String[] args) throws Exception {
        // Configure the event loop groups for boss and worker threads
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // One boss thread
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // Default number of worker threads

        try {
            // Create the ServerBootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // Use NIO transport
                    .handler(new LoggingHandler(LogLevel.INFO)) // Enable logging
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec()); // Add HTTP codec
                            ch.pipeline().addLast(new HighPerformanceHandler()); // Add request handler
                        }
                    });

            // Bind and start the server
            ChannelFuture future = serverBootstrap.bind(4747).sync();
            System.out.println("Server started on port 4747");

            // Wait until the server socket is closed
            future.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop groups
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
