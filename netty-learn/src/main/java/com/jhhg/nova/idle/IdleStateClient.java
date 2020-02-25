package com.jhhg.nova.idle;

import com.jhhg.nova.chat.TCPClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * @ClassName: TCPClient
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:27
 * @version : V1.0
 */
public class IdleStateClient {

    public static void main(String[] args) throws Exception {

        EventLoopGroup clientGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new TCPClientInitializer());
            /**因为要向服务端发送消息，所以这里需要拿到与服务端建立的channel*/
            Channel channel = bootstrap.connect("127.0.0.1",9800).channel();
            /**从控制台读入内容*/
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            for (;;) {
                channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
            }
        } finally {
            clientGroup.shutdownGracefully();
        }


    }
}
