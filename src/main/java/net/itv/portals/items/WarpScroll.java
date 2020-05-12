package net.itv.portals.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WarpScroll extends Item {

    public WarpScroll() {
        super(new Item.Settings().group(ItemGroup.TRANSPORTATION));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);

        BlockPos spawn = player.getSpawnPosition();

        player.setPos(spawn.getX(), spawn.getY(), spawn.getZ());
        player.positAfterTeleport(spawn.getX(), spawn.getY(), spawn.getZ());

        ItemStack item = player.getStackInHand(hand);
        item.decrement(1);

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, item);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.itv-portals.warp_scroll.tooltip"));
    }

}