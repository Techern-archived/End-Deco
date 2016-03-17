package com.techern.minecraft.enddeco.blocks;

import com.techern.minecraft.enddeco.EndDecoMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * A utility class that registers and creates each new {@link net.minecraft.block.Block} for the {@link com.techern.minecraft.enddeco.EndDecoMod}
 *
 * @since 0.0.1
 */
public class EndDecoBlocks {

    /**
     * A {@link Block} defining an end stone brick
     *
     * @since 0.0.1
     */
    public static Block END_STONE_BRICKS = new Block(Material.rock, MapColor.sandColor).setHardness(0.8f)
                                                                   .setResistance(4f)
                                                                   .setCreativeTab(CreativeTabs.tabDecorations)
                                                                   .setUnlocalizedName("end_stone_bricks")
                                                                   .setStepSound(Block.soundTypePiston); /*what*/

    /**
     * Registers the {@link Block}s added by the {@link EndDecoMod}
     *
     * @since 0.0.1
     */
    public static void registerBlocks() {

        //First, let's check and do end stone bricks

        if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {

            //Now we have to set the level required for mining
            END_STONE_BRICKS.setHarvestLevel("pickaxe", 0);
            GameRegistry.registerBlock(END_STONE_BRICKS, "end_stone_bricks");
        }

        //Register other blocks later

        //Now let's do proxy shit

        if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {
            EndDecoMod.PROXY.registerItemModelMesher(Item.getItemFromBlock(END_STONE_BRICKS), 0, "end_stone_bricks", "inventory");
        }


    }



}
