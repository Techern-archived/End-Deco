package com.techern.minecraft.enddeco.blocks;

import com.techern.minecraft.enddeco.EndDecoMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            .setCreativeTab(CreativeTabs.tabBlock)
            .setUnlocalizedName("end_stone_bricks")
            .setStepSound(Block.soundTypePiston); /*what*/


    /**
     * A {@link BlockStairs} defining end stone stairs
     *
     * @since 0.0.1
     */
    public static BlockStairs END_STONE_STAIRS = new BaseBlockStairs(Blocks.end_stone.getDefaultState(), "end_stone_stairs");

    /**
     * A {@link BlockStairs} defining end stone brick stairs
     *
     * @since 0.0.1
     */
    public static BlockStairs END_STONE_BRICK_STAIRS = new BaseBlockStairs(END_STONE_BRICKS.getDefaultState(), "end_stone_brick_stairs");


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

        if (EndDecoMod.CONFIGURATION.getBoolean("Stairs", "NEW_BLOCKS", true, "Enables the use of new stair blocks")) {
            GameRegistry.registerBlock(END_STONE_STAIRS, "end_stone_stairs");
            if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {
                GameRegistry.registerBlock(END_STONE_BRICK_STAIRS, "end_stone_brick_stairs");
            }
        }

        //Register other blocks later

        //Now let's do proxy shit

        if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {
            EndDecoMod.PROXY.registerItemModelMesher(Item.getItemFromBlock(END_STONE_BRICKS), 0, "end_stone_bricks", "inventory");
        }

        if (EndDecoMod.CONFIGURATION.getBoolean("Stairs", "NEW_BLOCKS", true, "Enables the use of new stair blocks")) {
            EndDecoMod.PROXY.registerItemModelMesher(Item.getItemFromBlock(END_STONE_STAIRS), 0, "end_stone_stairs", "inventory");

            if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {
                EndDecoMod.PROXY.registerItemModelMesher(Item.getItemFromBlock(END_STONE_BRICK_STAIRS), 0, "end_stone_brick_stairs", "inventory");
            }
        }


    }

    /**
     * Registers recipes for the {@link EndDecoMod}
     *
     * @since 0.0.1
     */
    public static void registerRecipes() {

        /**
         * A single end stone block, used for recipes
         *
         * @since 0.0.1
         */
        ItemStack singleEndStoneBlock = new ItemStack(Blocks.end_stone);

        if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {

            GameRegistry.addShapedRecipe(new ItemStack(END_STONE_BRICKS, 4), "XX", "XX", 'X', singleEndStoneBlock);

        }

        //Now we do stairs :)
        if (EndDecoMod.CONFIGURATION.getBoolean("Stairs", "NEW_BLOCKS", true, "Enables the use of new stair blocks")) {

            registerStairsRecipe(Blocks.end_stone, END_STONE_STAIRS);
            if (EndDecoMod.CONFIGURATION.getBoolean("End_Stone_Bricks", "NEW_BLOCKS", true, "Enables the use of end stone bricks")) {
                registerStairsRecipe(END_STONE_BRICKS, END_STONE_BRICK_STAIRS);
            }

        }

    }

    /**
     * Registers a {@link BlockStairs} recipe
     *
     * @param baseBlock  The base block to be consumed
     * @param stairBlock The stair block to be returned
     * @since 0.0.1
     */
    public static void registerStairsRecipe(Block baseBlock, Block stairBlock) {
        registerStairsRecipe(baseBlock, 0, stairBlock);
    }

    /**
     * Registers a {@link BlockStairs} recipe
     *
     * @param baseBlock         The base block to be consumed
     * @param baseBlockMetadata The required metadata value of the base block
     * @param stairBlock        The stair block to be returned
     * @since 0.0.1
     */
    public static void registerStairsRecipe(Block baseBlock, int baseBlockMetadata, Block stairBlock) {
        ItemStack input = new ItemStack(baseBlock, 1, baseBlockMetadata);
        ItemStack output = new ItemStack(stairBlock, 4, 0);

        GameRegistry.addShapedRecipe(output, "  I", " II", "III", 'I', input);
        GameRegistry.addShapedRecipe(output, "I  ", "II ", "III", 'I', input);
    }

    /**
     * Registers all dye variant recipes for a single input {@link Block}
     *
     * @param blockToConsume The {@link Block} to consume
     * @param blockToReturn  The {@link Block} to return
     * @since 0.0.1
     */
    public static void registerSingleDyeBlockRecipeCombination(Block blockToConsume, Block blockToReturn) {
        registerSingleDyeBlockRecipeCombination(blockToConsume, 0, blockToReturn);
    }

    /**
     * Registers all dye variant recipes for a single input {@link Block}
     *
     * @param blockToConsume      The {@link Block} to consume
     * @param consumptionMetadata The metadata of the block being consumed
     * @param blockToReturn       The {@link Block} to return
     * @since 0.0.1
     */
    public static void registerSingleDyeBlockRecipeCombination(Block blockToConsume, int consumptionMetadata, Block blockToReturn) {
        ItemStack dye;
        ItemStack water = new ItemStack(Items.water_bucket, 1);
        for (EnumDyeColor color : EnumDyeColor.values()) {
            dye = new ItemStack(Items.dye, 1, color.getDyeDamage());

            ItemStack input = new ItemStack(blockToConsume, 1, consumptionMetadata);
            ItemStack output = new ItemStack(blockToReturn, 8, color.getMetadata());

            GameRegistry.addShapedRecipe(output, "III", "IDI", "III", 'I', input, 'D', dye);
            GameRegistry.addShapedRecipe(new ItemStack(blockToConsume, 8, consumptionMetadata),
                    "OOO", "OWO", "OOO",
                    'W', water, 'O', new ItemStack(blockToReturn, 1, color.getMetadata()));
        }
    }


}
