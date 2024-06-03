package de.aubli.labymod.devaddon.core.commands;

import de.aubli.labymod.devaddon.core.DevAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.resources.ResourceLocation;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PluginMessageChannelSubCommand extends SubCommand {

  private final DevAddon addon;

  PluginMessageChannelSubCommand(DevAddon addon) {
    super("pm");

    this.addon = addon;
  }

  @Override
  public boolean execute(String s, String[] strings) {
    // /dev pm <channel> <data>"

    addon.logger().info("s: " + s + ", args: " + Arrays.toString(strings));

    if (strings.length < 2) {
      displayMessage("Syntax: /dev pm <channel> <data>");
      return true;
    }
    String channel = strings[0];
    byte[] data = Arrays.stream(strings).skip(1).collect(Collectors.joining(" ")).getBytes(StandardCharsets.UTF_8);

    Laby.references().serverController().sendPayload(ResourceLocation.parse(channel), data);
    displayMessage("pm send");
    return true;
  }
}
