package stanhebben.minetweaker;

import stanhebben.minetweaker.util.DataArrayInputStream;
import stanhebben.minetweaker.util.DataArrayOutputStream;
import stanhebben.minetweaker.util.DefaultUndoStack;
import stanhebben.minetweaker.util.IOUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;

import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerTable;
import stanhebben.minetweaker.base.actions.ServerAction;
import stanhebben.minetweaker.base.functions.MaxFunction;
import stanhebben.minetweaker.base.functions.MinFunction;
import stanhebben.minetweaker.base.values.BlockGroupValue;
import stanhebben.minetweaker.base.values.FurnaceValue;
import stanhebben.minetweaker.base.values.ItemGroupValue;
import stanhebben.minetweaker.base.values.MineTweakerValue;
import stanhebben.minetweaker.base.values.OreDictValue;
import stanhebben.minetweaker.base.values.RecipesValue;
import stanhebben.minetweaker.rewriter.ScriptRewriter;
import stanhebben.minetweaker.script.ScriptEnvironment;
import stanhebben.minetweaker.script.ScriptEnvironmentDir;
import stanhebben.minetweaker.script.ScriptEnvironmentVirtual;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.parser.ParseException;
import stanhebben.minetweaker.tweaker.FuelTweaker;

import cpw.mods.fml.common.Mod;
//#ifdef OLDEVENTS
//+import cpw.mods.fml.common.Mod.Init;
//+import cpw.mods.fml.common.Mod.PreInit;
//+import cpw.mods.fml.common.Mod.PostInit;
//+import cpw.mods.fml.common.Mod.ServerStarting;
//+import cpw.mods.fml.common.Mod.ServerStopping;
//#else
import cpw.mods.fml.common.Mod.EventHandler;
//#endif
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.TimerTask;
import java.util.zip.CRC32;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.functions.FurnaceRemove;
import stanhebben.minetweaker.base.functions.PrintFunction;
import stanhebben.minetweaker.base.functions.RemoveRecipesFunction;
import stanhebben.minetweaker.base.values.FluidGroupValue;
import stanhebben.minetweaker.mods.buildcraft.BuildCraftModSupport;
import stanhebben.minetweaker.mods.ic2.IC2ModSupport;

/**
 * MineTweaker mod class. Includes the functionality needed for Forge mods,
 * as well as several mods to perform essential actions.
 * 
 * @author Stan Hebben
 */
