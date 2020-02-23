package com.jhhg.nova.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/***
 * @ClassName: TCPServer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:05
 * @version : V1.0
 */
public class TCPServer {

    public static void main(String[] args) throws InterruptedException {

        /**定义事件循环组*/
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TCPServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(9800).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
