package me.samxps.crafttunnel;

import java.net.InetSocketAddress;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProxyConfiguration implements Cloneable {

	private int bindPort;
	private String bindHost = null;
	
	private ProxyMode proxyMode = ProxyMode.PROXY_ONLY;
	private ServerType serverType = ServerType.ENTRY_POINT;
	
	private String masterAddress = null;
	private int masterPort = 25881;
	
	private boolean HAProxyHeaderEnabled = true;
	private String serverHost = "localhost";
	private int serverPort = 25565;
	
	public InetSocketAddress getBindAddress() {
		if (bindHost == null)
			return new InetSocketAddress(bindPort);
		return new InetSocketAddress(bindHost, bindPort);
	}
	
	public InetSocketAddress getMasterAddress() {
		if (masterAddress == null)
			return new InetSocketAddress(masterPort);
		return new InetSocketAddress(masterAddress, masterPort);
	}
	
	public InetSocketAddress getServerAddress() {
		if (masterAddress == null)
			return new InetSocketAddress(serverPort);
		return new InetSocketAddress(serverHost, serverPort);
	}
	
}
