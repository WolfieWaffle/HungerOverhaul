package iguanaman.hungeroverhaul.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class IguanaConfig
{
    public static final String mainConfigFileName = "HungerOverhaul.cfg";
    public static Configuration mainConfig;
    public static final String blankSlateConfigFileName = "blankslate.cfg";
    public static Configuration blankSlateConfig;
    public static final String defaultConfigFileName = "default.cfg";
    public static Configuration defaultConfig;

    public static final List<ConfigCategory> configCategories = new ArrayList<ConfigCategory>();
    public static final List<ConfigOption<?>> configOptions = new ArrayList<ConfigOption<?>>();
    public static final List<ConfigCategory> removedConfigCategories = new ArrayList<ConfigCategory>();
    public static final List<ConfigOption<?>> removedConfigOptions = new ArrayList<ConfigOption<?>>();

    public static final ConfigCategory CATEGORY_GETTING_SEEDS = addCategory("getting seeds", "Options for obtaining seeds");
    public static final ConfigCategory CATEGORY_DELAYS = addCategory("delays", "Delays for various obtaining food related activities");
    public static final ConfigCategory CATEGORY_HARVESTING = addCategory("harvesting", "Options related to drops from crops");
    public static final ConfigCategory CATEGORY_CUSTOM_FIELD = addCategory("custom field", "Options for enabling and manipulating a custom field in Villages");
    public static final ConfigCategory CATEGORY_DIFFICULTY_SCALING = addCategory("difficulty scaling", "Options to scale the difficulty of certain elements based on game difficulty");
    public static final ConfigCategory CATEGORY_FOOD = addCategory("food", "Food related options");
    public static final ConfigCategory CATEGORY_HARVESTCRAFT = addCategory("harvestcraft", "Options related to HarvestCraft.\n" + EnumChatFormatting.RED + "Only works if HarvestCraft is installed!");
    public static final ConfigCategory CATEGORY_HUNGER = addCategory("hunger", "Options related to hunger");
    public static final ConfigCategory CATEGORY_LOW_STATS = addCategory("low stats", "Options for how to handle when the player get low health/hunger");
    public static final ConfigCategory CATEGORY_HEALTH = addCategory("health", "Options related to health");
    @Deprecated
    public static final ConfigCategory CATEGORY_ROTTING = removeCategory("rotting");
    @Deprecated
    public static final ConfigCategory CATEGORY_POTION_IDS = removeCategory("potion ids");

    public static ConfigCategory addCategory(String name, String comment)
    {
        return putCategoryInList(name, comment, configCategories);
    }

    public static ConfigCategory removeCategory(String name)
    {
        return putCategoryInList(name, "", removedConfigCategories);
    }

    public static ConfigCategory putCategoryInList(String name, String comment, List<ConfigCategory> list)
    {
        ConfigCategory category = new ConfigCategory(name, comment);
        list.add(category);
        return category;
    }

    public static <T> ConfigOption<T> addOption(ConfigCategory category, String name, T defaultValue, T blankSlate, String comment)
    {
        return addOption(category, name, defaultValue, null, null, blankSlate, comment);
    }

    public static <T> ConfigOption<T> addOption(ConfigCategory category, String name, T defaultValue, T minValue, T maxValue, T blankSlate, String comment)
    {
        return putOptionInList(category, name, defaultValue, minValue, maxValue, blankSlate, comment, configOptions);
    }

    public static <T> ConfigOption<T> removeOption(ConfigCategory category, String name, T defaultValue)
    {
        return putOptionInList(category, name, defaultValue, null, null, null, "", removedConfigOptions);
    }

    public static <T> ConfigOption<T> putOptionInList(ConfigCategory category, String name, T defaultValue, T minValue, T maxValue, T blankSlate, String comment, List<ConfigOption<?>> list)
    {
        ConfigOption<T> configOption = new ConfigOption<T>(category.name, name, defaultValue, minValue, maxValue, blankSlate, comment);
        list.add(configOption);
        return configOption;
    }

    // seeds + hoes
    public static final ConfigOption<Boolean> allSeedsEqualOption = addOption(CATEGORY_GETTING_SEEDS, "allSeedsEqual", true, false, "Each seed has an equal chance to drop (grass drops and via hoes)");
    public static boolean allSeedsEqual;
    public static final ConfigOption<Boolean> removeTallGrassSeedsOption = addOption(CATEGORY_GETTING_SEEDS, "removeTallGrassSeeds", true, false, "Removes seed drops when breaking tall grass");
    public static boolean removeTallGrassSeeds;
    public static final ConfigOption<Boolean> modifyHoeUseOption = addOption(CATEGORY_GETTING_SEEDS, "modifyHoeUse", true, false, "Changes the use of hoes depending on the availability of water");
    public static boolean modifyHoeUse;
    public static final ConfigOption<Boolean> removeHoeRecipesOption = addOption(CATEGORY_GETTING_SEEDS, "removeHoeRecipes", true, false, "Whether wood and stone hoe recipes are removed");
    public static boolean removeHoeRecipes;
    public static final ConfigOption<Integer> hoeToolDamageMultiplierOption = addOption(CATEGORY_GETTING_SEEDS, "hoeToolDamageMultiplier", 5, 1, null, 1, "Multiplier on tool damage taken when a hoe is used ('modifyHoeUse' must be true)");
    public static int hoeToolDamageMultiplier;
    public static final ConfigOption<Integer> seedChanceOption = addOption(CATEGORY_GETTING_SEEDS, "seedChance", 20, 0, 100, 20, "Percent chance for seed to drop from hoe use on normal difficulty ('modifyHoeUse' must be true)");
    public static int seedChance;

    // delays
    public static final ConfigOption<Integer> noSunlightRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "noSunlightRegrowthMultiplier", 2, 0, null, 1, "Multipier on crop growth time without sunlight (1 to disable feature, 0 to make crops only grow in sunlight)");
    public static int noSunlightRegrowthMultiplier;
    public static final ConfigOption<Integer> wrongBiomeRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "wrongBiomeRegrowthMultiplier", 2, 0, null, 1, "Multipier on crop growth time (except sugarcane) in the wrong biome (1 to disable feature, 0 to make crops only grow in correct biome)");
    public static int wrongBiomeRegrowthMultiplier;
    public static final ConfigOption<Integer> wrongBiomeRegrowthMultiplierSugarcaneOption = addOption(CATEGORY_DELAYS, "wrongBiomeRegrowthMultiplierSugarcane", 2, 0, null, 1, "Multipier on sugarcane growth time in the wrong biome (1 to disable feature, 0 to make sugarcane only grow in correct biome)");
    public static int wrongBiomeRegrowthMultiplierSugarcane;
    public static final ConfigOption<Integer> flowerRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "flowerRegrowthMultiplier", 1, 0, null, 1, "Multiplier on the time it takes a WeeeFlower crop to grow");
    public static int flowerRegrowthMultiplier;
    public static final ConfigOption<Integer> cropRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "cropRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes a non-tree crop to grow");
    public static int cropRegrowthMultiplier;
    public static final ConfigOption<Integer> cactusRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "cactusRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes cactus to grow");
    public static int cactusRegrowthMultiplier;
    public static final ConfigOption<Integer> cocoaRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "cocoaRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes cocoa to grow");
    public static int cocoaRegrowthMultiplier;
    public static final ConfigOption<Integer> sugarcaneRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "sugarcaneRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes sugarcane to grow");
    public static int sugarcaneRegrowthMultiplier;
    public static final ConfigOption<Integer> treeCropRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "treeCropRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes a tree crop to grow");
    public static int treeCropRegrowthMultiplier;
    public static final ConfigOption<Integer> saplingRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "saplingRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes a sapling to grow into a tree");
    public static int saplingRegrowthMultiplier;
    public static final ConfigOption<Integer> netherWartRegrowthMultiplierOption = addOption(CATEGORY_DELAYS, "netherWartRegrowthMultiplier", 4, 0, null, 1, "Multiplier on the time it takes nether wart to grow");
    public static int netherWartRegrowthMultiplier;
    public static final ConfigOption<Integer> dryingRackTimeMultiplierOption = addOption(CATEGORY_DELAYS, "dryingRackTimeMultiplier", 4, 0, null, 1, "Multiplier on the time it takes food to dry on Tinkers' Construct drying racks");
    public static int dryingRackTimeMultiplier;
    public static final ConfigOption<Integer> eggTimeoutMultiplierOption = addOption(CATEGORY_DELAYS, "eggTimeoutMultiplier", 4, 0, null, 1, "Multiplier applied to the delay between chicken egg laying");
    public static int eggTimeoutMultiplier;
    public static final ConfigOption<Integer> breedingTimeoutMultiplierOption = addOption(CATEGORY_DELAYS, "breedingTimeoutMultiplier", 4, 0, null, 1, "Multiplier applied to the delay between breeding entities");
    public static int breedingTimeoutMultiplier;
    public static final ConfigOption<Integer> childDurationMultiplierOption = addOption(CATEGORY_DELAYS, "childDurationMultiplier", 4, 0, null, 1, "Multiplier applied to the delay before children become adults");
    public static int childDurationMultiplier;
    public static final ConfigOption<Integer> milkedTimeoutOption = addOption(CATEGORY_DELAYS, "milkedTimeout", 20, 0, null, 0, "Delay (in minutes) after milking a cow before it can be milked again");
    public static int milkedTimeout;

    // crop drops
    // TODO: blank slate
    public static final ConfigOption<Integer> seedsPerHarvestMinOption = addOption(CATEGORY_HARVESTING, "seedsPerHarvestMin", 0, 0, null, 0, "Minimum number of seeds you get when harvesting a non-tree crop");
    public static int seedsPerHarvestMin;
    public static final ConfigOption<Integer> seedsPerHarvestMaxOption = addOption(CATEGORY_HARVESTING, "seedsPerHarvestMax", 0, 0, null, 0, "Maximum number of seeds you get when harvesting a non-tree crop");
    public static int seedsPerHarvestMax;
    public static final ConfigOption<Integer> producePerHarvestMinOption = addOption(CATEGORY_HARVESTING, "producePerHarvestMin", 2, 0, null, 2, "Minimum number of produce you get when harvesting a non-tree crop");
    public static int producePerHarvestMin;
    public static final ConfigOption<Integer> producePerHarvestMaxOption = addOption(CATEGORY_HARVESTING, "producePerHarvestMax", 4, 0, null, 4, "Maximum number of produce you get when harvesting a non-tree crop");
    public static int producePerHarvestMax;

    // village field
    public static final ConfigOption<Boolean> addCustomVillageFieldOption = addOption(CATEGORY_CUSTOM_FIELD, "addCustomVillageField", true, false, "Adds a custom field to villages");
    public static boolean addCustomVillageField;
    public static final ConfigOption<Integer> fieldNormalWeightOption = addOption(CATEGORY_CUSTOM_FIELD, "fieldNormalWeight", 70, 0, null, 70, "Weighted chance for custom field to contain 'normal' crops");
    public static int fieldNormalWeight;
    public static final ConfigOption<Integer> fieldReedWeightOption = addOption(CATEGORY_CUSTOM_FIELD, "fieldReedWeight", 10, 0, null, 10, "Weighted chance for custom field to contain reeds");
    public static int fieldReedWeight;
    public static final ConfigOption<Integer> fieldStemWeightOption = addOption(CATEGORY_CUSTOM_FIELD, "fieldStemWeight", 10, 0, null, 10, "Weighted chance for custom field to contain pumpkins/melons");
    public static int fieldStemWeight;

    // difficulty scaling
    public static final ConfigOption<Boolean> difficultyScalingOption = addOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScaling", true, false, "Enable/Disable all difficulty scaling effect in one setting");
    public static boolean difficultyScaling;
    public static final ConfigOption<Boolean> difficultyScalingBoneMealOption = addOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScalingBoneMeal", true, false, "Effects of bone meal dependant of difficulty ('difficultyScaling' must be true)");
    public static boolean difficultyScalingBoneMeal;
    public static final ConfigOption<Boolean> difficultyScalingEffectsOption = addOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScalingEffects", true, false, "Negative effects on low health/hunger scale by difficulty ('difficultyScaling' must be true)");
    public static boolean difficultyScalingEffects;
    public static final ConfigOption<Boolean> difficultyScalingHealingOption = addOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScalingHealing", true, false, "Healing rate scales by difficulty ('difficultyScaling' must be true)");
    public static boolean difficultyScalingHealing;
    public static final ConfigOption<Boolean> difficultyScalingHungerOption = addOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScalingHunger", true, false, "Hunger loss rate scales by difficulty ('difficultyScaling' must be true)");
    public static boolean difficultyScalingHunger;
    public static final ConfigOption<Boolean> difficultyScalingRespawnHungerOption = addOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScalingRespawnHunger", true, false, "Hunger value after respawn is affected by difficulty ('difficultyScaling' must be true)");
    public static boolean difficultyScalingRespawnHunger;

    // food
    public static final ConfigOption<Boolean> modifyFoodValuesOption = addOption(CATEGORY_FOOD, "modifyFoodValues", true, false, "Enables/disables all food value modification");
    public static boolean modifyFoodValues;
    public static final ConfigOption<Boolean> useHOFoodValuesOption = addOption(CATEGORY_FOOD, "useHOFoodValues", true, false, "Enables/disables Hunger Overhaul manually setting the food values for supported mods ('modifyFoodValues' must be true)");
    public static boolean useHOFoodValues;
    public static final ConfigOption<Boolean> addFoodTooltipsOption = addOption(CATEGORY_FOOD, "addFoodTooltips", true, false, "Add tooltips to food items showing hints at food value");
    public static boolean addFoodTooltips;
    public static final ConfigOption<Boolean> modifyFoodStackSizeOption = addOption(CATEGORY_FOOD, "modifyFoodStackSize", true, false, "Changes the stack size of food to be dependant on the food's replenishment value");
    public static boolean modifyFoodStackSize;
    public static final ConfigOption<Boolean> modifyFoodEatingSpeedOption = addOption(CATEGORY_FOOD, "modifyFoodEatingSpeed", true, false, "Changes the eating animation speed to be dependant on the food's replenishment value");
    public static boolean modifyFoodEatingSpeed;
    public static final ConfigOption<Integer> foodStackSizeMultiplierOption = addOption(CATEGORY_FOOD, "foodStackSizeMultiplier", 1, 1, null, 1, "Multiplier on the stack size of food ('modifyFoodStackSize' must be true)");
    public static int foodStackSizeMultiplier;
    @Deprecated
    public static final ConfigOption<Float> modFoodValueDividerOption = removeOption(CATEGORY_FOOD, "modFoodValueDivider", 4.0f);
    @Deprecated
    public static int modFoodValueDivider;
    public static final ConfigOption<Float> foodHungerDividerOption = addOption(CATEGORY_FOOD, "foodHungerDivider", 4.0f, 1.0f, "Food values not manually set (see 'useHOFoodValues') will have their hunger value divided by this ('modifyFoodValues' must be true)");
    public static float foodHungerDivider;
    public static final ConfigOption<Float> foodSaturationDividerOption = addOption(CATEGORY_FOOD, "foodSaturationDivider", 1.0f, 1.0f, "Food values not manually set (see 'useHOFoodValues') will have their saturation modifier divided by this ('modifyFoodValues' must be true)\nNote: Gets applied after 'foodHungerToSaturationDivider'");
    public static float foodSaturationDivider;
    public static final ConfigOption<Float> foodHungerToSaturationDividerOption = addOption(CATEGORY_FOOD, "foodHungerToSaturationDivider", 20.0f, 0.0f, "Food values not manually set (see 'useHOFoodValues') will have their saturation modifier set to <modified hunger> divided by this ('modifyFoodValues' must be true)\nSet to 0 to disable");
    public static float foodHungerToSaturationDivider;
    public static final ConfigOption<Boolean> addWellFedEffectOption = addOption(CATEGORY_FOOD, "addWellFedEffect", true, false, "Adds a 'well fed' effect that gives slight health regen");
    public static boolean addWellFedEffect;

    // harvestcraft integration
    public static final ConfigOption<Boolean> addTradesButcherOption = addOption(CATEGORY_HARVESTCRAFT, "addTradesButcher", true, false, "Add high tier HarvestCraft foods to the items butcher villagers will sell (HarvestCraft)");
    public static boolean addTradesButcher;
    public static final ConfigOption<Boolean> addCropTradesFarmerOption = addOption(CATEGORY_HARVESTCRAFT, "addCropTradesFarmer", true, false, "Add HarvestCraft crop produce to the items farmer villagers will buy (HarvestCraft)");
    public static boolean addTradesFarmer;
    public static final ConfigOption<Boolean> addSaplingTradesFarmerOption = addOption(CATEGORY_HARVESTCRAFT, "addSaplingTradesFarmer", true, false, "Add HarvestCraft saplings to the items farmer villagers will sell (HarvestCraft)");
    public static boolean addSaplingTradesFarmer;
    public static final ConfigOption<Boolean> addHarvestCraftChestLootOption = addOption(CATEGORY_HARVESTCRAFT, "addHarvestCraftChestLoot", true, false, "HarvestCraft items added to dungeon/temple chests (Harvestcraft)");
    public static boolean addHarvestCraftChestLoot;
    public static final ConfigOption<Integer> chestLootMaxStackSizeOption = addOption(CATEGORY_HARVESTCRAFT, "chestLootMaxStackSize", 64, 1, null, 64, "Max stack size for food to be found in chests (addHarvestCraftChestLoot must be true)");
    public static int chestLootMaxStackSize;
    public static final ConfigOption<Integer> chestLootChanceOption = addOption(CATEGORY_HARVESTCRAFT, "chestLootChance", 5, 1, null, 5, "Weighted chance for food to be found in chests (addHarvestCraftChestLoot must be true)");
    public static int chestLootChance;
    public static final ConfigOption<Boolean> foodsUnplantableOption = addOption(CATEGORY_HARVESTCRAFT, "foodsUnplantable", true, false, "Makes HarvestCraft foods unplantable, meaning seeds are required to plant crops");
    public static boolean foodsUnplantable;

    // hunger
    public static final ConfigOption<Boolean> constantHungerLossOption = addOption(CATEGORY_HUNGER, "constantHungerLoss", true, false, "You lose hunger (very slowly) at all times");
    public static boolean constantHungerLoss;
    public static final ConfigOption<Integer> damageOnStarveOption = addOption(CATEGORY_HUNGER, "damageOnStarve", 200, 2, "Amount of damage you take when hunger hits zero (vanilla default 2)");
    public static int damageOnStarve;
    public static final ConfigOption<Integer> respawnHungerValueOption = addOption(CATEGORY_HUNGER, "respawnHungerValue", 20, 0, null, 20, "hunger value set after respawning for peaceful/easy difficulty (vanilla default is 20)");
    public static int respawnHungerValue;
    public static final ConfigOption<Integer> respawnHungerDifficultyModifierOption = addOption(CATEGORY_HUNGER, "respawnHungerDifficultyModifier", 4, 0, null, 4, "The amount difficulty modifies the hunger value set after respawning  ('difficultyScaling' and 'difficultyScalingRespawnHunger' must be true)");
    public static int respawnHungerDifficultyModifier;
    public static final ConfigOption<Boolean> disableHealingHungerDrainOption = addOption(CATEGORY_HUNGER, "disableHealingHungerDrain", true, false, "Disable the hunger drain when healing that was introduced in vanilla 1.6.2");
    public static boolean disableHealingHungerDrain;
    public static final ConfigOption<Integer> hungerLossRatePercentageOption = addOption(CATEGORY_HUNGER, "hungerLossRatePercentage", 100, 0, null, 100, "Speed up or slow down the rate that hunger drops (set to 0 to disable hunger loss)");
    public static int hungerLossRatePercentage;

    // low stat
    public static final ConfigOption<Boolean> addGuiTextOption = addOption(CATEGORY_LOW_STATS, "addGuiText", true, false, "Shows onscreen text when hunger/health is low");
    public static boolean addGuiText;
    public static final ConfigOption<Boolean> addLowStatEffectsOption = addOption(CATEGORY_LOW_STATS, "addLowStatEffects", true, false, "Enables/disables all low hunger/health effects");
    public static boolean addLowStatEffects;
    public static final ConfigOption<Boolean> addLowStatNauseaOption = addOption(CATEGORY_LOW_STATS, "addLowStatNausea", true, false, "Nausea effect when hunger is really low ('addLowStatEffects' must be true)");
    public static boolean addLowStatNausea;
    public static final ConfigOption<Boolean> addLowStatSlownessOption = addOption(CATEGORY_LOW_STATS, "addLowStatSlowness", true, false, "Slowness effect when health/hunger is low ('addLowStatEffects' must be true)");
    public static boolean addLowStatSlowness;
    public static final ConfigOption<Boolean> addLowStatWeaknessOption = addOption(CATEGORY_LOW_STATS, "addLowStatWeakness", true, false, "Weakness effect when health/hunger is low ('addLowStatEffects' must be true)");
    public static boolean addLowStatWeakness;
    public static final ConfigOption<Boolean> addLowStatMiningSlowdownOption = addOption(CATEGORY_LOW_STATS, "addLowStatMiningSlowdown", true, false, "Mining slowdown effect when health/hunger is low ('addLowStatEffects' must be true)");
    public static boolean addLowStatMiningSlowdown;

    // health
    public static final ConfigOption<Integer> minHungerToHealOption = addOption(CATEGORY_HEALTH, "minHungerToHeal", 7, 0, null, 18, "Minimum hunger level before healing starts (vanilla default is 18)");
    public static int minHungerToHeal;
    public static final ConfigOption<Boolean> foodRegensHealthOption = addOption(CATEGORY_HEALTH, "foodRegensHealth", false, false, "Eating food regenerates health");
    public static boolean foodRegensHealth;
    public static final ConfigOption<Integer> foodHealDividerOption = addOption(CATEGORY_HEALTH, "foodHealDivider", 4, 0, null, 4, "Amount of health food restores is the food value divided by this number (foodRegensHealth must be true)");
    public static int foodHealDivider;
    public static final ConfigOption<Integer> healthRegenRatePercentageOption = addOption(CATEGORY_HEALTH, "healthRegenRatePercentage", 100, 0, null, 100, "Speed up or slow down the rate that health regenerates (0 to disable regen)");
    public static int healthRegenRatePercentage;
    // TODO: blank slate
    public static final ConfigOption<Integer> lowHealthRegenRateModifierOption = addOption(CATEGORY_HEALTH, "lowHealthRegenRateModifier", 5, 0, null, 5, "The lower your health the longer it takes to regen, modify the effect here (lower = less effect)");
    public static int lowHealthRegenRateModifier;

    // various removed options
    @Deprecated
    public static final ConfigOption<Boolean> addSetHungerCommand = removeOption(CATEGORY_HUNGER, "addSetHungerCommand", true);
    @Deprecated
    public static final ConfigOption<Boolean> addSleepHungerLoss = removeOption(CATEGORY_HUNGER, "addSleepHungerLoss", false);
    @Deprecated
    public static final ConfigOption<Boolean> difficultyScalingSleepHungerLoss = removeOption(CATEGORY_DIFFICULTY_SCALING, "difficultyScalingSleepHungerLoss", true);

    public static void init(File configDir, File oldConfigFile)
    {
        if (!configDir.exists())
            configDir.mkdirs();

        if (mainConfig == null)
        {
            File mainConfigFile = new File(configDir, mainConfigFileName);
            if (oldConfigFile.exists())
            {
                oldConfigFile.renameTo(mainConfigFile);
            }
            mainConfig = new Configuration(mainConfigFile);
            reload(mainConfig);
        }
        if (blankSlateConfig == null)
        {
            blankSlateConfig = new Configuration(new File(configDir, blankSlateConfigFileName));
            generateBlankSlate();
        }
        if (defaultConfig == null)
        {
            defaultConfig = new Configuration(new File(configDir, defaultConfigFileName));
            generateDefault();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equals("HungerOverhaul"))
        {
            reload(mainConfig);
        }
    }

    public static void generateBlankSlate()
    {
        blankSlateConfig.addCustomCategoryComment(" ", "This config contains settings that will disable all of the changes that Hunger Overhaul makes.\nIt's intended to serve as a starting point for anyone wanting to only enable a few things and leave everything else untouched.\nTo use, rename this file to HungerOverhaul.cfg (overwriting your current HungerOverhaul.cfg)");
        for (ConfigCategory configCategory : configCategories)
        {
            configCategory.create(blankSlateConfig);
        }
        for (ConfigCategory configCategory : removedConfigCategories)
        {
            configCategory.remove(blankSlateConfig);
        }
        for (ConfigOption<?> configOption : configOptions)
        {
            configOption.setToBlankSlate(blankSlateConfig);
        }
        for (ConfigOption<?> configOption : removedConfigOptions)
        {
            configOption.remove(blankSlateConfig);
        }
        blankSlateConfig.save();
    }

    public static void generateDefault()
    {
        defaultConfig.addCustomCategoryComment(" ", "This config contains all of the default Hunger Overhaul settings.\nIt's intended to serve as an easy backup for returning to the defaults.\nTo use, rename this file to HungerOverhaul.cfg (overwriting your current HungerOverhaul.cfg)");
        for (ConfigCategory configCategory : configCategories)
        {
            configCategory.create(defaultConfig);
        }
        for (ConfigCategory configCategory : removedConfigCategories)
        {
            configCategory.remove(defaultConfig);
        }
        for (ConfigOption<?> configOption : configOptions)
        {
            configOption.setToDefault(defaultConfig);
        }
        for (ConfigOption<?> configOption : removedConfigOptions)
        {
            configOption.remove(defaultConfig);
        }
        defaultConfig.save();
    }

    public static void reload(Configuration config)
    {
        // categories
        for (ConfigCategory configCategory : configCategories)
        {
            configCategory.create(config);
        }
        for (ConfigCategory configCategory : removedConfigCategories)
        {
            configCategory.remove(config);
        }
        // info category from default/blankslate configs
        if (config.hasCategory(" "))
            config.removeCategory(config.getCategory(" "));

        // get all config option values
        allSeedsEqual = allSeedsEqualOption.get(config);
        removeHoeRecipes = removeHoeRecipesOption.get(config);
        modifyHoeUse = modifyHoeUseOption.get(config);
        removeTallGrassSeeds = removeTallGrassSeedsOption.get(config);
        seedChance = seedChanceOption.get(config);
        hoeToolDamageMultiplier = hoeToolDamageMultiplierOption.get(config);
        noSunlightRegrowthMultiplier = noSunlightRegrowthMultiplierOption.get(config);
        wrongBiomeRegrowthMultiplier = wrongBiomeRegrowthMultiplierOption.get(config);
        wrongBiomeRegrowthMultiplierSugarcane = wrongBiomeRegrowthMultiplierSugarcaneOption.get(config);
        flowerRegrowthMultiplier = flowerRegrowthMultiplierOption.get(config);
        cropRegrowthMultiplier = cropRegrowthMultiplierOption.get(config);
        sugarcaneRegrowthMultiplier = sugarcaneRegrowthMultiplierOption.get(config);
        cactusRegrowthMultiplier = cactusRegrowthMultiplierOption.get(config);
        cocoaRegrowthMultiplier = cocoaRegrowthMultiplierOption.get(config);
        treeCropRegrowthMultiplier = treeCropRegrowthMultiplierOption.get(config);
        saplingRegrowthMultiplier = saplingRegrowthMultiplierOption.get(config);
        netherWartRegrowthMultiplier = netherWartRegrowthMultiplierOption.get(config);
        dryingRackTimeMultiplier = dryingRackTimeMultiplierOption.get(config);
        eggTimeoutMultiplier = eggTimeoutMultiplierOption.get(config);
        breedingTimeoutMultiplier = breedingTimeoutMultiplierOption.get(config);
        childDurationMultiplier = childDurationMultiplierOption.get(config);
        milkedTimeout = milkedTimeoutOption.get(config);
        seedsPerHarvestMin = seedsPerHarvestMinOption.get(config);
        seedsPerHarvestMax = seedsPerHarvestMaxOption.get(config);
        producePerHarvestMin = producePerHarvestMinOption.get(config);
        producePerHarvestMax = producePerHarvestMaxOption.get(config);
        addCustomVillageField = addCustomVillageFieldOption.get(config);
        fieldNormalWeight = fieldNormalWeightOption.get(config);
        fieldReedWeight = fieldReedWeightOption.get(config);
        fieldStemWeight = fieldStemWeightOption.get(config);
        difficultyScaling = difficultyScalingOption.get(config);
        difficultyScalingBoneMeal = difficultyScalingBoneMealOption.get(config);
        difficultyScalingEffects = difficultyScalingEffectsOption.get(config);
        difficultyScalingHealing = difficultyScalingHealingOption.get(config);
        difficultyScalingHunger = difficultyScalingHungerOption.get(config);
        difficultyScalingRespawnHunger = difficultyScalingRespawnHungerOption.get(config);
        modifyFoodValues = modifyFoodValuesOption.get(config);
        useHOFoodValues = useHOFoodValuesOption.get(config);
        addFoodTooltips = addFoodTooltipsOption.get(config);
        modifyFoodEatingSpeed = modifyFoodEatingSpeedOption.get(config);
        modifyFoodStackSize = modifyFoodStackSizeOption.get(config);
        foodStackSizeMultiplier = foodStackSizeMultiplierOption.get(config);

        // renamed to foodHungerDivider; this will transfer the old value over
        float oldFoodValueDividerValue = modFoodValueDividerOption.get(config);
        foodHungerDividerOption.defaultValue = oldFoodValueDividerValue;
        foodHungerDivider = foodHungerDividerOption.get(config);
        // for backwards compatibility
        modFoodValueDivider = (int) foodHungerDivider;

        foodSaturationDivider = foodSaturationDividerOption.get(config);
        foodHungerToSaturationDivider = foodHungerToSaturationDividerOption.get(config);
        addWellFedEffect = addWellFedEffectOption.get(config);
        addTradesButcher = addTradesButcherOption.get(config);
        addTradesFarmer = addCropTradesFarmerOption.get(config);
        addSaplingTradesFarmer = addSaplingTradesFarmerOption.get(config);
        addHarvestCraftChestLoot = addHarvestCraftChestLootOption.get(config);
        chestLootMaxStackSize = chestLootMaxStackSizeOption.get(config);
        chestLootChance = chestLootChanceOption.get(config);
        foodsUnplantable = foodsUnplantableOption.get(config);
        constantHungerLoss = constantHungerLossOption.get(config);
        damageOnStarve = damageOnStarveOption.get(config);
        respawnHungerValue = respawnHungerValueOption.get(config);
        respawnHungerDifficultyModifier = respawnHungerDifficultyModifierOption.get(config);
        disableHealingHungerDrain = disableHealingHungerDrainOption.get(config);
        hungerLossRatePercentage = hungerLossRatePercentageOption.get(config);
        addGuiText = addGuiTextOption.get(config);
        addLowStatEffects = addLowStatEffectsOption.get(config);
        addLowStatNausea = addLowStatNauseaOption.get(config);
        addLowStatSlowness = addLowStatSlownessOption.get(config);
        addLowStatWeakness = addLowStatWeaknessOption.get(config);
        addLowStatMiningSlowdown = addLowStatMiningSlowdownOption.get(config);
        foodRegensHealth = foodRegensHealthOption.get(config);
        minHungerToHeal = minHungerToHealOption.get(config);
        healthRegenRatePercentage = healthRegenRatePercentageOption.get(config);
        lowHealthRegenRateModifier = lowHealthRegenRateModifierOption.get(config);
        foodHealDivider = foodHealDividerOption.get(config);

        for (ConfigOption<?> configOption : removedConfigOptions)
        {
            configOption.remove(config);
        }

        if (config.hasChanged())
            config.save();
    }
}
