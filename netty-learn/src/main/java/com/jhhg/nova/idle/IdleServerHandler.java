package com.jhhg.nova.idle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/***
 * @ClassName: IdleServerHandler
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/25 下午4:06
 * @version : V1.0
 */
public class IdleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        /**当有事件发生时触发*/
        if (evt instanceof IdleStateEvent) {
            String eventType = "";

            switch (((IdleStateEvent) evt).state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress()+" : "+eventType+"触发");
        }
        super.userEventTriggered(ctx, evt);
    }
}
