package com.techern.minecraft.enddeco;

import com.techern.minecraft.enddeco.blocks.EndDecoBlocks;
import com.techern.minecraft.enddeco.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A {@link net.minecraftforge.fml.common.Mod} for decorative (and functional) blocks in the end
 *
 * @since 0.0.1
 */
@Mod(modid = "end_deco", version = EndDecoMod.VERSION)
public class EndDecoMod {

    /**
     * The version {@link String}
     *
     * @since 0.0.1
     */
    public static final String VERSION = "0.0.1-dev";

    /**
     * The {@link Logger} for this mod
     *
     * Gets replaced in {@link EndDecoMod#handlePreInitEvent(FMLPreInitializationEvent)}
     *
     * @since 0.0.1
     */
    public static Logger LOGGER = LogManager.getLogger(EndDecoMod.class);

    /**
     * A {@link SidedProxy} of either {@link CommonProxy} or {@link com.techern.minecraft.enddeco.proxy.ClientProxy}
     *
     * @since 0.0.1
     */
    @SidedProxy(clientSide = "com.techern.minecraft.enddeco.proxy.ClientProxy",
            serverSide = "com.techern.minecraft.enddeco.proxy.CommonProxy")
    public static CommonProxy PROXY;

    /**
     * The {@link Configuration} (backed by a {@link java.io.File} used by {@link EndDecoMod}
     *
     * @since 0.0.1
     */
    public static Configuration CONFIGURATION;

    /**
     * Handles the {@link FMLPreInitializationEvent}
     *
     * @param event The {@link FMLPreInitializationEvent}
     * @since 0.0.1
     */
    @Mod.EventHandler
    public void handlePreInitEvent(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        LOGGER.debug("Attempting to load configuration file...");

        CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());

        LOGGER.info("Loaded configuration file for End Deco");
    }

    /**
     * Handles the {@link FMLInitializationEvent}
     *
     * @param event The {@link FMLInitializationEvent}
     * @since 0.0.1
     */
    @Mod.EventHandler
    public void handleInitEvent(FMLInitializationEvent event) {
        EndDecoBlocks.registerBlocks();
    }

    /**
     * Handles the {@link FMLPostInitializationEvent}
     *
     * @param event The {@link FMLPostInitializationEvent}
     * @since 0.0.1
     */
    @Mod.EventHandler
    public void handlePostInitEvent(FMLPostInitializationEvent event) {
        EndDecoBlocks.registerRecipes();

        if (CONFIGURATION.hasChanged()) {
            LOGGER.info("End Deco found extra / changed configuration, and is now saving");
            CONFIGURATION.save();
        }
    }
}
