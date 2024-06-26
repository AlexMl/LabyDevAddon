package de.aubli.labymod.devaddon.core;

import de.aubli.labymod.devaddon.core.commands.DevCommand;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class DevAddon extends LabyAddon<ExampleConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(new DevCommand(this));

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<ExampleConfiguration> configurationClass() {
    return ExampleConfiguration.class;
  }
}
