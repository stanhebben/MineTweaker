package stanhebben.minetweaker.rewriter;

// <item>:
//    <integer> = item without meta tag; or for pattern matches any item with that id ignoring meta tag
//    <integer>:<integer> = item with meta
//    <identifier> = previously defined alias
// <itemStack>:
//	  <item> = single item
//    <item>+<integer> = multiple items
// <xp>:
//    xx% = in percentages
//    xx.yy = float number

public enum TweakCommand {
	VERSION, // version 1 -- required as first command in the file
	DEBUG, // debug -- enables debug output
	ALIAS, // alias <name> <item> -- give a name to an item id, or creates a second alias for an already known item
	OREDICT, // oreDict <name> -- defines an item as ore dictionary item
	REMOVE, // remove <item> -- removes all supported recipes for that item
	REMOVESHAPED, // removeShaped (<itemStack> | '?') [= (<item> | '-' | '?')+ ('/' (<item> | '-' | '?'))*] -- removes a shaped recipe with that pattern
	REMOVESHAPELESS, // removeShapeless (<itemStack> | '?') [= (<item> | '-') [...]]
	ADDSHAPED, // addShaped <itemStack> = <item>+ ('/' <item>)*
	ADDSHAPELESS, // addShapeless <itemStack> = <item>+
	REMOVEFURNACE, // removeFurnace (<itemStack> | '?') [= (<item> | '?')]
	ADDFURNACE, // addFurnace <itemStack> = <item> <xp>
	OREDICTADD, // oreDictAdd <item> <orename>
	OREDICTREMOVE, // oreDictRemove <item> [<orename>]
	SETFURNACEFUEL, // setFurnaceFuel <item> <int>
	SETNAME, // setName <item> <name>
	SETLOCALIZEDNAME, // setLocalizedName <item> <langcode> <name>
}
