package com.jhhg.nova.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/***
 * @ClassName: TCPClientHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 下午5:31
 * @version : V1.0
 */
public class TCPClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接收到："+msg);

    }


}
