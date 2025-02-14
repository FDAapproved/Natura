package mods.natura.blocks.nether;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import mods.natura.blocks.NBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class TaintedSoil extends NBlock {
    public TaintedSoil() {
        super(Material.ground, 2.2f, new String[] {"tainted_soil", "tainted_farmland_dry", "tainted_farmland_heated"});
        this.setStepSound(Block.soundTypeGravel);
        this.setResistance(25f);
    }

    @Override
    public boolean isFertile(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) == 2;
    }

    @Override
    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
        return this == target || target == Blocks.netherrack;
    }

    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
        if (plantType == EnumPlantType.Nether) return true;
        return super.canSustainPlant(world, x, y, z, direction, plant);
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return side == UP;
    }
}
