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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class VirtualAnvil extends ContainerRepair {

    private final World world;

    public VirtualAnvil(EntityPlayerMP entityHuman) {
        this(entityHuman, entityHuman.getPosition());
    }

    public VirtualAnvil(EntityPlayer entityHuman, BlockPos blockPosIn) {
        super(entityHuman.inventory, entityHuman.world, blockPosIn, entityHuman);
        this.world = entityHuman.world;
    }

    public VirtualAnvil(EntityPlayerMP entityHuman, World wd, int x, int y, int z) {
        super(new InventoryPlayer(entityHuman), wd, new BlockPos(x, y, z), entityHuman);
        this.world = wd;
    }

    public VirtualAnvil(EntityPlayerMP entityHuman, BlockPos blockPosIn) {
        super(new InventoryPlayer(entityHuman), entityHuman.getEntityWorld(), blockPosIn, entityHuman);
        this.world = entityHuman.getEntityWorld();
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
