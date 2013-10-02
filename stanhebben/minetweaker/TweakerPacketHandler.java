package stanhebben.minetweaker;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class TweakerPacketHandler implements IPacketHandler {
	public static final String CHANNEL_SERVERSCRIPT = "MTServerScript";
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals(CHANNEL_SERVERSCRIPT)) {
			if (packet == null || packet.data == null) {
				return;
			} else if (packet.data.length == 0) {
				MineTweaker.instance.serverExecute(manager.getSocketAddress(), null);
			} else {
				MineTweaker.instance.serverExecute(manager.getSocketAddress(), packet.data);
			}
		}
	}
}
