package de.aubli.labymod.devaddon.core.commands;

import de.aubli.labymod.devaddon.core.DevAddon;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public class DevCommand extends Command {

  private final DevAddon addon;

  public DevCommand(DevAddon addon) {
    super("dev", "aublidev");

    this.addon = addon;

    withSubCommand(new PluginMessageChannelSubCommand(addon));
  }

  @Override
  public boolean execute(String s, String[] strings) {
    this.displayMessage(Component.text("Use /dev pm <channel> <data>", NamedTextColor.AQUA));
    return true;
  }
}
