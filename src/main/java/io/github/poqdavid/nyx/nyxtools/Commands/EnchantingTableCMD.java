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

package io.github.poqdavid.nyx.nyxtools.Commands;

import io.github.poqdavid.nyx.nyxcore.Permissions.ToolsPermission;
import io.github.poqdavid.nyx.nyxcore.Utils.Invs;
import io.github.poqdavid.nyx.nyxcore.Utils.Tools;
import io.github.poqdavid.nyx.nyxtools.Utils.Containers.VirtualEnchantingTable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandPermissionException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class EnchantingTableCMD implements CommandExecutor {
    private final Invs inv;

    public EnchantingTableCMD(Invs inv) {
        this.inv = inv;
    }

    public static Text getDescription() {
        return Text.of("/enchantingtable, /et");
    }

    public static String[] getAlias() {
        return new String[]{"enchantingtable", "et"};
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            if (src.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE)) {
                final Integer maxPower = this.getEnchantingTablePower(Tools.getPlayer(src));
                final Integer et_lvl = args.<Integer>getOne("power").orElse(maxPower);
                VirtualEnchantingTable VET;
                if (et_lvl >= maxPower) {
                    VET = new VirtualEnchantingTable(Tools.getPlayerE(src), maxPower.floatValue());
                } else {
                    VET = new VirtualEnchantingTable(Tools.getPlayerE(src), et_lvl.floatValue());
                }
                return inv.Open(src, VET, "minecraft:enchanting_table", "Enchant");
            } else {
                throw new CommandPermissionException(Text.of("You don't have permission to use this command."));
            }
        } else {
            throw new CommandException(Text.of("You can't use this command if you are not a player!"));
        }

    }

    public Integer getEnchantingTablePower(Player player) {
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_15))
            return 15;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_14))
            return 14;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_13))
            return 13;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_12))
            return 12;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_11))
            return 11;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_10))
            return 10;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_9))
            return 9;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_8))
            return 8;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_7))
            return 7;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_6))
            return 6;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_5))
            return 5;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_4))
            return 4;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_3))
            return 3;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_2))
            return 2;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_1))
            return 1;
        if (player.hasPermission(ToolsPermission.COMMAND_ENCHANTINGTABLE_POWER_0))
            return 0;
        return 15;
    }
}
