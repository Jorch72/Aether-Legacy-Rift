package com.legacy.aether.blocks.natural.ore;

import java.util.Random;

import com.legacy.aether.item.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockAmbrosiumOre extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAmbrosiumOre()
	{
		super(Block.Builder.create(Material.ROCK).hardnessAndResistance(3.0F, 5.0F).soundType(SoundType.STONE));

		this.setDefaultState(this.getDefaultState().withProperty(DOUBLE_DROP, true));
	}

	@Override
	public IItemProvider getItemProvider(IBlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return ItemsAether.ambrosium_shard;
	}

	@Override
	public void addPropertiesToBuilder(StateContainer.Builder<Block, IBlockState> propertyBuilderIn)
	{
		propertyBuilderIn.addProperties(DOUBLE_DROP);
	}

	@Override
	public void spawnItems(IBlockState stateIn, World worldIn, BlockPos posIn, float chanceIn, int fortuneIn)
	{
		super.spawnItems(stateIn, worldIn, posIn, chanceIn, fortuneIn);

		super.dropXpOnBlockBreak(worldIn, posIn, MathHelper.getInt(new Random(), 0, 2));
	}

	@Override
    public int getItemsToDropCount(IBlockState stateIn, int fortuneIn, World worldIn, BlockPos posIn, Random randomIn)
    {
        if (fortuneIn > 0)
        {
            int i = randomIn.nextInt(fortuneIn + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.getItemsToDropCount(stateIn, randomIn) * (i + 1);
        }
        else
        {
            return this.getItemsToDropCount(stateIn, randomIn);
        }
    }

	@Override
	public int getItemsToDropCount(IBlockState stateIn, Random randIn)
	{
		return stateIn.getValue(DOUBLE_DROP).booleanValue() ? 2 : 1;
	}

}