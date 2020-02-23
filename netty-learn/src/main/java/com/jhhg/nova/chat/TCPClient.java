package com.jhhg.nova.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/***
 * @ClassName: TCPClient
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:27
 * @version : V1.0
 */
public class TCPClient {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup clientGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new TCPClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",9800).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            clientGroup.shutdownGracefully();
        }


    }
}
