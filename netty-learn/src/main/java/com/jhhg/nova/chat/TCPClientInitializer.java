package com.jhhg.nova.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/***
 * @ClassName: TCPClientInitializer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:29
 * @version : V1.0
 */
public class TCPClientInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        /**聊天时需要使用DelimiterBasedFrame这个解码器*/
//        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
//        channelPipeline.addLast(new LengthFieldPrepender(4));
        channelPipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new TCPClientHandler());
    }
}
