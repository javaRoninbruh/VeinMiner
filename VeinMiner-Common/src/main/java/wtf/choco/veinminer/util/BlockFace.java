package wtf.choco.veinminer.util;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a block face.
 */
public enum BlockFace {

    NORTH(0, 0, -1),
    EAST(1, 0, 0),
    SOUTH(0, 0, 1),
    WEST(-1, 0, 0),
    UP(0, 1, 0),
    DOWN(0, -1, 0),

    NORTH_EAST(1, 0, -1),
    NORTH_WEST(-1, 0, -1),
    SOUTH_EAST(1, 0, 1),
    SOUTH_WEST(-1, 0, 1),

    NORTH_UP(0, 1, -1),
    EAST_UP(1, 1, 0),
    SOUTH_UP(0, 1, 1),
    WEST_UP(-1, 1, 0),
    NORTH_DOWN(0, -1, -1),
    EAST_DOWN(1, -1, 0),
    SOUTH_DOWN(0, -1, 1),
    WEST_DOWN(-1, -1, 0);

    private final int xOffset, yOffset, zOffset;

    private BlockFace(int xOffset, int yOffset, int zOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    /**
     * Get the x offset of this block face.
     *
     * @return the x offset
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * Get the y offset of this block face.
     *
     * @return the y offset
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * Get the z offset of this block face.
     *
     * @return the z offset
     */
    public int getZOffset() {
        return zOffset;
    }

    /**
     * Get the {@link BlockFace} opposite to this BlockFace. Note that this works only for
     * cardinal faces (up, down, east, west, north, and south). Any other face will throw
     * an {@link UnsupportedOperationException}.
     *
     * @return the opposite block face
     */
    @NotNull
    public BlockFace getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case EAST -> WEST;
            case WEST -> EAST;
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            default -> throw new UnsupportedOperationException("Unknown opposing BlockFace");
        };
    }

    /**
     * Get a {@link BlockPosition} offset by this {@link BlockFace}'s offset coordinates
     * relative to the given BlockPosition.
     *
     * @param position the block of reference
     *
     * @return the relative block
     */
    @NotNull
    public BlockPosition getRelative(@NotNull BlockPosition position) {
        return position.offset(xOffset, yOffset, zOffset);
    }

}