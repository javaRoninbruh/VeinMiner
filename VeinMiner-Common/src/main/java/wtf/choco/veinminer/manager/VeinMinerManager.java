package wtf.choco.veinminer.manager;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import wtf.choco.veinminer.VeinMiner;
import wtf.choco.veinminer.block.BlockList;
import wtf.choco.veinminer.block.VeinMinerBlock;
import wtf.choco.veinminer.config.VeinMinerConfig;
import wtf.choco.veinminer.platform.BlockState;
import wtf.choco.veinminer.platform.BlockType;
import wtf.choco.veinminer.platform.GameMode;
import wtf.choco.veinminer.tool.VeinMinerToolCategory;

/**
 * A manager for VeinMiner's general configurable values.
 */
public final class VeinMinerManager {

    private BlockList globalBlockList = new BlockList();
    private VeinMinerConfig globalConfig = new VeinMinerConfig();

    private final Set<GameMode> disabledGameModes = EnumSet.noneOf(GameMode.class);

    /**
     * Set the global {@link BlockList}.
     * <p>
     * Values in this BlockList should be breakable by all categories.
     *
     * @param blockList the block list to set
     */
    public void setGlobalBlockList(@NotNull BlockList blockList) {
        this.globalBlockList = blockList;
    }

    /**
     * Get the global {@link BlockList}.
     * <p>
     * Values in this BlockList should be breakable by all categories.
     *
     * @return the global block list
     */
    @NotNull
    public BlockList getGlobalBlockList() {
        return globalBlockList;
    }

    /**
     * Set the global {@link VeinMinerConfig}.
     * <p>
     * Values in this config should be used as the default unless otherwise specified.
     *
     * @param config the config to set
     */
    public void setGlobalConfig(@NotNull VeinMinerConfig config) {
        this.globalConfig = config;
    }

    /**
     * Get the global {@link VeinMinerConfig}.
     * <p>
     * Values in this config should be used as the default unless otherwise specified.
     *
     * @return the global config
     */
    @NotNull
    public VeinMinerConfig getGlobalConfig() {
        return globalConfig;
    }

    /**
     * Check whether or not the given {@link GameMode} is disabled.
     *
     * @param gameMode the game mode to check
     *
     * @return true if disabled, false otherwise
     */
    public boolean isDisabledGameMode(@NotNull GameMode gameMode) {
        return disabledGameModes.contains(gameMode);
    }

    /**
     * Set the disabled {@link GameMode GameModes}.
     *
     * @param gameModes the game modes to disable
     */
    public void setDisabledGameModes(@NotNull Collection<GameMode> gameModes) {
        this.disabledGameModes.clear();
        this.disabledGameModes.addAll(gameModes);
    }

    /**
     * Get an unmodifiable {@link Set} of all disabled {@link GameMode GameModes}.
     *
     * @return all disabled game modes
     */
    @NotNull
    @UnmodifiableView
    public Set<GameMode> getDisabledGameModes() {
        return Collections.unmodifiableSet(disabledGameModes);
    }

    /**
     * Get a {@link BlockList} of all {@link VeinMinerBlock VeinMinerBlocks} vein mineable
     * in any category.
     *
     * @return a BlockList containing the blocks of all categories and the global BlockList
     */
    @NotNull
    public BlockList getAllVeinMineableBlocks() {
        BlockList blockList = globalBlockList.clone();

        VeinMiner.getInstance().getToolCategoryRegistry().getAll().forEach(category -> {
            blockList.addAll(category.getBlockList());
        });

        return blockList;
    }

    /**
     * Check whether or not the given {@link BlockState} can be destroyed using vein miner
     * under the given {@link VeinMinerToolCategory}.
     * <p>
     * Convenience method to check both the category's {@link BlockList}, as well as
     * {@link #getGlobalBlockList() the global BlockList}.
     *
     * @param state the state to check
     * @param category the category to check
     *
     * @return true if the state is vein mineable, false otherwise
     */
    public boolean isVeinMineable(@NotNull BlockState state, @NotNull VeinMinerToolCategory category) {
        return globalBlockList.containsState(state) || category.getBlockList().containsState(state);
    }

    /**
     * Check whether or not the given {@link BlockType} can be destroyed using vein miner
     * under the given {@link VeinMinerToolCategory}.
     * <p>
     * Convenience method to check both the category's {@link BlockList}, as well as
     * {@link #getGlobalBlockList() the global BlockList}.
     *
     * @param type the type to check
     * @param category the category to check
     *
     * @return true if the type is vein mineable, false otherwise
     */
    public boolean isVeinMineable(@NotNull BlockType type, @NotNull VeinMinerToolCategory category) {
        return globalBlockList.containsType(type) || category.getBlockList().containsType(type);
    }

    /**
     * Check whether or not the given {@link BlockState} can be destroyed using vein miner
     * with any category.
     * <p>
     * Convenience method to check all {@link BlockList BlockLists}, including
     * {@link #getGlobalBlockList() the global BlockList}.
     *
     * @param state the state to check
     *
     * @return true if the state is vein mineable, false otherwise
     */
    public boolean isVeinMineable(@NotNull BlockState state) {
        if (globalBlockList.containsState(state)) {
            return true;
        }

        for (VeinMinerToolCategory category : VeinMiner.getInstance().getToolCategoryRegistry().getAll()) {
            if (category.getBlockList().containsState(state)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check whether or not the given {@link BlockType} can be destroyed using vein miner
     * with any category.
     * <p>
     * Convenience method to check all {@link BlockList BlockLists}, including
     * {@link #getGlobalBlockList() the global BlockList}.
     *
     * @param type the type to check
     *
     * @return true if the type is vein mineable, false otherwise
     */
    public boolean isVeinMineable(@NotNull BlockType type) {
        if (globalBlockList.containsType(type)) {
            return true;
        }

        for (VeinMinerToolCategory category : VeinMiner.getInstance().getToolCategoryRegistry().getAll()) {
            if (category.getBlockList().containsType(type)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get the {@link VeinMinerBlock} instance from the provided {@link VeinMinerToolCategory}'s
     * {@link BlockList} that matches the given {@link BlockState}.
     *
     * @param state the state whose matching VeinMinerBlock instance to get
     * @param category the category whose BlockList from which the instance should be fetched
     *
     * @return the matching VeinMinerBlock, or null if none exists
     */
    @Nullable
    public VeinMinerBlock getVeinMinerBlock(@NotNull BlockState state, @NotNull VeinMinerToolCategory category) {
        VeinMinerBlock block = globalBlockList.getVeinMinerBlock(state);
        return (block != null) ? block : category.getBlockList().getVeinMinerBlock(state);
    }

    /**
     * Clear values stored in this {@link VeinMinerManager} (excluding the {@link #getGlobalConfig()}).
     */
    public void clear() {
        this.globalBlockList.clear();
        this.disabledGameModes.clear();
    }

}