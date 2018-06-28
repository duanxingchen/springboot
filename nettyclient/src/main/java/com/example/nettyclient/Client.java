package com.example.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")))
                                                .addLast(new ClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1", 10009).sync();
        while (true) {
            future.channel().writeAndFlush(Unpooled.copiedBuffer("88888段中荣".getBytes()));
            Thread.sleep(1000);
        }

    }

}

