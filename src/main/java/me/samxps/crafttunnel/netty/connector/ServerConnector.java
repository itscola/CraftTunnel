package me.samxps.crafttunnel.netty.connector;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import me.samxps.crafttunnel.ProxyMode;
import me.samxps.crafttunnel.server.ProxyServer;
import me.samxps.crafttunnel.server.SlaveServer;

/**
 * A {@link ServerConnector} is responsible for opening a channel to a
 * remote server. <br>
 * <br>
 * The {@link DirectServerConnector} will be used or on the
 * {@link ProxyMode#PROXY_ONLY} mode by the {@link ProxyServer} or on 
 * the {@link ProxyMode#MULTI_PROXY_TUNNEL} by the {@link SlaveServer}.<br>
 * */
public interface ServerConnector {

	public ChannelFuture init(Channel clientChannel) throws Exception;
	
	public Future<?> exit() throws Exception;
	
}
