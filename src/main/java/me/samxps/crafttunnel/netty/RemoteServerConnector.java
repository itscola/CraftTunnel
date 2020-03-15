package me.samxps.crafttunnel.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.samxps.crafttunnel.CraftTunnel;

@RequiredArgsConstructor
public class RemoteServerConnector {

	@NonNull private String host;
	@NonNull private Integer port;
	private EventLoopGroup workerGroup;
	
	public ChannelFuture init(Channel clientChannel) throws Exception{
		workerGroup = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		b.group(workerGroup)
		 .option(ChannelOption.SO_KEEPALIVE, true)
		 .channel(NioSocketChannel.class)
		 .handler(new ChannelInitializer<SocketChannel>() {
			 protected void initChannel(SocketChannel ch) throws Exception {
				 ch.pipeline().addLast(new ServerChannelHandler(clientChannel));
			 };
		});
		
		return b.connect(host, port);
	}
	
	/**
	 * Releases all workers
	 * */
	public Future<?> exit() throws Exception {
		return workerGroup.shutdownGracefully();
	}
	
	/**
	 * Creates a new instance of {@link RemoteServerConnector} using the current
	 * host and port configuration of {@link CraftTunnel} instance.
	 * */
	public static RemoteServerConnector newDefault() {
		CraftTunnel main = CraftTunnel.getInstance();
		return new RemoteServerConnector(main.getRemoteHost(), main.getRemotePort());
	}
	
}