package com.jhhg.nova.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/***
 * @ClassName: TCPServerHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:23
 * @version : V1.0
 */
public class TCPServerHandler extends SimpleChannelInboundHandler<String> {

    /**想多了，netty里边有channelGroup来保存channel对象*/
    //List<Channel> clients = new ArrayList<>();
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
       Channel channel = ctx.channel();

       channelGroup.forEach(ch -> {
           if (ch!=channel) {
               ch.writeAndFlush(ch.id()+"对大家说："+msg+"\n");
           }else {
               ch.writeAndFlush("[自己]"+msg+"\n");
           }
       });
    }

    /**上线通知*/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id()+" handlerAdded");
        /**netty提供了针对group的writeandflush方法,这个方法会将消息发给组内所有channel*/
        channelGroup.writeAndFlush("[服务器] - "+ctx.channel().id()+"上线了！\n");

        /**将新上线的客户端缓存起来*/
        channelGroup.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    /**这个是在服务端主动去remove掉handler时候的回调函数，所以不能依赖这个来处理客户端连接异常
     * 需要通过心跳机制来处理，这个是作为心跳处理结果的回调
     * */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        channelGroup.writeAndFlush("[服务器] - "+ctx.channel().id()+"离开了！\n");
        /**正常来讲，应该调用一下remove方法移除下线的channel，但netty这里自动处理了，一旦客户端下线，会自动将channel从
         * group中移除*/
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id()+" channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id()+" channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
