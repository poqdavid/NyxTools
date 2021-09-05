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
import io.github.poqdavid.nyx.nyxtools.NyxTools;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandPermissionException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.net.MalformedURLException;
import java.net.URL;


public class HelpCMD implements CommandExecutor {
    private final NyxTools nyt;

    public HelpCMD(NyxTools nyt) {
        this.nyt = nyt;
    }

    public static String[] getAlias() {
        return new String[]{"help", "?"};
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src.hasPermission(ToolsPermission.COMMAND_HELP)) {
            PaginationList.Builder builder = PaginationList.builder();
            URL url1 = null;
            try {
                url1 = new URL("https://github.com/poqdavid/NyxTools/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Text h1 = Text.builder("Author: ").color(TextColors.BLUE).style(TextStyles.BOLD).build();
            Text h2 = Text.builder("POQDavid").color(TextColors.GRAY).style(TextStyles.BOLD).onHover(TextActions.showText(Text.of(url1.toString()))).onClick(TextActions.openUrl(url1)).build();

            builder.title(Text.of("§9Nyx§5Tools §7- §6V" + nyt.getVersion()))
                    .header(Text.of(h1, h2))
                    .contents(
                            Text.of(TextColors.BLUE, TextStyles.ITALIC, ""),
                            Text.of(TextColors.GREEN, TextStyles.BOLD, "Commands"),
                            Text.of("§6- /§7anvil§b, §6/§7av"),
                            Text.of("§6- /§7enchantingtable§b, §6/§7et §6[§7<power>§6]"),
                            Text.of("§6- /§7enderchest§b, §6/§7ec"),
                            Text.of("§6- /§7workbench§b, §6/§7wb")
                    )
                    .padding(Text.of("="))
                    .sendTo(src);
        } else {
            throw new CommandPermissionException(Text.of("You don't have permission to use this command."));
        }

        return CommandResult.success();
    }

}


