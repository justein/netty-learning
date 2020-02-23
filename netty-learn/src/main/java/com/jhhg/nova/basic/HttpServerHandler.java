package com.jhhg.nova.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/***
 * @ClassName: HttpServerHandler
 * @Description: 自定义的服务端Handler
 * @Author: Lyn
 * @Date: 2020/2/22 下午6:22
 * @version : V1.0
 */

/**
 * 处理顺序如下：
 * handlerAdded
 * channelRegistered
 * channelActive
 * 请求方法名:GET（channelRead0）
 * （下面的表示的是断开连接后）
 * 1.如果是使用curl ：连接会立刻关闭
 * 2.如果是浏览器访问，http1.0：它是短连接，会立刻关闭。http1.1，是长连接，连接保持一段时间
 * 如果设置为http1.1，请求完毕后连接不会立即关闭，所以管道的inactive和unregister事件不会执行。只有超时或者
 * 手动关闭浏览器才会关闭连接，上述两个事件也才会触发。
 * channelInactive
 * channelUnregistered
 */

/**这个类继承自InboundHandler，表示处理读入的IO事件，还有一个OutboundHandler，用于处理写出的IO事件*/

/**用户通过拓展实现SimpleChannelInboundHandler来定义业务处理的ChannelInboundHandler时，
 * 通过指定泛型参数I，限制该ChannelInboundHandler只处理Channel读入的类型为I的数据；比如这里就是只处理HttpObject*/
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {

        if (msg instanceof HttpRequest) {
            /**如果用浏览器访问，会有一个favor.ico的请求*/
            String uri = ((HttpRequest) msg).uri();
            if (uri.contains("favicon.ico")) {
                System.out.println("来自浏览器favicon请求，直接返回");
                return;
            }


            System.out.println(msg.getClass().getName());
            /**定义要返回给客户端的内容*/
            ByteBuf responseContent = Unpooled.copiedBuffer("Hello World",CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,responseContent);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,responseContent.readableBytes());

            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
