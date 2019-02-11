package com.wx.nettysearch.day5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;

/**
 * @author weixing
 * @date 2019/2/10
 **/
public class MyWebSocketServer {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyWebSocketServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(9005).sync();
//            Channel channel = channelFuture.channel();
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            for (;;) {
//                channel.writeAndFlush(new TextWebSocketFrame(br.readLine() + "\r\n"));
//            }
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
