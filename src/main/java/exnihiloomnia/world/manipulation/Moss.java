package exnihiloomnia.world.manipulation;

import exnihiloomnia.util.helpers.PositionHelper;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Moss {
	public static final int DEFAULT_GROWTH_SPEED = 8;
	private static int growth = 0;
	private static boolean rain_reactive;
	
	private static BlockPos pos = null;
	private static IBlockState state = null;
	
	public static int getGrowth() {
		return growth;
	}

	public static void setGrowth(int growth) {
		Moss.growth = growth;
	}
	
	public static boolean getSpreadsWhileRaining() {
		return rain_reactive;
	}

	public static void setSpreadsWhileRaining(boolean spreads) {
		Moss.rain_reactive = spreads;
	}

	public static void grow(World world, Chunk chunk)
	{		
		for (int i = 0; i < growth; i++)
		{
			pos = PositionHelper.getRandomPositionInChunk(world, chunk);
			state = world.getBlockState(pos);

			if (isValidCobblestone(state) && (PositionHelper.hasNearbyWaterSource(world, pos) || (getSpreadsWhileRaining() && PositionHelper.canRainReach(world, pos))))
			{
				world.setBlockState(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState());
			}
			else if (isValidStoneBrick(state) && (PositionHelper.hasNearbyWaterSource(world, pos) ||  (getSpreadsWhileRaining() && PositionHelper.canRainReach(world, pos))))
			{
				world.setBlockState(pos, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
			}
			else if (isValidCobbleWall(state) && (PositionHelper.hasNearbyWaterSource(world, pos) ||  (getSpreadsWhileRaining() && PositionHelper.canRainReach(world, pos))))
			{
				world.setBlockState(pos, Blocks.COBBLESTONE_WALL.getDefaultState().withProperty(BlockWall.VARIANT, BlockWall.EnumType.MOSSY));
			}
		}
	}
	
	private static boolean isValidCobblestone(IBlockState state)
	{
		return state.getBlock() == Blocks.COBBLESTONE;
	}
	
	private static boolean isValidStoneBrick(IBlockState state)
	{
		return state.getBlock() == Blocks.STONEBRICK
				&& (state.getValue(BlockStoneBrick.VARIANT) == BlockStoneBrick.EnumType.DEFAULT
				|| state.getValue(BlockStoneBrick.VARIANT) == BlockStoneBrick.EnumType.CRACKED);
	}
	
	private static boolean isValidCobbleWall(IBlockState state)
	{
		return state.getBlock() == Blocks.COBBLESTONE_WALL
				&& (state.getValue(BlockWall.VARIANT) == BlockWall.EnumType.NORMAL);
	}
}
