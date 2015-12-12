package net.silentchaos512.gems.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.silentchaos512.gems.block.ModBlocks;
import net.silentchaos512.gems.configuration.Config;
import net.silentchaos512.gems.core.registry.IAddRecipe;
import net.silentchaos512.gems.core.registry.SRegistry;
import net.silentchaos512.gems.core.util.LocalizationHelper;
import net.silentchaos512.gems.core.util.RecipeHelper;
import net.silentchaos512.gems.lib.Names;
import net.silentchaos512.gems.lib.Strings;

public class FluffyPlantSeeds extends ItemSeeds implements IAddRecipe {

  public FluffyPlantSeeds() {

    super(SRegistry.getBlock(Names.FLUFFY_PLANT), Blocks.farmland);
    setUnlocalizedName(Names.FLUFFY_SEED);
    MinecraftForge.addGrassSeed(new ItemStack(this), Config.FLUFFY_PUFF_SEED_WEIGHT);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {

    String s = LocalizationHelper.getItemDescription(Names.FLUFFY_SEED, 0);
    list.add(EnumChatFormatting.GREEN + s);
  }

  @Override
  public void addOreDict() {

    OreDictionary.registerOre("materialCotton", this);
  }

  @Override
  public void addRecipes() {

    // String
    GameRegistry.addShapedRecipe(new ItemStack(Items.string), "ff", 'f', this);
    // Wool
    GameRegistry.addShapedRecipe(new ItemStack(Blocks.wool), "fff", "f f", "fff", 'f', this);
    // Feather
    GameRegistry.addShapedRecipe(new ItemStack(Items.feather), " ff", "ff ", "f  ", 'f', this);
    // Fluffy Fabric
    ItemStack puff = new ItemStack(this);
    ItemStack fabric = CraftingMaterial.getStack(Names.FLUFFY_FABRIC);
    RecipeHelper.addCompressionRecipe(puff, fabric, 4);
    // Fluffy block
    ItemStack block = new ItemStack(ModBlocks.fluffyBlock);
    RecipeHelper.addCompressionRecipe(fabric, block, 4);
    // Book
    ItemStack book = new ItemStack(Items.book);
    ItemStack paper = new ItemStack(Items.paper);
    GameRegistry.addShapelessRecipe(book, paper, paper, paper, fabric);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {

    return LocalizationHelper.ITEM_PREFIX + Names.FLUFFY_SEED;
  }

  @Override
  public void registerIcons(IIconRegister reg) {

    itemIcon = reg.registerIcon(Strings.RESOURCE_PREFIX + Names.FLUFFY_SEED);
  }
}
