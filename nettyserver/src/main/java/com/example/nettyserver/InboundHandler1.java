package com.example.nettyserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InboundHandler1 extends ChannelInboundHandlerAdapter {
    private  static final Logger LOGGER = LoggerFactory.getLogger(InboundHandler1.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("InboundHandler1.channelRead: ctx :" + ctx);

        // 通知执行下一个InboundHandler
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("InboundHandler1.channelReadComplete");
        ctx.flush();
    }
}