@Mod(modid = "MineTweaker", name = "MineTweaker", version = MineTweaker.MCVERSION + "-2.1.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {TweakerPacketHandler.CHANNEL_SERVERSCRIPT}, packetHandler = TweakerPacketHandler.class)
public class MineTweaker {
	//#ifdef MC152
	//+public static final String MCVERSION = "1.5.2";
	//#endif
	//#ifdef MC162
	//+public static final String MCVERSION = "1.6.2";
	//#endif
	//#ifdef MC164
	public static final String MCVERSION = "1.6.4";
	//#endif
	
	@Instance("MineTweaker")
	public static MineTweaker instance;
	
	private DefaultUndoStack undoStack;
	
	private final TweakerNameSpace global;
	private final List<MineTweakerInterface> interfaces; // contains interfaces added by mods (mods.xxx)
	private final List<MineTweakerInterface> supportInterfaces; // contains interfaces for mod support (modSupport.xxx)
	private final TweakerTable mods = new TweakerTable();
	private final TweakerTable modSupport = new TweakerTable();
	
	private File tweakerDir;
	private File mainFile;
	private byte[] serverScriptBytes;
	private boolean canClear = true;
	
	private boolean canRollback = true;
	private boolean iAmTheServer = false;
	private boolean serverMode = false;
	private int serverClearLevel = 0;
	private SocketAddress serverAddress = null;
	private long serverScriptHash = 0;
	
	public MineTweaker() {
		undoStack = new DefaultUndoStack();
		
		interfaces = new ArrayList<MineTweakerInterface>();
		supportInterfaces = new ArrayList<MineTweakerInterface>();
		
		global = new TweakerNameSpace(null);
		global.put("minetweaker", MineTweakerValue.INSTANCE);
		global.put("recipes", RecipesValue.INSTANCE);
		global.put("furnace", FurnaceValue.INSTANCE);
		global.put("oreDict", OreDictValue.INSTANCE);
		global.put("mods", mods);
		global.put("modSupport", modSupport);
		global.put("min", MinFunction.INSTANCE);
		global.put("max", MaxFunction.INSTANCE);
		global.put("print", PrintFunction.INSTANCE);
		global.put("tile", new BlockGroupValue("tile"));
		global.put("blocks", new BlockGroupValue());
		global.put("item", new ItemGroupValue("item"));
		global.put("items", new ItemGroupValue());
		global.put("fluid", new FluidGroupValue("fluid"));
		global.put("fluids", new FluidGroupValue());
	}
	
	/**
	 * Internal method to execute a certain action. Should not be called directly,
	 * use Tweaker.apply instead.
	 * 
	 * @param action action to execute
	 */
	public void apply(IUndoableAction action) {
		if (canClear && !action.canUndo()) {
			Tweaker.log(Level.WARNING, "This action is not undoable: " + action.describe());
		}
		canClear &= action.canUndo();
		if (serverMode) canRollback &= action.canUndo();
		
		Tweaker.log(Level.INFO, action.describe());
		action.apply();
		undoStack.push(action);
	}
	
	/**
	 * Internal method to register a mod interface. Should not be called directly,
	 * use Tweaker.registerModInterface instead.
	 * 
	 * @param iface mod interface to register
	 */
	public void registerModInterface(MineTweakerInterface iface) {
		interfaces.add(iface);
		mods.indexSet(iface.getName(), iface.getValue());
		
		Tweaker.log(Level.INFO, "Loaded mod interface: " + iface.getName());
	}
	
	/**
	 * Internal method to register a mod support interface. Called for each mod
	 * support interface to add.
	 * 
	 * @param iface mod interface to register
	 */
	public void registerSupportInterface(MineTweakerInterface iface) {
		supportInterfaces.add(iface);
		modSupport.indexSet(iface.getName(), iface.getValue());
	}
	
	/**
	 * Clears the undo stack. Called by the minetweaker.clear function.
	 * 
	 * @return the old undo stack, used to restore the actions later on
	 */
	public DefaultUndoStack clear() {
		if (serverMode) serverClearLevel++;
		
		DefaultUndoStack old = undoStack;
		old.disable();
		undoStack = new DefaultUndoStack();
		return old;
	}
	
	/**
	 * Restores an undo stack erased by a clear call.
	 * 
	 * @param old the old undo stack
	 */
	public void undoClear(DefaultUndoStack old) {
		undoStack = old;
		old.enable();
		
		if (serverMode) serverClearLevel--;
	}
	
	/**
	 * Signals the start of a server. Called when the start server action is
	 * executed.
	 * 
	 * @param address server address
	 * @param serverScripts server scripts data, or null if there is no server script
	 */
	public void signalServerStart(final SocketAddress address, byte[] serverScripts) {
		long crc = 0;
		if (serverScripts != null) {
			CRC32 crc32 = new CRC32();
			crc32.update(serverScripts);
			crc = crc32.getValue();
		}
		
		if (serverAddress == null || !address.equals(serverAddress) || serverScriptHash != crc) {
			if (serverMode) {
				// rollback old things
				if (!canRollback) {
					Tweaker.log(Level.INFO, "Cannot rollback, time to restart buddy!");
					
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							if (serverAddress != null && address.equals(serverAddress)) {
								Minecraft.getMinecraft().displayGuiScreen(new GuiCannotRemodify(
										"Minecraft has been tweaked for another server",
										"with modifications that cannot be rolled back.",
										"Please restart your game."));
							} else {
								Minecraft.getMinecraft().displayGuiScreen(new GuiCannotRemodify(
										"This server's script has changed and the modifications",
										"of the old script cannot be rolled back.",
										"Please restart your game."));
							}
						}
					}, 1000);
					
					/*if (serverAddress != null && address.equals(serverAddress)) {
						//Minecraft.getMinecraft().shutdown();
						JOptionPane.showMessageDialog(null, "Minecraft has been tweaked for another server with modifications that cannot be rolled back. Please restart your game.");
						//Minecraft.getMinecraft().shutdown();
						//throw new RuntimeException("Minecraft has been tweaked for another server with modifications that cannot be rolled back. Please restart your game.");
						//Minecraft.getMinecraft().displayGuiScreen(new GuiCannotRemodify("Minecraft has been tweaked for another server with modifications that cannot be rolled back.", "Please restart your game."));
					} else {
						//Minecraft.getMinecraft().shutdown();
						JOptionPane.showMessageDialog(null, "This server's script has changed and the modifications of the old script cannot be rolled back. Please restart your game.");
						//Minecraft.getMinecraft().shutdown();
						//throw new RuntimeException("This server's script has changed and the modifications of the old script cannot be rolled back. Please restart your game.");
						//Minecraft.getMinecraft().displayGuiScreen(new GuiCannotRemodify("This server's script has changed and the modifications of the old script cannot be rolled back.", "Please restart your game."));
					}*/
				} else {
					while (serverMode) {
						IUndoableAction action = undoStack.pop();
						Tweaker.log(Level.INFO, action.describeUndo());
						action.undo();
					}
				}
			}

			try {
				serverMode = true;
				serverAddress = address;
				serverScriptHash = crc;
				
				if (serverScripts != null) {
					DataArrayInputStream input = new DataArrayInputStream(serverScripts);
					int numFiles = input.readInt();
					
					HashMap<String, String> files = new HashMap<String, String>();
					for (int i = 0; i < numFiles; i++) {
						String name = input.readUTF();
						String content = input.readUTF();
						files.put(name, content);
					}
					
					ScriptEnvironmentVirtual env = new ScriptEnvironmentVirtual(files);
	
					int numActionsBefore = undoStack.size();
					env.getFile("/main.cfg").execute(new TweakerNameSpace(global));
					Tweaker.log(Level.INFO, "Server script executed: " + (undoStack.size() - numActionsBefore) + " alterations");
				}
			} catch (TweakerException ex) {
				Tweaker.log(Level.SEVERE, ex.getFile().getName() + ":" + ex.getLine() + " " + ex.getExplanation());
			} catch (ParseException ex) {
				Tweaker.log(Level.SEVERE, ex.getFile() + ":" + ex.getLine() + " " + ex.getExplanation());
			}
		} else {
			// no modification needed
		}
	}
	
	/**
	 * Signals the end of a server. Called when the server start action is
	 * rolled back due to a disconnect or server close.
	 */
	public void signalServerEnd() {
		if (serverClearLevel == 0) serverMode = false;
	}
	
	/**
	 * Internal method to remove all recipes crafting this item. Do not call this
	 * method directly, use Tweaker.remove instead.
	 * 
	 * @param item item pattern to remove
	 * @return number of recipes removed
	 */
	public int remove(TweakerItemStackPattern item) {
		int total = 0;
		total += RemoveRecipesFunction.INSTANCE.remove(item);
		total += FurnaceRemove.INSTANCE.remove(item);
		for (MineTweakerInterface iface : interfaces) {
			total += iface.remove(item);
		}
		for (MineTweakerInterface iface : supportInterfaces) {
			total += iface.remove(item);
		}
		return total;
	}
	
	/**
	 * Method stub. Later on this will trigger the replacement of items in recipes
	 * with new items. Still figuring out the practical details of this one, though.
	 * 
	 * @param original original item
	 * @param replaced replaced item
	 * @return number of replacements
	 */
	public int replace(TweakerItemPattern original, TweakerValue replaced) {
		return 0;
	}
	
	/**
	 * Called when the server start packet has been received on the client.
	 * 
	 * @param from address with which the server connects
	 * @param serverScripts server scripts received
	 */
	public void serverExecute(SocketAddress from, byte[] serverScripts) {
		if (iAmTheServer) {
			Tweaker.log(Level.INFO, "Skipping local server script");
			return;
		}
		
		if (Tweaker.isLoggable(Level.FINEST)) {
			global.printContents();
		}
		
		Tweaker.apply(new ServerAction(from, serverScripts));
	}
	
	/**
	 * Retrieves the server scripts. Used by the connection handler.
	 * 
	 * @return server scripts
	 */
	public byte[] getServerScript() {
		return serverScriptBytes;
	}
	
	/**
	 * Returns true if the undo stack can be cleared. Will return false if the
	 * undo stack contains permanent actions.
	 * 
	 * @return true if the undo stack can be cleared
	 */
	public boolean canClear() {
		return canClear;
	}
	
	/**
	 * Returns the global namespace. The global namespace contains symbols such
	 * as minetweaker, furnace, recipes, mods, modSupport, item, items, tile,
	 * blocks, ...
	 * 
	 * Take care to not accidentally put things into the global namespace.
	 * Generally you want to wrap this namespace into a local namespace.
	 * 
	 * @return the global namespace
	 */
	public TweakerNameSpace getGlobal() {
		return global;
	}
	
	// ------------------------------
	// -- Forge mod implementation --
	// ------------------------------

	//#ifdef OLDEVENTS
	//+@PreInit
	//#else
	@EventHandler
	//#endif
	public void preInit(FMLPreInitializationEvent event) {
		Tweaker.onInit();
		
		File configFile = event.getSuggestedConfigurationFile();
		tweakerDir = new File(configFile.getParentFile(), "minetweaker");
		mainFile = new File(tweakerDir, "main.cfg");
		
		if (!tweakerDir.exists()) {
			tweakerDir.mkdir();
			if (configFile.exists()) {
				ScriptRewriter.rewrite(configFile, mainFile);
			} else {
				try {
					mainFile.createNewFile();
					Writer w = new FileWriter(mainFile);
					w.write("# See the forum post for example and documentation.\r\n");
					w.write("version 2;\r\n");
					w.write("\r\n");
					w.write("# Add your commands here\r\n");
					w.close();
				} catch (IOException ex) {
					Tweaker.log(Level.SEVERE, "Could not create script file", ex);
				}
			}
			
		}
		
		NetworkRegistry.instance().registerConnectionHandler(new TweakerConnectionHandler());
	}

	//#ifdef OLDEVENTS
	//+@Init
	//#else
	@EventHandler
	//#endif
	public void load(FMLInitializationEvent event) {
		// Stub Method
	}

	//#ifdef OLDEVENTS
	//+@PostInit
	//#else
	@EventHandler
	//#endif
	public void postInit(FMLPostInitializationEvent event) {
		MineTweakerRegistry.INSTANCE.init();
		
		// Register fuel tweaker
		FuelTweaker.INSTANCE.register();
		
		// Load mod support
		if (ModLoader.isModLoaded("BuildCraft|Core")) {
			MineTweaker.instance.registerSupportInterface(BuildCraftModSupport.INSTANCE);
			Tweaker.log(Level.INFO, "BuildCraft support loaded");
		}
		if (ModLoader.isModLoaded("IC2")) {
			MineTweaker.instance.registerSupportInterface(IC2ModSupport.INSTANCE);
			Tweaker.log(Level.INFO, "IC2 support loaded");
		}
		
		// Execute boot script
		
		try {
			ScriptEnvironment environment = new ScriptEnvironmentDir(tweakerDir);
			TweakerFile bootScript = new TweakerFile(environment, "/" + mainFile.getName(), mainFile);
			bootScript.execute(new TweakerNameSpace(global));
		} catch (IOException ex) {
			Tweaker.log(Level.SEVERE, "Could not read script file", ex);
		} catch (ParseException ex) {
			Tweaker.log(Level.SEVERE, ex.getFile() + ":" + ex.getLine() + " " + ex.getExplanation());
		} catch (TweakerException ex) {
			Tweaker.log(Level.SEVERE, ex.getFile().getName() + ":" + ex.getLine() + ex.getExplanation());
		} catch (Exception ex) {
			Tweaker.log(Level.SEVERE, "Could not process scipts", ex);
		}
	}
	
	//#ifdef OLDEVENTS
	//+@ServerStarting
	//#else
	@EventHandler
	//#endif
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new MineTweakerCommand());
	    
	    File saveDir = MineTweakerUtil.getWorldDirectory(event.getServer());
	    Tweaker.log(Level.FINE, "World dir: " + saveDir.getAbsolutePath());
	    File serverScriptDir = new File(saveDir, "minetweaker");
	    if (serverScriptDir.exists() && new File(serverScriptDir, "main.cfg").exists()) {
	    	HashMap<String, File> serverScripts = new HashMap<String, File>();
	    	collectServerScripts(serverScriptDir, serverScripts);
	    	
	    	DataArrayOutputStream output = new DataArrayOutputStream();
	    	output.writeInt(serverScripts.size());
	    	for (String name : serverScripts.keySet()) {
	    		String contents = IOUtil.readFileAsString(serverScripts.get(name));
	    		
	    		if (contents != null) {
	    			output.writeUTF(name);
	    			output.writeUTF(contents);
	    		}
	    	}
	    	
	    	this.serverScriptBytes = output.toByteArray();
	    }

		iAmTheServer = true;
		Tweaker.apply(new ServerAction(new InetSocketAddress("myself", 0), serverScriptBytes));
	}
	
	//#ifdef OLDEVENTS
	//+@ServerStopping
	//#else
	@EventHandler
	//#endif
	public void serverStop(FMLServerStoppingEvent ev) {
		iAmTheServer = false;
		this.serverScriptBytes = null;
	}
	
	// ---------------------
	// -- Private methods --
	// ---------------------
	
	private void collectServerScripts(File serverDir, HashMap<String, File> files) {
		collectServerScripts("/", serverDir, files);
	}
	
	private void collectServerScripts(String dir, File fdir, HashMap<String, File> files) {
		for (File f : fdir.listFiles()) {
			if (f.isDirectory()) {
				collectServerScripts(dir + f.getName() + "/", f, files);
			} else if (f.isFile()) {
				files.put(dir + f.getName(), f);
			}
		}
	}
}
