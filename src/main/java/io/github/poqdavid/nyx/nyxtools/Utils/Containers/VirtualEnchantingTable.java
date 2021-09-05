/*
 *     This file is part of NyxTools.
 *
 *     NyxTools is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     NyxTools is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with NyxTools.  If not, see <https://www.gnu.org/licenses/>.
 *
 *     Copyright (c) POQDavid <https://github.com/poqdavid/NyxTools>
 *     Copyright (c) contributors
 */

package io.github.poqdavid.nyx.nyxtools.Utils.Containers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.api.entity.living.player.Player;

import java.util.List;
import java.util.Random;

public final class VirtualEnchantingTable extends ContainerEnchantment {
    private final Random rand;
    private final World worldPointer;
    private final float power;

    public VirtualEnchantingTable(EntityPlayerMP entityHuman, float power) {
        super(new InventoryPlayer(entityHuman), (World) ((Player) entityHuman).getWorld(), new BlockPos(((Player) entityHuman).getLocation().getBlockX(), ((Player) entityHuman).getLocation().getBlockY(), ((Player) entityHuman).getLocation().getBlockZ()));
        this.worldPointer = (World) ((Player) entityHuman).getWorld();
        this.rand = new Random();
        this.power = power;
    }

    public VirtualEnchantingTable(EntityPlayerMP entityHuman, float power, World wd, int x, int y, int z) {
        super(new InventoryPlayer(entityHuman), wd, new BlockPos(x, y, z));
        this.worldPointer = wd;
        this.rand = new Random();
        this.power = power;
    }

    private List<EnchantmentData> getEnchantmentList(net.minecraft.item.ItemStack stack, int p_178148_2_, int p_178148_3_) {
        this.rand.setSeed(this.xpSeed + p_178148_2_);
        List<EnchantmentData> list = EnchantmentHelper.buildEnchantmentList(this.rand, stack, p_178148_3_, false);

        if (stack.getItem() == Items.BOOK && list.size() > 1) {
            list.remove(this.rand.nextInt(list.size()));
        }

        return list;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        if (inventoryIn == this.tableInventory) {
            net.minecraft.item.ItemStack itemstack = inventoryIn.getStackInSlot(0);

            if (itemstack != null && itemstack.isItemEnchantable()) {
                if (!this.worldPointer.isRemote) {
                    int l = 0;

                    this.rand.setSeed(this.xpSeed);

                    for (int i1 = 0; i1 < 3; ++i1) {
                        this.enchantLevels[i1] = EnchantmentHelper.calcItemStackEnchantability(this.rand, i1, (int) this.power, itemstack);
                        this.enchantClue[i1] = -1;
                        this.worldClue[i1] = -1;

                        if (this.enchantLevels[i1] < i1 + 1) {
                            this.enchantLevels[i1] = 0;
                        }
                    }

                    for (int j1 = 0; j1 < 3; ++j1) {
                        if (this.enchantLevels[j1] > 0) {
                            List<EnchantmentData> list = this.getEnchantmentList(itemstack, j1, this.enchantLevels[j1]);

                            if (list != null && !list.isEmpty()) {
                                EnchantmentData enchantmentdata = list.get(this.rand.nextInt(list.size()));
                                this.enchantClue[j1] = Enchantment.getEnchantmentID(enchantmentdata.enchantment);
                                this.worldClue[j1] = enchantmentdata.enchantmentLevel;
                            }
                        }
                    }

                    this.detectAndSendChanges();
                }
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.enchantLevels[i] = 0;
                    this.enchantClue[i] = -1;
                    this.worldClue[i] = -1;
                }
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}