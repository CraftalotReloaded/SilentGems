package net.silentchaos512.gems.network.message;

import com.google.common.base.Predicate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.compat.BaublesCompat;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.network.Message;
import net.silentchaos512.lib.util.PlayerHelper;

public class MessageKeyReturnHome extends Message {

  public MessageKeyReturnHome() {

  }

  @Override
  public IMessage handleMessage(MessageContext ctx) {

    if (ctx.side != Side.SERVER)
      return null;

    Predicate<ItemStack> predicate = s -> s.getItem() == ModItems.returnHomeCharm;
    EntityPlayer player = ctx.getServerHandler().playerEntity;
    NonNullList<ItemStack> stacks = PlayerHelper.getNonEmptyStacks(player, predicate);
    if (Loader.isModLoaded(SilentGems.BAUBLES_MOD_ID))
      stacks.addAll(BaublesCompat.getBaubles(player, predicate));

    if (stacks.isEmpty())
      return null;

    ModItems.returnHomeCharm.tryTeleportPlayer(stacks.get(0), player);

    return null;
  }
}