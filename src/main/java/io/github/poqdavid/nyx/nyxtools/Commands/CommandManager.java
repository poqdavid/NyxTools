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

import io.github.poqdavid.nyx.nyxcore.Utils.Invs;
import io.github.poqdavid.nyx.nyxtools.NyxTools;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandManager {
    public static CommandSpec enderchestCmd;
    public static CommandSpec anvilCmd;
    public static CommandSpec workbenchCmd;
    public static CommandSpec enchantingtableCmd;
    public static CommandSpec nytCommand;
    public static CommandSpec helpCmd;
    private final Game game;
    private final NyxTools nyt;
    private final Invs inv;

    public CommandManager(Game game, NyxTools nyt) {
        this.game = game;
        this.nyt = nyt;
        this.inv = new Invs(nyt);
        registerCommands();
    }

    public void registerCommands() {
        helpCmd = CommandSpec.builder()
                .description(Text.of("/nyt help"))
                .executor(new HelpCMD(this.nyt))
                .build();

        enderchestCmd = CommandSpec.builder()
                .description(EnderChestCMD.getDescription())
                .executor(new EnderChestCMD(this.inv))
                .child(helpCmd, HelpCMD.getAlias())
                .build();

        anvilCmd = CommandSpec.builder()
                .description(AnvilCMD.getDescription())
                .executor(new AnvilCMD(this.inv))
                .child(helpCmd, HelpCMD.getAlias())
                .build();

        workbenchCmd = CommandSpec.builder()
                .description(WorkbenchCMD.getDescription())
                .executor(new WorkbenchCMD(this.inv))
                .child(helpCmd, HelpCMD.getAlias())
                .build();

        enchantingtableCmd = CommandSpec.builder()
                .description(EnchantingTableCMD.getDescription())
                .executor(new EnchantingTableCMD(this.inv))
                .child(helpCmd, HelpCMD.getAlias())
                .arguments(GenericArguments.optional(GenericArguments.integer(Text.of("power"))))
                .build();

        nytCommand = CommandSpec.builder()
                .description(MainCMD.getDescription())
                .executor(new MainCMD())
                .child(helpCmd, HelpCMD.getAlias())
                .build();

        //--Main---
        game.getCommandManager().register(nyt, nytCommand, MainCMD.getAlias());

        //--Tools--
        if (nyt.getSettings().getCommands().isEnderchestEnabled()) {
            game.getCommandManager().register(nyt, enderchestCmd, EnderChestCMD.getAlias());
        }
        if (nyt.getSettings().getCommands().isAnvilEnabled()) {
            game.getCommandManager().register(nyt, anvilCmd, AnvilCMD.getAlias());
        }
        if (nyt.getSettings().getCommands().isWorkbenchEnabled()) {
            game.getCommandManager().register(nyt, workbenchCmd, WorkbenchCMD.getAlias());
        }
        if (nyt.getSettings().getCommands().isEnchantingTableEnabled()) {
            game.getCommandManager().register(nyt, enchantingtableCmd, EnchantingTableCMD.getAlias());
        }

    }

}
