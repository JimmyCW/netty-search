package com.wx.nettysearch.day4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author weixing
 * @date 2019/2/10
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            IdleState idleState = idleStateEvent.state();
            String state = "";
            switch (idleState) {
                case READER_IDLE:
                    state = "读空闲";
                    break;
                case WRITER_IDLE:
                    state = "写空闲";
                    break;
                case ALL_IDLE:
                    state = "都空闲";
                    break;
                default:
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "状态:" + state);
            ctx.channel().close();

        }
    }
}
