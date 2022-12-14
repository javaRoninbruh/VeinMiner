package wtf.choco.veinminer.config;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import wtf.choco.veinminer.ActivationStrategy;
import wtf.choco.veinminer.VeinMinerServer;
import wtf.choco.veinminer.block.BlockList;
import wtf.choco.veinminer.data.PersistentDataStorage;
import wtf.choco.veinminer.pattern.VeinMiningPattern;
import wtf.choco.veinminer.tool.VeinMinerToolCategory;

/**
 * Represents a rudimentary configuration for VeinMiner.
 * <p>
 * Note that any methods declared in this interface are pulled from a configuration file and
 * should not be confused with any similarly named methods that represent a stored state. For
 * instance, {@link #getDefaultActivationStrategy()} may not necessarily match the value
 * returned by {@link VeinMinerServer#getDefaultActivationStrategy()} as the latter can be
 * changed by the server.
 */
public interface VeinMinerConfiguration {

    /**
     * Get whether or not VeinMiner should check for updates using the update checker.
     *
     * @return true if should check for updates, false otherwise
     */
    public boolean shouldCheckForUpdates();

    /**
     * Get a list of block state strings for the global {@link BlockList}.
     *
     * @return the global block list
     */
    @NotNull
    public List<String> getGlobalBlockList();

    /**
     * Get a list of block state strings for the {@link BlockList} for {@link VeinMinerToolCategory}
     * with the given id.
     *
     * @param categoryId the category id
     *
     * @return the block list of the given category
     */
    @NotNull
    public List<String> getCategoryBlockList(@NotNull String categoryId);

    /**
     * Get the default {@link ActivationStrategy}.
     *
     * @return the default activation strategy
     */
    @NotNull
    public ActivationStrategy getDefaultActivationStrategy();

    /**
     * Get the default {@link VeinMiningPattern}.
     *
     * @return the default vein mining pattern
     */
    @NotNull
    public VeinMiningPattern getDefaultVeinMiningPattern();

    /**
     * Get whether or not dropped items should be collected at the block broken by the player
     * that initiated the vein mine.
     *
     * @return true if should collect at the source, false if dropped at the original positions
     */
    public boolean shouldCollectItemsAtSource();

    /**
     * Get whether or not vein miner is repair friendly and will ensure that tools do not break
     * mid-vein mine
     *
     * @return true if repair friendly, false otherwise
     */
    public boolean isRepairFriendly();

    /**
     * Get whether or not vein miner is repair friendly for the {@link VeinMinerToolCategory}
     * with the given id and will ensure that tools do not break mid-vein mine.
     *
     * @param categoryId the category id
     *
     * @return true if the category is repair friendly, false otherwise
     */
    public boolean isRepairFriendly(@NotNull String categoryId);

    /**
     * Get whether or not vein miner is repair friendly for the {@link VeinMinerToolCategory}
     * with the given id and will ensure that tools do not break mid-vein mine.
     *
     * @param categoryId the category id
     * @param defaultValue the default value to return if not explicitly set
     *
     * @return true if the category is repair friendly, false otherwise
     */
    public boolean isRepairFriendly(@NotNull String categoryId, boolean defaultValue);

    /**
     * Get the maximum amount of blocks that are allowed to be broken in a single vein mine.
     *
     * @return the maximum vein size
     */
    public int getMaxVeinSize();

    /**
     * Get the maximum amount of blocks that are allowed to be broken in a single vein mine
     * by the {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     *
     * @return the maximum vein size of the category
     */
    public int getMaxVeinSize(@NotNull String categoryId);

    /**
     * Get the maximum amount of blocks that are allowed to be broken in a single vein mine
     * by the {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     * @param defaultValue the default value to return if not explicitly set
     *
     * @return the maximum vein size of the category
     */
    public int getMaxVeinSize(@NotNull String categoryId, int defaultValue);

    /**
     * Get the amount of money to be pulled from a player's balance when vein mining.
     *
     * @return the cost of a vein mine
     */
    public double getCost();

    /**
     * Get the amount of money to be pulled from a player's balance when vein mining with the
     * {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     *
     * @return the cost of a vein mine with the category
     */
    public double getCost(@NotNull String categoryId);

    /**
     * Get the amount of money to be pulled from a player's balance when vein mining with the
     * {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     * @param defaultValue the default value to return if not explicitly set
     *
     * @return the cost of a vein mine with the category
     */
    public double getCost(@NotNull String categoryId, double defaultValue);

