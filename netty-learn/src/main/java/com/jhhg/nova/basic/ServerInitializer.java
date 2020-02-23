package com.jhhg.nova.basic;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/***
 * @ClassName: ServerInitializer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/22 下午6:16
 * @version : V1.0
 */
/**
 *
 * */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    public ServerInitializer() {
        System.out.println("构造方法执行");
    }

    /**管道注册的时候，channelRegistered 会首先调用管道初始化这个方法*/
    @Override
    protected void initChannel(SocketChannel ch) {
        System.out.println("开始初始化管道");
        /**拿到通道的处理管道*/
        ChannelPipeline channelPipeline = ch.pipeline();
        /**给处理管道上添加处理逻辑，类似于拦截器，必须是多实例，每个连接对应不同的用户*/
        channelPipeline.addLast("httpServercode",new HttpServerCodec());
        channelPipeline.addLast("customHttpHandler",new HttpServerHandler());

    }


}
