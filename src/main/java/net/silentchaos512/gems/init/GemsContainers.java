package net.silentchaos512.gems.init;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.block.altar.AltarContainer;
import net.silentchaos512.gems.block.altar.AltarScreen;
import net.silentchaos512.gems.block.supercharger.SuperchargerContainer;
import net.silentchaos512.gems.block.supercharger.SuperchargerScreen;
import net.silentchaos512.gems.block.tokenenchanter.TokenEnchanterContainer;
import net.silentchaos512.gems.block.tokenenchanter.TokenEnchanterScreen;
import net.silentchaos512.gems.block.urn.SoulUrnContainer;
import net.silentchaos512.gems.block.urn.SoulUrnScreen;
import net.silentchaos512.gems.item.container.GemBagContainer;
import net.silentchaos512.gems.item.container.GemContainerScreen;
import net.silentchaos512.gems.item.container.GlowroseBasketContainer;
import net.silentchaos512.utils.Lazy;

import java.util.Locale;

public enum GemsContainers {
    GEM_BAG(GemBagContainer::new),
    GLOWROSE_BASKET(GlowroseBasketContainer::new),
    SOUL_URN(SoulUrnContainer::new),
    SUPERCHARGER(SuperchargerContainer::new),
    TOKEN_ENCHANTER(TokenEnchanterContainer::new),
    TRANSMUTATION_ALTAR(AltarContainer::new);

    private final Lazy<ContainerType<?>> type;

    GemsContainers(ContainerType.IFactory<?> factory) {
        this.type = Lazy.of(() -> new ContainerType<>(factory));
    }

    public ContainerType<?> type() {
        return type.get();
    }

    public static void registerAll(RegistryEvent.Register<ContainerType<?>> event) {
        for (GemsContainers container : values()) {
            register(container.name().toLowerCase(Locale.ROOT), container.type());
        }
    }

    @SuppressWarnings("unchecked")
    @OnlyIn(Dist.CLIENT)
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.registerFactory((ContainerType<? extends GemBagContainer>) GEM_BAG.type(), GemContainerScreen::new);
        ScreenManager.registerFactory((ContainerType<? extends GlowroseBasketContainer>) GLOWROSE_BASKET.type(), GemContainerScreen::new);
        ScreenManager.registerFactory((ContainerType<? extends SoulUrnContainer>) SOUL_URN.type(), SoulUrnScreen::new);
        ScreenManager.registerFactory((ContainerType<? extends SuperchargerContainer>) SUPERCHARGER.type(), SuperchargerScreen::new);
        ScreenManager.registerFactory((ContainerType<? extends TokenEnchanterContainer>) TOKEN_ENCHANTER.type(), TokenEnchanterScreen::new);
        ScreenManager.registerFactory((ContainerType<? extends AltarContainer>) TRANSMUTATION_ALTAR.type(), AltarScreen::new);
    }

    private static void register(String name, ContainerType<?> type) {
        ResourceLocation id = SilentGems.getId(name);
        type.setRegistryName(id);
        ForgeRegistries.CONTAINERS.register(type);
    }
}
