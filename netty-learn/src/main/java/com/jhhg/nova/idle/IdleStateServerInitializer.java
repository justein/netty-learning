package com.jhhg.nova.idle;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/***
 * @ClassName: IdleStateServerInitializer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/25 下午3:24
 * @version : V1.0
 */
public class IdleStateServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new IdleStateHandler(5,7,10,TimeUnit.SECONDS));
        channelPipeline.addLast(new IdleServerHandler());
    }
}
