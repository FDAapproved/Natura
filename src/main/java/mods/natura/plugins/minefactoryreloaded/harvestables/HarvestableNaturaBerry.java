package mods.natura.plugins.minefactoryreloaded.harvestables;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class HarvestableNaturaBerry implements IFactoryHarvestable {
    private Block _sourceBlockId;
    private Item _berryItemId;

    public HarvestableNaturaBerry(Block sourceBlockId, Item berryItemId) {
        _sourceBlockId = sourceBlockId;
        _berryItemId = berryItemId;
    }

    @Override
    public Block getPlant() {
        return _sourceBlockId;
    }

    @Override
    public HarvestType getHarvestType() {
        return HarvestType.Column;
    }

    @Override
    public boolean breakBlock() {
        return false;
    }

    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) >= 12;
    }

    @Override
    public List<ItemStack> getDrops(
            World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
        ItemStack[] returnItems = {new ItemStack(_berryItemId, 1, world.getBlockMetadata(x, y, z) - 12)};
        return Arrays.asList(returnItems);
    }

    @Override
    public void preHarvest(World world, int x, int y, int z) {}

    @Override
    public void postHarvest(World world, int x, int y, int z) {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 4, 2);
    }
}
