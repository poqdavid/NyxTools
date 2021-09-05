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
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;

public class VirtualWorkbench extends ContainerWorkbench {

    public VirtualWorkbench(EntityPlayer entityHuman) {
        super(entityHuman.inventory, entityHuman.world, entityHuman.getPosition());
    }

    public VirtualWorkbench(EntityPlayer entityHuman, BlockPos blockPosIn) {
        super(entityHuman.inventory, entityHuman.world, blockPosIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}