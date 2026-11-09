package itamio.weathershow;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("weathershow")
public class WeatherShowMod {
    public WeatherShowMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("weathershow").requires(s -> s.hasPermission(0))
            .executes(ctx -> {
                var lvl = ctx.getSource().getLevel(); String w; if (lvl.isRaining()) w = lvl.isThundering() ? "Thunderstorm" : "Rain"; else w = "Clear"; ctx.getSource().sendSuccess(() -> Component.literal("Weather: " + w), false);
                return 1;
            }));
    }
}
