package stanhebben.minetweaker;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.util.ChatMessageComponent;

public class TweakerConnectionHandler implements IConnectionHandler {
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		String[] admins = MineTweaker.instance.getAdmins();
		boolean canSendErrors = false;
		if (admins.length == 0) {
			canSendErrors = true;
		} else {
			for (String s : admins) {
				if (s.equals(netHandler.getPlayer().getEntityName())) canSendErrors = true;
			}
		}
		if (canSendErrors) {
			for (String s : MineTweaker.instance.getErrorMessages()) {
				//#ifdef MC152
				//+netHandler.handleChat(new Packet3Chat(s));
				//#else
				netHandler.handleChat(new Packet3Chat(ChatMessageComponent.createFromText(s), false));
				//#endif
			}
			MineTweaker.instance.onAdminLogin(manager, netHandler);
		}
		if (MineTweaker.instance.getServerScript() != null) {
			manager.addToSendQueue(new Packet250CustomPayload(
					TweakerPacketHandler.CHANNEL_SERVERSCRIPT,
					MineTweaker.instance.getServerScript()));
		} else {
			manager.addToSendQueue(new Packet250CustomPayload(
					TweakerPacketHandler.CHANNEL_SERVERSCRIPT,
					new byte[0]));
		}
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		MineTweaker.instance.onLogout(manager);
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
	}
}
