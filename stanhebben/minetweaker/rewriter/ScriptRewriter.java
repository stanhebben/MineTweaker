package stanhebben.minetweaker.rewriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import stanhebben.minetweaker.util.Arrays2;
import stanhebben.minetweaker.api.TweakerExecuteException;

public class ScriptRewriter {
	private ScriptRewriter() {}

	private static final HashMap<String, TweakCommand> COMMANDS;
	
	static {
		COMMANDS = new HashMap<String, TweakCommand>();
		COMMANDS.put("debug", TweakCommand.DEBUG);
		COMMANDS.put("version", TweakCommand.VERSION);
		COMMANDS.put("alias", TweakCommand.ALIAS);
		COMMANDS.put("oredict", TweakCommand.OREDICT);
		COMMANDS.put("remove", TweakCommand.REMOVE);
		COMMANDS.put("removeshaped", TweakCommand.REMOVESHAPED);
		COMMANDS.put("removeshapeless", TweakCommand.REMOVESHAPELESS);
		COMMANDS.put("addshaped", TweakCommand.ADDSHAPED);
		COMMANDS.put("addshapeless", TweakCommand.ADDSHAPELESS);
		COMMANDS.put("removefurnace", TweakCommand.REMOVEFURNACE);
		COMMANDS.put("addfurnace", TweakCommand.ADDFURNACE);
		COMMANDS.put("oredictadd", TweakCommand.OREDICTADD);
		COMMANDS.put("oredictremove", TweakCommand.OREDICTREMOVE);
		COMMANDS.put("setfurnacefuel", TweakCommand.SETFURNACEFUEL);
		COMMANDS.put("setname", TweakCommand.SETNAME);
		COMMANDS.put("setlocalizedname", TweakCommand.SETLOCALIZEDNAME);
	}
	
