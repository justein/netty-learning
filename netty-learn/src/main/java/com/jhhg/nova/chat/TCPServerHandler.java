package com.jhhg.nova.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/***
 * @ClassName: TCPServerHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:23
 * @version : V1.0
 */
public class TCPServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        /**获取客户端地址*/
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
        System.out.println("接收到来自客户端的消息："+msg);

        ctx.writeAndFlush("来自服务端："+UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
