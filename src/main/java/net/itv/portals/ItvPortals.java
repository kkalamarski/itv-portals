package net.itv.portals;

import express.Express;
import net.fabricmc.api.ModInitializer;
import net.itv.portals.items.WarpScroll;
import net.minecraft.command.arguments.NbtPathArgumentType.NbtPath;
import net.minecraft.datafixer.NbtOps;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ItvPortals implements ModInitializer {

	public static final Item WARP_SCROLL = new WarpScroll();

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("itv-portals", "warp_scroll"), WARP_SCROLL);

		Express app = new Express();

		app.get("/", (req, res) -> {
			res.send("Hello World");
		}).listen();
	}
}
