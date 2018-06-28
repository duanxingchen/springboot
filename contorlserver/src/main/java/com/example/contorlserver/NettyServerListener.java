package com.example.contorlserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class NettyServerListener{
    private  static final Logger LOGGER = LoggerFactory.getLogger(NettyServerListener.class);
    /*创建bootstrap*/
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    /*boss*/
    EventLoopGroup boos  =  new NioEventLoopGroup();
    /*work*/
    EventLoopGroup work = new NioEventLoopGroup();
    /**
     * 通道适配器
     */
    @Resource
    private ServerChannelHandlerAdapter channelHandlerAdapter;
    /*关闭服务器*/
    @PreDestroy
    public void close(){
        LOGGER.info("关闭服务器");
        boos.shutdownGracefully();
        work.shutdownGracefully();
    }

    /*开启服务线程*/
    @PostConstruct
    public void start() throws InterruptedException{
        /*从配置文件中获取服务器端口号*/
        /*int port = nettyConfig.getPort();*/
        int port = 11000;
        serverBootstrap.group(boos,work)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG,100)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>(){
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline()
                                .addLast(new LengthFieldPrepender(2))
                                .addLast(channelHandlerAdapter);
                            }
                        });
        LOGGER.info("netty服务器在[{}]端口启动监听", port);
        ChannelFuture f = serverBootstrap.bind(port).sync();
        f.channel().closeFuture().sync();
    }
}
