package exnihiloomnia.blocks.barrels.states.compost.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihiloomnia.blocks.barrels.architecture.BarrelLogic;
import exnihiloomnia.blocks.barrels.states.BarrelStates;
import exnihiloomnia.blocks.barrels.states.compost.BarrelStateGrass;
import exnihiloomnia.blocks.barrels.tileentity.TileEntityBarrel;

public class GrassStateTrigger extends BarrelLogic{
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getTimerStatus() == -1.0d)
		{
			if (((BarrelStateGrass)BarrelStates.GRASS).isIngredient(item))
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getTimerStatus() == -1.0d)
		{
			if (((BarrelStateGrass)BarrelStates.GRASS).isIngredient(item))
				barrel.setState(BarrelStates.GRASS);
		}
		
		return false;
	}
}
