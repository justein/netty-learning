package com.jhhg.nova.ws;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/***
 * @ClassName: WSServerInitializer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/25 下午5:06
 * @version : V1.0
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        /**因为websocket需要依托于http建立，所以需要先添加一个http解码器*/
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new HttpObjectAggregator(8192));
        /**WebSocketServerProtocolHandler封装了ws建立连接的相关操作，包括握手，心跳等*/
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        channelPipeline.addLast(new WSServerHandler());

    }
}