    /**
     * Get the numeric priority of the {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     *
     * @return the priority
     */
    public int getPriority(@NotNull String categoryId);

    /**
     * Get the NBT value that must be on an item stack in order to use vein miner with the
     * {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     *
     * @return the required NBT value, or null if no value is required
     */
    @Nullable
    public String getNBTValue(@NotNull String categoryId);

    /**
     * Get a list of all disabled game modes.
     *
     * @return all disabled game mode names
     */
    @NotNull
    public List<String> getDisabledGameModeNames();

    /**
     * Get a list of all disabled worlds.
     *
     * @return all disabled worlds
     */
    @NotNull
    public Set<String> getDisabledWorlds();

    /**
     * Get a list of all disabled worlds for the {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     *
     * @return all disabled worlds
     */
    @NotNull
    public Set<String> getDisabledWorlds(@NotNull String categoryId);

    /**
     * Get a list of all disabled worlds for the {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     * @param defaultValues the default values to return if not explicitly set
     *
     * @return all disabled worlds
     */
    @NotNull
    public Set<String> getDisabledWorlds(@NotNull String categoryId, Supplier<Set<String>> defaultValues);

    /**
     * Get the hunger modifier to be applied when a player vein mines. Higher values will apply more
     * hunger to the player.
     *
     * @return the hunger modifier
     */
    public float getHungerModifier();

    /**
     * Get the minimum food level required to vein mine.
     *
     * @return the minimum food level
     */
    public int getMinimumFoodLevel();

    /**
     * Get the message to be sent to the player if they are too hungry to vein mine.
     *
     * @return the message to send, or null if none
     */
    @Nullable
    public String getHungryMessage();

    /**
     * Get the default {@link ClientConfig}.
     *
     * @return the default client config
     */
    @NotNull
    public ClientConfig getClientConfiguration();

    /**
     * Get the {@link wtf.choco.veinminer.data.PersistentDataStorage.Type storage type} to use.
     *
     * @return the storage type
     */
    @NotNull
    public PersistentDataStorage.Type getStorageType();

    /**
     * Get the {@link File directory} in which JSON files should be stored.
     *
     * @return the json storage directory, or null if not specified
     */
    @Nullable
    public File getJsonStorageDirectory();

    /**
     * Get the MySQL host address.
     *
     * @return the host address, or null if not specified
     */
    @Nullable
    public String getMySQLHost();

    /**
     * Get the MySQL port.
     *
     * @return the port, or 0 if not specified
     */
    public int getMySQLPort();

    /**
     * Get the MySQL username.
     *
     * @return the username, or null if not specified
     */
    @Nullable
    public String getMySQLUsername();

    /**
     * Get the MySQL password.
     *
     * @return the password, or null if not specified
     */
    @Nullable
    public String getMySQLPassword();

    /**
     * Get the MySQL database name.
     *
     * @return the database name, or null if not specified
     */
    @Nullable
    public String getMySQLDatabase();

    /**
     * Get the prefix to use for all MySQL tables.
     *
     * @return the prefix, or null if not specified
     */
    @Nullable
    public String getMySQLTablePrefix();

    /**
     * Get a list of the strings defining block aliases.
     *
     * @return the alias strings
     */
    @NotNull
    public List<String> getRawAliasStrings();

    /**
     * Get a set of all defined category ids.
     *
     * @return all category ids
     */
    @NotNull
    public Set<String> getAllDefinedCategoryIds();

    /**
     * Get a list of all item type strings for the {@link VeinMinerToolCategory} with the given id.
     *
     * @param categoryId the category id
     *
     * @return all items in the category
     */
    @NotNull
    public List<String> getCategoryItemList(@NotNull String categoryId);

    /**
     * Write to the configuration file to update the block list of the given category id with the
     * provided {@link BlockList}.
     *
     * @param categoryId the category id to update
     * @param blockList the block list to set
     */
    public void updateBlockList(@NotNull String categoryId, @NotNull BlockList blockList);

    /**
     * Write to the configuration file to update the item list of the given category.
     *
     * @param category the category to update
     */
    public void updateToolList(@NotNull VeinMinerToolCategory category);

    /**
     * Save the default values of this configuration to disk.
     */
    public void saveDefaults();

    /**
     * Reload all values from disk into memory.
     */
    public void reload();

}
