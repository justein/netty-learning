package com.jhhg.nova.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/***
 * @ClassName: TCPServerInitializer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:09
 * @version : V1.0
 */
public class TCPServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        /**拿到通信管道*/
        ChannelPipeline channelPipeline = ch.pipeline();
        /**给管道设置各种拦截器*/
        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        channelPipeline.addLast(new LengthFieldPrepender(4));
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new TCPServerHandler());
    }
}
