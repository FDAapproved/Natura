package mods.natura.worldgen;

import java.util.Random;
import mods.natura.common.NContent;
import mods.natura.common.PHNatura;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BerryBushGen extends WorldGenerator {

    private int metadata;
    private int spawnHeight;

    public BerryBushGen(int meta, int range) {
        metadata = meta;
        spawnHeight = range;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int height = findGround(world, x, y, z);
        if (height != -1) {
            int type = random.nextInt(10);
            if (type == 9) generateLargeNode(world, random, x, height, z);
            else if (type >= 7) generateShrub(world, random, x, height, z);
            else if (type >= 3) generateSmallNode(world, random, x, height, z);
            else generateTinyNode(world, random, x, height, z);
        }
        return true;
    }

    int findGround(World world, int x, int y, int z) {
        int returnHeight = -1;
        Block blockID = world.getBlock(x, y - 1, z);
        if (!world.getBlock(x, y, z).isOpaqueCube() && (blockID == Blocks.dirt || blockID == Blocks.grass)) {
            return y;
        }
        int height = spawnHeight;
        do {
            if (height < PHNatura.seaLevel) {
                break;
            }
            Block j1 = world.getBlock(x, height, z);
            if (j1 == Blocks.dirt || j1 == Blocks.grass) {
                if (!world.getBlock(x, height + 1, z).isOpaqueCube()) {
                    returnHeight = height + 1;
                }
                break;
            }
            height--;
        } while (height > 0);
        return returnHeight;
    }

    public void generateLargeNode(World world, Random random, int x, int y, int z) {
        for (int iterX = x - 2; iterX <= x + 2; iterX++) {
            for (int iterZ = z - 1; iterZ <= z + 1; iterZ++) {
                for (int iterY = y - 1; iterY <= y; iterY++) {
                    generateBerryBlock(world, iterX, iterY, iterZ, random);
                }
            }
        }

        for (int iterX = x - 1; iterX <= x + 1; iterX++) {
            for (int iterZ = z - 2; iterZ <= z - 2; iterZ++) {
                for (int iterY = y - 1; iterY <= y; iterY++) {
                    generateBerryBlock(world, iterX, iterY, iterZ, random);
                }
            }
        }

        for (int iterX = x - 1; iterX <= x + 1; iterX++) {
            for (int iterZ = z + 2; iterZ <= z + 2; iterZ++) {
                for (int iterY = y - 1; iterY <= y; iterY++) {
                    generateBerryBlock(world, iterX, iterY, iterZ, random);
                }
            }
        }

        for (int iterX = x - 1; iterX <= x + 1; iterX++) {
            for (int iterZ = z - 1; iterZ <= z + 1; iterZ++) {
                int yPos = y + 1;
                generateBerryBlock(world, iterX, yPos, iterZ, random);
                yPos = y - 2;
                generateBerryBlock(world, iterX, yPos, iterZ, random);
            }
        }
    }

    public void generateShrub(World world, Random random, int x, int y, int z) {
        int l;

        Block block = null;
        do {
            block = world.getBlock(x, y, z);
            if (!world.isAirBlock(x, y, z) && !block.isLeaves(world, x, y, z)) {
                break;
            }
            y--;
        } while (y > 0);

        Block i1 = world.getBlock(x, y, z);

        if (i1 == Blocks.dirt || i1 == Blocks.grass) {
            ++y;

            for (int yPos = y; yPos <= y + 2; ++yPos) {
                int k1 = yPos - y;
                int l1 = 2 - k1;

                for (int xPos = x - l1; xPos <= x + l1; ++xPos) {
                    int j2 = xPos - x;

                    for (int zPos = z - l1; zPos <= z + l1; ++zPos) {
                        int l2 = zPos - z;

                        block = world.getBlock(xPos, yPos, zPos);

                        if ((Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0)
                                && block.canBeReplacedByLeaves(world, xPos, yPos, zPos)) {
                            // this.setBlockAndMetadata(world, i2, j1, k2, Block.leaves.blockID,
                            // this.field_76527_a);
                            generateBerryBlock(world, xPos, yPos, zPos, random);
                        }
                    }
                }
            }
        }
    }

    public void generateSmallNode(World world, Random random, int x, int y, int z) {
        generateBerryBlock(world, x, y, z, random);
        if (random.nextBoolean()) generateBerryBush(world, x + 1, y, z, random);
        if (random.nextBoolean()) generateBerryBush(world, x - 1, y, z, random);
        if (random.nextBoolean()) generateBerryBush(world, x, y, z + 1, random);
        if (random.nextBoolean()) generateBerryBush(world, x, y, z - 1, random);
    }

    public void generateTinyNode(World world, Random random, int x, int y, int z) {
        generateBerryBush(world, x, y, z, random);
    }

    void generateBerryBlock(World world, int x, int y, int z, Random random) {
        if (!world.getBlock(x, y, z).isOpaqueCube()) {
            int metaOffset = random.nextInt(5) == 0 ? 1 : 0;
            world.setBlock(x, y, z, NContent.berryBush, metadata + 8 + metaOffset * 4, 0);
        }
    }

    void generateBerryBush(World world, int x, int y, int z, Random random) {
        if (!world.getBlock(x, y, z).isOpaqueCube()) {
            int metaOffset = random.nextInt(4);
            world.setBlock(x, y, z, NContent.berryBush, metadata + metaOffset * 4, 0);
        }
    }
}
