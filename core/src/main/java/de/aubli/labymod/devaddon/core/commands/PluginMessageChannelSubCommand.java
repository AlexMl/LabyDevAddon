package de.aubli.labymod.devaddon.core.commands;

import de.aubli.labymod.devaddon.core.DevAddon;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.resources.ResourceLocation;

public class PluginMessageChannelSubCommand extends SubCommand {

  private final DevAddon addon;

  PluginMessageChannelSubCommand(DevAddon addon) {
    super("pm", "binpm");

    this.addon = addon;
  }

  @Override
  public boolean execute(String s, String[] strings) {
    // /dev pm <channel> <data>"

    addon.logger().info("s: " + s + ", args: " + Arrays.toString(strings));

    if (strings.length < 2) {
      displayMessage("Syntax: /dev <pm|binpm> <channel> <data>");
      return true;
    }
    String channel = strings[0];

    byte[] data;
    if (s.equalsIgnoreCase("pm")) {
      data = Arrays.stream(strings).skip(1).collect(Collectors.joining(" "))
          .getBytes(StandardCharsets.UTF_8);
    } else if (s.equalsIgnoreCase("binpm")) {
      data = new byte[strings.length - 1];

      List<Byte> collect = Arrays.stream(strings)
          .skip(1)
          .map(Byte::parseByte)
          .toList();

      for (int i = 0; i < collect.size(); i++) {
        data[i] = collect.get(i);
      }
      displayMessage("Sending binary pm " + Arrays.toString(data));
    } else {
      displayMessage("Syntax: /dev <pm|binpm> <channel> <data>");
      return true;
    }

    Laby.references().serverController().sendPayload(ResourceLocation.parse(channel), data);
    displayMessage("pm send");
    return true;
  }
}