	public static void rewrite(File oldFile, File newFile) {
		try {
			HashSet<String> aliases = new HashSet<String>();
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new FileReader(oldFile));
			String line = reader.readLine();
			int lineNumber = 1;
			
			while (line != null) {
				String suffix = "";
				int hash = line.indexOf('#');
				if (hash >= 0) {
					suffix = line.substring(hash);
					line = line.substring(0, hash);
				}
				line = line.trim();
				
				if (line.length() == 0) {
					output.append(suffix).append("\r\n");
				} else {
					String[] elements = Arrays2.split(line, ' ');
					
					String command = elements[0].toLowerCase();
					if (!COMMANDS.containsKey(command)) {
						System.out.println("[MineTweaker] [Line " + lineNumber + "] Unknown command: " + elements[0] + " - ignored");
					} else {
						switch (COMMANDS.get(command)) {
						case DEBUG:
							if (elements.length > 1) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: parameters in the debug command ignored");
							}
							output.append("minetweaker.debug = true;").append(suffix).append("\r\n");
							break;
						case VERSION:
							if (elements.length < 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: no version specified");
							} else if (elements.length > 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: too many parameters for version, they have been ignored");
							}
							output.append("version 2;").append(suffix).append("\r\n");
							break;
						case ALIAS: {
							if (elements.length < 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing parameters in alias command");
								break;
							} else if (elements.length > 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: too many parameters in the alias command. They have been ignored.");
							}
							
							try {
								String id = processItemId(elements[2], aliases);
								if (isValidIdentifier(elements[1])) {
									aliases.add(elements[1]);
									output.append(elements[1]).append(" = ").append(id).append(";").append(suffix).append("\r\n");
								} else {
									System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: invalid identifier: " + elements[1]);
								}
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						}
						case OREDICT: {
							if (elements.length < 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing name for oreDict command");
								break;
							} else if (elements.length > 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: too many parameters for oreDict command, they have been ignored");
								break;
							}
							aliases.add(elements[1]);
							output.append(elements[1]).append(" = oreDict.").append(elements[1]).append(";").append(suffix).append("\n");
							break;
						}
						case REMOVE:
							try {
								String stack = processItemStackPattern(elements[1], aliases);
								output.append("minetweaker.remove(").append(stack).append(");").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						case REMOVESHAPED: {
							if (elements.length < 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: too few arguments for removeShaped command");
								break;
							}
							
							try {
								String target = processItemStackPattern(elements[1], aliases);
								
								if (elements.length == 2) {
									output.append("recipes.removeShaped(").append(target).append(");").append(suffix).append("\r\n");
								} else {
									if (!elements[2].equals("=")) {
										System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: = expected");
										break;
									} else if (elements.length < 4) {
										System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: recipe expected");
										break;
									}
									
									ArrayList<ArrayList<String>> recipe = new ArrayList<ArrayList<String>>();
									int x = 0;
									int y = 0;
									recipe.add(new ArrayList<String>());
									
									int width = 1;
									
									for (int i = 3; i < elements.length; i++) {
										if (elements[i].equals("/")) {
											recipe.add(new ArrayList<String>());
											y++;
											x = 0;
										} else {
											recipe.get(y).add(processItemStackPattern(elements[i], aliases));
											x++;
											width = Math.max(width, x);
										}
									}
									
									int height = recipe.size();
									output.append("recipes.removeShaped(").append(target).append(", [");
									
									boolean firstRow = true;
									for (ArrayList<String> row : recipe) {
										if (firstRow) {
											firstRow = false;
										} else {
											output.append(", ");
										}
										output.append("[");
										
										boolean first = true;
										for (String s : row) {
											if (first) {
												first = false;
											} else {
												output.append(", ");
											}
											output.append(s);
										}
										output.append("]");
									}
									output.append("]);").append(suffix).append("\r\n");
								}
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							
							break;
						}
						case REMOVESHAPELESS: {
							if (elements.length < 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: too few arguments for removeShapeless");
								break;
							}
							
							try {
								String target = processItemStackPattern(elements[1], aliases);
								if (elements.length == 2) {
									output.append("recipes.removeShapeless(").append(target).append(");").append(suffix).append("\r\n");
								} else if (!elements[2].equals("=")) {
									System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: = expected");
									break;
								} else if (elements.length < 4) {
									System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: recipe expected");
									break;
								} else {
									boolean wildcard = elements[elements.length - 1].equals("...");
									int limit = wildcard ? elements.length - 1 : elements.length;
									
									String[] recipe = new String[limit - 3];
									
									for (int i = 3; i < limit; i++) {
										recipe[i - 3] = processItemStackPattern(elements[i], aliases);
									}
									
									output.append("recipes.removeShapeless(").append(target).append(", [");
									
									boolean first = true;
									for (String s : recipe) {
										if (first) {
											first = false;
										} else {
											output.append(", ");
										}
										output.append(s);
									}
									output.append("]");
									
									if (wildcard) {
										output.append(", true");
									}
									output.append(");").append(suffix).append("\r\n");
								}
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						}
						case ADDSHAPED: {
							if (elements.length < 4) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: too few arguments for addShaped command");
								break;
							} else if (!elements[2].equals("=")) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: = expected");
								break;
							}
							
							ArrayList<ArrayList<String>> recipe = new ArrayList<ArrayList<String>>();
							
							try {
								String target = processItemStack(elements[1], aliases);
								
								int x = 0;
								int y = 0;
								recipe.add(new ArrayList<String>());
								
								int width = 1;
								
								for (int i = 3; i < elements.length; i++) {
									if (elements[i].equals("/")) {
										recipe.add(new ArrayList());
										y++;
										x = 0;
									} else if (elements[i].equals("*")) {
										recipe.get(y).add(null);
										x++;
										width = Math.max(width, x);
									} else {
										String stack = processOreStack(elements[i], aliases);
										
										recipe.get(y).add(stack);
										x++;
										width = Math.max(width, x);
									}
								}
								
								output.append("recipes.addShaped(").append(target).append(", [");
								
								boolean firstRow = true;
								for (ArrayList<String> row : recipe) {
									if (firstRow) {
										firstRow = false;
									} else {
										output.append(", ");
									}
									
									output.append("[");
									boolean first = true;
									for (String s : row) {
										if (first) {
											first = false;
										} else {
											output.append(", ");
										}
										output.append(s);
									}
									output.append("]");
								}
								output.append("]);").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						}
						case ADDSHAPELESS: {
							if (elements.length < 4) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: too few arguments for addShapeless command");
								break;
							} else if (!elements[2].equals("=")) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: = expected");
								break;
							}
							
							try {
								String target = processItemStack(elements[1], aliases);
								ArrayList<String> recipe = new ArrayList<String>();
								
								for (int i = 3; i < elements.length; i++) {
									String stack = processOreStack(elements[i], aliases);
									recipe.add(stack);
								}
								
								output.append("recipes.addShapeless(").append(target).append(", [");
								
								boolean first = true;
								for (String s : recipe) {
									if (first) {
										first = false;
									} else {
										output.append(", ");
									}
									output.append(s);
								}
								
								output.append("]);").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						}
						case REMOVEFURNACE: {
							if (elements.length < 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: item expected");
								break;
							}
							try {
								if (elements.length == 2) {
									String out = processItemStackPattern(elements[1], aliases);
									output.append("furnace.remove(").append(out).append(");").append(suffix).append("\r\n");
								} else if (!elements[2].equals("=")) {
									System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: = expected");
									break;
								} else {
									if (elements.length > 4) {
										System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: redundant arguments for removeFurnace command have been ignored");
									} else if (elements.length < 4) {
										System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing input parameter");
										break;
									}
									
									String out = processItemStackPattern(elements[1], aliases);
									String input = processItemStackPattern(elements[3], aliases);
									output.append("furnace.remove(").append(out).append(", ").append(input).append(");").append(suffix).append("\r\n");
								}
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						}
						case ADDFURNACE: {
							if (elements.length < 4) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: too few arguments for addFurnace command");
								break;
							} else if (!elements[2].equals("=")) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: = expected");
								break;
							}
							
							try {
								String target = processItemStack(elements[1], aliases);
								String input = processItemId(elements[3], aliases);
								float xp = 0.0f;
								if (elements.length > 4) {
									try {
										xp = Float.parseFloat(elements[4]);
									} catch (NumberFormatException ex) {
										System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: xp parameter must be a float value");
									}
									output.append("furnace.add(")
										  .append(target)
										  .append(", ")
										  .append(input)
										  .append(", ")
										  .append(elements[4])
										  .append(");")
										  .append(suffix)
										  .append("\r\n");
								} else {
									output.append("furnace.add(")
									      .append(target)
									      .append(", ")
									      .append(input)
									      .append(");")
									      .append(suffix)
									      .append("\r\n");
								}
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
							}
							break;
						}
						case OREDICTADD: {
							if (elements.length < 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing arguments for oreDictAdd command");
								break;
							} else if (elements.length > 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: too many arguments for oreDictAdd command, they have been ignored");
							}
							
							try {
								String id = processItemId(elements[1], aliases);
								output.append("oreDict.").append(elements[2]).append("add(").append(id).append(");").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
								break;
							}
							
							break;
						}
						case OREDICTREMOVE: {
							if (elements.length < 2) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing arguments for oreDictRemove command");
								break;
							} else if (elements.length > 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: too many arguments for the oreDictRemove command, they have been ignored");
							}
							
							try {
								String pattern = processItemStackPattern(elements[1], aliases);
								if (elements.length > 2) {
									String name = elements[2];
									output.append("oreDict.").append(name).append("remove(").append(pattern).append(");").append(suffix).append("\r\n");
								} else {
									output.append("oreDict.remove(").append(pattern).append(");").append(suffix).append("\r\n");
								}
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
								break;
							}
							
							break;
						}
						case SETFURNACEFUEL: {
							if (elements.length < 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing arguments for setFurnaceFuel command");
								break;
							} else if (elements.length > 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Warning: too many arguments for the setFurnaceFuel command, they have been ignored");
							}
							
							try {
								String pattern = processItemStackPattern(elements[1], aliases);
								int value = Integer.parseInt(elements[2]);
								output.append(pattern).append(".fuel = ").append(value).append(";").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
								break;
							} catch (NumberFormatException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: invalid fuel value");
								break;
							}
							break;
						}
						case SETNAME: {
							if (elements.length < 3) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing arguments for setName command");
								break;
							}
							
							try {
								String item = processItemId(elements[1], aliases);
								String name = elements.length == 3 ? elements[2] : Arrays2.join(elements, 2, elements.length, " ");
								
								output.append(item).append(".displayName = \"").append(name).append("\";").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
								break;
							}
							break;
						}
						case SETLOCALIZEDNAME: {
							if (elements.length < 4) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: missing arguments for setLocalizedName command");
								break;
							}
							
							try {
								String item = processItemId(elements[1], aliases);
								String lang = elements[2];
								String name = elements.length == 4 ? elements[3] : Arrays2.join(elements, 3, elements.length, " ");
								
								output.append(item).append(".setDisplayName(\"").append(lang).append("\", \"").append(name).append("\");").append(suffix).append("\r\n");
							} catch (TweakerExecuteException ex) {
								System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: " + ex.getMessage());
								break;
							}
							break;
						}
						default:
							System.out.println("[MineTweaker] [Line " + lineNumber + "] Error: command not yet implemented: " + elements[0]);
							break;
						}
					}
				}
				
				line = reader.readLine();
				lineNumber++;
			}
			
			reader.close();
			
			FileWriter writer = new FileWriter(newFile);
			writer.append(output.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("[MineTweaker] Could not load config file: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[MineTweaker] Could not process config file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static String processItemId(String id, HashSet<String> aliases) {
		if (Character.isDigit(id.charAt(0))) {
			int colon = id.indexOf(':');
			if (colon < 0) {
				try {
					int itemId = Integer.parseInt(id);
					return "<" + itemId + ">";
				} catch (NumberFormatException ex) {
					throw new TweakerExecuteException("Invalid item id: " + id);
				}
			} else {
				String before = id.substring(0, colon);
				String after = id.substring(colon + 1);
				
				int itemId = 0;
				int meta = 0;
				try {
					itemId = Integer.parseInt(before);
				} catch (NumberFormatException ex) {
					throw new TweakerExecuteException("Invalid item id: " + before);
				}
				try {
					meta = Integer.parseInt(after);
				} catch (NumberFormatException ex) {
					throw new TweakerExecuteException("Invalid item meta: " + after);
				}
				
				return "<" + itemId + ":" + meta + ">";
			}
		} else if (aliases.contains(id)) {
			return id;
		} else {
			throw new TweakerExecuteException("Invalid item: " + id);
		}
	}

	private static boolean isValidIdentifier(String value) {
		char[] chars = value.toCharArray();
		char start = chars[0];
		if (!((start >= 'A' && start <= 'Z') || (start >= 'a' && start <= 'z') || start == '_')) return false;
		for (int i = 1; i < chars.length; i++) {
			char c = chars[i];
			if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || start == '_')) return false;
		}
		return true;
	}
	

	private static String processItemStack(String id, HashSet<String> aliases) {
		int plus = id.indexOf('+');
		int amount = 1;
		if (plus >= 0) {
			try {
				amount = Integer.parseInt(id.substring(plus + 1));
			} catch (NumberFormatException ex) {
				throw new TweakerExecuteException("Invalid amount: " + id.substring(plus + 1));
			}
			id = id.substring(0, plus);
		}
		
		String iid = processItemId(id, aliases);
		if (amount > 1) iid += " * " + amount;
		return iid;
	}

	public static String processOreStack(String id, HashSet<String> aliases) {
		int plus = id.indexOf('+');
		int amount = 1;
		if (plus >= 0) {
			try {
				amount = Integer.parseInt(id.substring(plus + 1));
			} catch (NumberFormatException ex) {
				throw new TweakerExecuteException("Invalid amount: " + id.substring(plus + 1));
			}
			id = id.substring(0, plus);
		}
		
		String iid = processItemId(id, aliases);
		if (amount > 1) iid += " * " + amount;
		return iid;
	}
	
	private static String processItemStackPattern(String value, HashSet<String> aliases) {
		if (value.equals("-") || value.equals("*")) return "null";
		if (value.equals("?")) return "<*>";
		
		boolean like = false;
		if (value.startsWith("~")) {
			like = true;
			value = value.substring(1);
		}
		
		int amount = -1;
		int plus = value.indexOf('+');
		if (plus >= 0) {
			try {
				amount = Integer.parseInt(value.substring(plus + 1));
			} catch (NumberFormatException ex) {
				throw new TweakerExecuteException("Invalid amount after +");
			}
			value = value.substring(0, plus);
		}
		
		String result = processItemId(value, aliases);
		if (like) {
			result += ".any";
		}
		if (amount >= 0) {
			result += " * " + amount;
		}
		return result;
	}
}
