package net.silentchaos512.gems.api.tool.part;

import net.minecraft.item.ItemStack;

public interface IPartProperties {

  public String getName();

  public String getNamePrefix();

  public int getColor();

  public int getDurability();

  public float getMiningSpeed(); // Harvest speed

  public int getHarvestLevel();

  public float getMeleeDamage();

  public float getMagicDamage();

  public float getMeleeSpeed();

  public int getEnchantability();

  public float getProtection();

  public ItemStack getCraftingStack();

  public String getCraftingOreName();
}
