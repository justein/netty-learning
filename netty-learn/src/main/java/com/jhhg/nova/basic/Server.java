package com.jhhg.nova.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/***
 * @ClassName: Server
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/22 下午6:04
 * @version : V1.0
 */
public class Server {

    public static void main(String[] args) throws Exception {

        /**
         * 事件循环组，就是死循环
         */
        /**
         * parent (acceptor) and the child (client)
         * */
        /**仅仅处理客户端过来的连接请求，接着将请求转给workerGroup，自己不做处理*/
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        /**真正处理客户端的数据读写*/
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            /**用于包装服务端的各种属性设置*/
            ServerBootstrap serverBootstrap=new ServerBootstrap();

            /**group方法用于设置acceptor和client，前者负责处理连接事件，后者负责处理读写事件*/
            /**childHandler子处理器,这个设置是对workerGroup起作用，所以叫 childHandler。
             * 也可以使用handler(),这样的话，就是对bosGroup起作用了。在客户端就只有一个handler()
             * 传入一个初始化器参数ServerInitializer（这里是自定义）*/
            /**ServerInitializer中的initChannel()方法会在channel被注册时调用*/
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ServerInitializer());
            /**绑定一个端口并且同步，生成一个ChannelFuture对象*/
            ChannelFuture channelFuture=serverBootstrap.bind(8899).sync();
            //对关闭的监听
            channelFuture.channel().closeFuture().sync();
        }
        finally {
            //循环组优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
