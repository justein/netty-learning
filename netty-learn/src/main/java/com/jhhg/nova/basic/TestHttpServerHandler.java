package com.jhhg.nova.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/***
 * @ClassName: TestHttpServerHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/23 上午10:08
 * @version : V1.0
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //channelRead0读取客户端请求，并返回响应的方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //如果不加这个判断使用curl 测试会报错，使用curl测试命令curl "http://localhost:8899"
        //判断这个是不是httprequest请求
        if (msg instanceof HttpRequest) {
            System.out.println(msg.getClass());
            System.out.println(ctx.channel().remoteAddress());
            HttpRequest httpRequest= (HttpRequest) msg;
            URI uri=new URI(httpRequest.uri());
            //判断url是否请求了favicon.ico
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了favicon.ico");
                return;
            }
            /**
             * 上面这段代码是验证如果用浏览器访问
             * chrome浏览器发起了两次请求，一次是发起的端口，第二次是请求/favicon.ico图标
             * 具体可以查看chrome的请求
             */
            System.out.println("请求方法名:"+httpRequest.method().name());
            //ByteBuf,netty中极为重要的概念，代表响应返回的数据
            ByteBuf content = Unpooled.copiedBuffer("HelloWorld!", CharsetUtil.UTF_8);
            //构造一个http响应,HttpVersion.HTTP_1_1:采用http1.1协议，HttpResponseStatus.OK：状态码200
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //如果只是调用write方法，他仅仅是存在缓冲区里，并不会返回客户端
            //调用writeAndFlush可以
            ctx.writeAndFlush(response);
        }
    }

    /**
     * 处理顺序如下：
     * handlerAdded
     * channelRegistered
     * channelActive
     * 请求方法名:GET（channelRead0）
     * （下面的表示的是断开连接后）
     * 1.如果是使用curl ：连接会立刻关闭
     * 2.如果是浏览器访问，http1.0：它是短连接，会立刻关闭。http1.1，是长连接，连接保持一段时间
     * channelInactive
     * channelUnregistered
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }
}
