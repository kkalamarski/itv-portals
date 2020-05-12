package net.itv.portals;

import net.fabricmc.api.ModInitializer;
import net.itv.portals.items.WarpScroll;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItvPortals implements ModInitializer {

	public static final Item WARP_SCROLL = new WarpScroll();

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("itv-portals", "warp_scroll"), WARP_SCROLL);
	}
}
