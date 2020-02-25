package com.jhhg.nova.ws;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/***
 * @ClassName: WSServerHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/25 下午5:12
 * @version : V1.0
 */
public class WSServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到客户端："+ctx.channel().id().asLongText()+"发来的消息："+msg);

        /**回送消息给客户端*/
        ctx.writeAndFlush(new TextWebSocketFrame("服务器时间："+LocalDateTime.now()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active"+ctx.channel().id().asLongText());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive"+ctx.channel().id().asLongText());
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added"+ctx.channel().id().asLongText());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler removed"+ctx.channel().id().asLongText());
        super.handlerRemoved(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
