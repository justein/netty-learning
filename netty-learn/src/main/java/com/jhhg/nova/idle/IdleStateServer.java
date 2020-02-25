package com.jhhg.nova.idle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.awt.*;

/***
 * @ClassName: IdleStateServer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/25 下午3:19
 * @version : V1.0
 */
public class IdleStateServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    /**这个handler是作用于服务端的*/
                    .handler(new LoggingHandler())
                    .childHandler(new IdleStateServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(9800).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
