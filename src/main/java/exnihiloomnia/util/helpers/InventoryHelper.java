package exnihiloomnia.util.helpers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

import java.util.List;

public class InventoryHelper {
	public static ItemStack getContainer(ItemStack full)
	{
		ItemStack empty;
		
		if (full.getItem().hasContainerItem(full)) 
		{
			empty = full.getItem().getContainerItem(full);
		} 
		else 
		{
			empty = FluidContainerRegistry.drainFluidContainer(full);
		}
		
		return empty;
	}
	
	public static void giveItemStackToPlayer(EntityPlayer player, ItemStack item)
	{
		if(!player.worldObj.isRemote)
		{
			EntityItem entity = new EntityItem(player.worldObj, player.posX + 0.5d, player.posY + 0.5d, player.posZ + 0.5d, item);
			player.worldObj.spawnEntityInWorld(entity);
		}	
	}
	
	public static void consumeItem(EntityPlayer player, ItemStack item)
	{
		if (player != null && !player.capabilities.isCreativeMode)
		{
            item.stackSize--;
		}
	}
}
