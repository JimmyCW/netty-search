package com.wx.nettysearch.day1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author weixing
 * @date 2019/1/26
 **/
public class HelloWorldNetty {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //组
            serverBootstrap.group(bossGroup, workerGroup)
                    //通道
                    .channel(NioServerSocketChannel.class)
                    //子处理器
                    .childHandler(new HelloWorldNettyServerChannelInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(9002).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
