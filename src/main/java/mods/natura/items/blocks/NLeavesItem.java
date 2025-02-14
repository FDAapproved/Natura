package mods.natura.items.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import mantle.blocks.abstracts.MultiItemBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class NLeavesItem extends MultiItemBlock {
    public static final String blockType[] = {
        "redwood", "eucalyptus", "bush", "", "", "", "", "", "", "", "", "", "", "", "", ""
    };

    public NLeavesItem(Block i) {
        super(i, "block", "NLeaves", blockType);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int md) {
        return md | 4;
    }

    /* @Override
    public String getUnlocalizedName (ItemStack itemstack)
    {
        return (new StringBuilder()).append(blockType[itemstack.getItemDamage()]).append("NLeaves").toString();
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        switch (stack.getItemDamage()) {
            case 0:
                list.add(StatCollector.translateToLocal("tooltip.tree4"));
                break;
            case 1:
                list.add(StatCollector.translateToLocal("tooltip.tree1"));
                break;
            case 2:
                list.add(StatCollector.translateToLocal("tooltip.tree6"));
                break;
        }
    }
}
