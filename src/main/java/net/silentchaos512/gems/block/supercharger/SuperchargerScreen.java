/*
 * Silent's Gems -- SuperchargerScreen
 * Copyright (C) 2018 SilentChaos512
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 3
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.silentchaos512.gems.block.supercharger;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.api.chaos.ChaosEmissionRate;

public class SuperchargerScreen extends ContainerScreen<SuperchargerContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SilentGems.MOD_ID, "textures/gui/altar.png");

    public SuperchargerScreen(SuperchargerContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int color = -1;

        // Structure tier
        int tier = container.tileEntity.getStructureLevel();
        String textTier = I18n.format("block.silentgems.supercharger.tier", String.valueOf(tier));
        font.drawStringWithShadow(textTier, 5, 5, color);

        // Chaos generated
        int chaosGenerated = container.tileEntity.getChaosGenerated();
        ChaosEmissionRate emissionRate = ChaosEmissionRate.fromAmount(chaosGenerated);
        String text = emissionRate.getEmissionText(chaosGenerated).getFormattedText();
        font.drawStringWithShadow(text, 5, 15, color);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) return;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(TEXTURE);
        int xPos = (this.width - this.xSize) / 2;
        int yPos = (this.height - this.ySize) / 2;
        blit(xPos, yPos, 0, 0, this.xSize, this.ySize);

        // Progress arrow
        int progress = container.tileEntity.getProgress();
        int cost = container.tileEntity.getProcessTime();
        int length = cost > 0 && progress > 0 && progress < cost ? progress * 24 / cost : 0;
        blit(xPos + 79, yPos + 34, 176, 14, length + 1, 16);
    }
}
