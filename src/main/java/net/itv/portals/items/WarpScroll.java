package net.itv.portals.items;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.realmsclient.dto.PlayerInfo;

import org.apache.logging.log4j.core.jmx.Server;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.fabricmc.loader.util.sat4j.minisat.core.DataStructureFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.data.dev.NbtProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.TagReaders;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.NbtText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

public class WarpScroll extends Item {

    public WarpScroll() {
        super(new Item.Settings().group(ItemGroup.TRANSPORTATION));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getStackInHand(hand);

        item.decrement(1);

        if (world.isClient()) {
            player.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
        } else {
            BlockPos spawn = ((ServerPlayerEntity) player).getSpawnPointPosition();

            player.setPos(spawn.getX(), spawn.getY(), spawn.getZ());
            player.positAfterTeleport(spawn.getX(), spawn.getY(), spawn.getZ());
        }

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, item);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.itv-portals.warp_scroll.tooltip"));
    }

}