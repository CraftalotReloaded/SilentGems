# Changelog

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.4.2] - 2019-09-21
### Added
- Wisp essence items, dropped by wisps (expect these to be worked into some existing recipes)
- Chaos runes for insulated and grounded effects
- Chaos buffs now accept conditions (like recipes)
- Missing enchantment token and spawn egg recipes
- Config to restrict teleporters to linking to the same gem only (similar to return home charms)
- Config to prevent teleporters linking to teleporter anchors
- Config to control silver ore vein count (set 0 to disable). [#411]
    - Defaults to 0 if Silent's Mechanisms is loaded when the config is created, 2 otherwise
### Changed
- Slightly increased chaos crystal drops
- Chaos buff data path from `silentgems/chaos_buffs` to `silentgems_chaos_buffs`
- Updated Silent Gear part and trait files to new data path
### Fixed
- New soul urn colors being wrong

## [3.4.1] - 2019-09-16
### Changed
- Corrupted slime attack strength reduced by 1 (same as vanilla slimes now)
### Fixed
- Corrupted slimes always rendering as small slimes [#404]
- Corrupted slimes ignoring light levels

## [3.4.0] - 2019-09-15
### Added
- Gem transmutation recipes are back, but with some changes. These require ore blocks now, but produce a full gem from another set (set depends on the catalyst).
- Slime crystal, magma cream crystal, and ender slime crystal (catalyst for gem transmutations)
- Transmutation altar textures
- More advancements!
- Chaos sickness chaos event. Applies some negative effects based on chaos levels, as well as a "chaos sickness" effect which just serves as an indicator.
### Changed
- Increase regional gem types from 2-4 to 4-6 (should make finding Nether/End gems a bit easier)
### Fixed
- Fix crash when using Naturally Charged Creepers [#406]

## [3.3.13] - 2019-09-11
### Changed
- Adds a more helpful message to the ChaosSourceCapability crash, which should help point users in the correct direction. This is usually caused by client-side mods on the server.
- Update required version of Silent Lib to 4.3.2 in mods.toml

## [3.3.12] - 2019-09-10
### Changed
- (API) IPedestalItem is now a capability
### Fixed
- Token enchanter not consuming ingredients [#403]
- Chaos event configs being ignored [#402]
- Sound type of fluffy puff plants
- Required Forge version range (92+)

## [3.3.11] - 2019-09-05
### Added
- Configs to disable individual chaos events. Note that disabling events will likely increase the odds of other events occurring. Does not effect events triggered by commands.
- New lightning event which spawns a series of lightning bolts which do NOT ignite fires. ID is `silentgems:chaos_lightning`. The old event has been renamed to `silentgems:lightning`.
### Fixed
- Gem glass on wrong render layer
- Unlinked redstone teleporters throwing an exception when powered [#395]

## [3.3.10]
### Added
- Transmutation altar. Processes recipes that take one input, one catalyst (not consumed), and produces one output. Generates chaos when working. Recipes can be added with data packs.
### Changed
- Nether star shards are produced in the transmutation altar now (resolves potential conflicts)
### Fixed
- Enchantment token crash with mod-added enchantment types [#394]

## [3.3.9]
Requires Silent Gear 1.3.9 or higher (if installed)
### Added
- Gear items crafted with supercharged parts will receive a "Supercharged" name prefix
### Changed
- Gear souls can now affect other stats, including repair efficiency and ranged damage/speed
### Fixed
- Gear souls not saving their data in some cases
- Wisps spawning indoors (maybe?)

## [3.3.8] - 2019-08-18
### Added
- Crusher recipes for gem ores (Silent's Mechanisms)
    - Recipe for amazonite also included (Slurpie's Dongles)
    - Recipes will load only if relevant mods are installed.

## [3.3.7] - 2019-08-17
### Added
- Gem bag item. Stores anything in the `forge:gems` tag (all gems and such). Items picked up off the ground will be placed in bags first.
- Glowrose basket item. Stores glowroses (`silentgems:glowroses`). Works the same way as gem bags.
- Gem glass can now color beacon beams
- Proper model for token enchanter (just a recolored supercharger for now)
- Textures for insulated and grounded effects (by starmute)
### Changed
- Nerfed persistence trait (it was ticking much more often than intended)

## [3.3.6] - 2019-08-13
### Changed
- Rebalanced chaos values for events. World chaos is once again added to player chaos, but with some exceptions. If player chaos is low and world chaos is high, the world chaos is replaced with the current equilibrium level. This should allow wisps to spawn rarely for players who are not producing chaos. Most events have too high of a minimum to trigger on the equilibrium level alone.
### Fixed
- Chaos rune recipes not producing valid runes
- Empty pedestals absorbing chaos
- Crash when placing soul urns in place of tall grass

## [3.3.5] - 2019-08-12
### Added
- Cooldown timers to chaos events. Varies by event, from around 30 seconds to 20 minutes. Once an event is triggered for a player, the same event cannot trigger again until the cooldown expires. These timers are not stored in NBT, so they will reset when the game/server is restarted.
- Some return home charm config options
    - Option to restrict to teleporters of the same gem (disabled by default)
    - Option to allow binding to anchors (enabled by default)
    - Option to set max uses (0/unlimited by default)
- Amazonite gear part (Slurpie's Dongles)
### Changed
- Rebalanced some chaos-related stuff again
    - Wisps should be less common (min chaos 200k)
    - Corrupted blocks should be slightly more common (min chaos 750k)
    - Events are now rolled every second (up from every 5), makes cooldown times more sensible
- Reduced chance of fire/lightning wisp shots lighting blocks on fires
### Fixed
- World chaos affecting chaos events for all players

## [3.3.4] - 2019-08-11
### Fixed
- Crash when breaking soul urns [#391]

## [3.3.3] - 2019-08-11
### Added
- Recipe for perfect chaos orb (may still change, feedback would be appreciated)
- Recipes for most vacuum and planter soul urn upgrades
- Planter soul urn upgrade model/texture, name [#386]
- Thunderstorm chaos event (very low probability, minimum 1 million chaos)
### Changed
- Wisp colors now match their element
- Increased odds of wisps spawning
- Improved token enchanter's ingredient detection. It can now merge item stacks together if needed.
### Fixed
- Soul urns losing the "lidless" property when harvested
- Lidless soul urn items not rendering correctly
- Supercharger processing items that are already supercharged
- HWYLA reporting the names of gem blocks incorrectly [#390]
- Wisps trying to spawn on peaceful difficulty

## [3.3.2] - 2019-08-06
### Changed
- Non-vanilla dimensions will now spawn classic gems in a "regional" style (similar to the Nether and The End).
### Fixed
- Recipe book crash on Forge 28.0.28+ [#385]
- Supercharging in JEI showing items transforming into other items

## [3.3.1] - 2019-07-28
- Updated for Silent Gear 1.3.2

## [3.3.0] - 2019-07-23
### Added
- A new set of hostile mobs, wisps. These will occasionally spawn on the surface near the player, depending on chaos levels. There are a few different types, each firing a projectile with a different effect.
- Chaos meter item. Measures player and world chaos levels, sneak + right-click to change modes.
- Config to override the "base biome seed", which will change which biomes gems will spawn in.
- Tags for corruptable stone/dirt
- Missing textures and models for corrupted piles and corrupting/purifying powder
- Corrupting/purifying powder to some chest loot tables
### Changed
- Log biomes for gems in a more compact more, should appear in latest.log
- Default soul urn capacity is now 54 (6 rows), upgrade has no effect right now
- Sol and Luna traits now deal much more bonus damage, but with only a 25% chance of activating. Added knockback to sol/luna procs.
### Fixed
- Secret donuts kicking players from servers [#381]

## [3.2.2] - 2019-07-05
### Changed
- Soul gem drop rates receive a small bonus from looting
- Fluffy puffs and fluffy puff seeds can be placed in the composter
### Fixed
- Soul gems not having their data on the client when playing on a server [#380]
- Soul gem drops crashing the game in some cases [#380]
- Hardened stone generating over the ocean [#379]
- Information on chaos buffs (runes for chaos gems) is now synced with the client

## [3.2.1] - 2019-07-03
### Fixed
- Token enchanter recipes preventing server connection [#378]
- JEI plugin failing to load on servers

## [3.2.0] - 2019-07-01
Update to Minecraft 1.14.3
### Added
- Gems added to some loot tables
### Fixed
- Teleporters and return home charms not working

## [3.1.3] - 2019-06-24
JEI plugin is up to date. Recommended Forge is 26.0.51 or later.
### Added
- Treasure Bags support (adds bags for gem sets, no way to obtain aside from commands yet)
- A new command, `sg_soul`. Can currently be used to give soul gems or get a list of all registered souls.
### Changed
- Token enchanter recipes now use the vanilla recipe system, so they have moved to the recipes folder. `type` is `silentgems:token_enchanting`, the format is the same otherwise.
- Updated gem shard textures
### Fixed
- Soul gem ingredient not working correctly

## [3.1.2] - 2019-06-21
(would have been 3.1.1, Git had other plans)  
Minimum Silent Gear version: 1.1.2
### Added
- Gem geodes are back. These are deposits of gems that are encased in hardened rock. There are classic, dark, and light variants, all found in the overworld.
- Gem ores in the Nether and The End now spawn in "regions", similar to old optional configs. Each region will have 2-4 types of gems.
- Dark and light glowroses now spawn in the Nether and The End, respectively
- Implemented traits (some traits are still missing effects)
    - Chaotic - generates chaos when used
    - Entropy - increases block breaking speed depending on chaos levels
    - Luna - attacks deal more damage at night (cancels with Sol)
    - Persistence - slow self-repair
    - Runic - adds damage based on magic damage stat (this will change later)
    - Sol - attacks deal more damage during the day (cancels with Luna)
### Changed
- Updated dark gem ores to match new netherrack texture
- Hardened stone, netherrack, and end stone textures
- Gear souls no longer gain the armor trait unless on an armor item
### Fixed
- Requirements on some advancements (pedestals, hardened rock) [#373]
- Undyed soul urn recipe not considering gem used

## [3.1.0] - 2019-06-17
### Added
- Configs for ender slime spawns are back
- Recipe advancements for most items (except gem stuff)

## [3.0.12] - 2019-04-24
### Added
- Silver
- Corrupting powder and purifying powder. These will create corrupted blocks, or restore them to their normal state. Recipes subject to change.
### Changed
- World chaos will now balance at a certain level, instead of zero. The level fluctuates over time.
- Wild fluffy puff plants will only drop seeds now 
### Fixed
- Possible fix for [#366]
- Crash when Silent Gear not installed [#365]
- Server crash

## [3.0.11] - 2019-04-14
### Added
- Granite, diorite, andesite, and obsidian pedestals. Obsidian pedestal is black-resistant.
- Chaos gems placed on pedestals will apply effects to nearby players, if enabled. The chaos produced is reduced slightly when applied to multiple players. Chaos gems can be enabled/disabled with redstone.
- JEI plugin is back
- Supercharger JEI support, also shows the blocks required for different pillar tiers
- Token enchanter JEI support
- Couple of advancements for pedestals
- (API) IPedestalItem, allows items to be ticked when placed on pedestals
### Removed
- Unneeded block items (blocks you would normally never see in your inventory)
### Changed
- Pedestals can now accept a redstone signal to control some items.
- Rebalanced chaos buff costs 
- Add leakage rate to chaos orb tooltip
### Fixed
- Pedestals not showing items on world load. Currently fixing this by just sending an update packet occasionally, so you may need to wait a few seconds.
- Various localization errors

## [3.0.10] - 2019-04-12
Add some traits to some gems (still working on this). Many of the new traits do nothing right now.
### Added
- Chaos orbs are back. They absorb chaos, but efficiency varies. All orbs have a certain amount of "leakage". They can absorb a fixed amount of chaos, then they break.
- Pedestals. Currently just a plain stone variety, will likely add more. Right-click to place/remove an item. Holds one item only.
- Chaos orbs placed on pedestals will absorb chaos produced by nearby blocks
- Config file (finally)
### Changed
- Glowroses spawn in sparser patches (you can tweak this in the config file). You can get the old behavior by setting `glowrose.world.placeTryCount` to 64 and maybe increase `glowrose.world.maxPerPatch`
- Soul gem average drop rate reduced to 2.5% (from 3.5%). You can change this in the config file.

## [3.0.9] - 2019-03-21
### Added
- Gear souls (formerly tool souls) are back! These work with Silent Gear items only. Soul skills are now learnable traits. Currently no way to customize the skill list, but it will likely happen. This means ANY trait could be made learnable, even user-defined traits.
- New potion effects, grounded and insulated. These protect from the shocking and freezing effects (caused by the lightning/ice aspect enchantments). No way to get these effects yet.
### Fixed
- A server crash (EntityPlayerSP in ChaosGem)
- Crash when Silent Gear is not loaded
- Fluffy puffs not growing [#360]
- Life Steal, Lightning Aspect, and Ice Aspect not working (Gravity still seems off)

## [3.0.8] - 2019-03-16
### Added
- Chaos gems and chaos runes are back. Both are crafted in the token enchanter.
- More advancements (supercharger pillar tiers, teleporters, and more)
- Teleporter linker recipe
- Ender slime spawns (plus spawn egg)
### Changed
- Enriched chaos crystals are now crafted in the token enchanter (same ingredients)
- Reorganized some advancements
### Fixed
- "Modify soul urn" recipe is working again. Craft a soul urn with a gem or dyes to change its appearance. Dyeing working just like leather armor.
- Chaos dust is now craftable (and therefore chaos iron)
- Ender slime loot table

## [3.0.7] - 2019-03-12
### Fixed
- Crash related to soul gems

## [3.0.6] - 2019-03-12
### Added
- Teleporters are back.
- Return home charms are back. Each gem variant is now a separate item.
- Soul gems are back, with changes. A soul gem is automatically added for all living entities. Plant soul gems are gone. Elements are randomized and they will probably have nonsense names (TBD). The elements are meaningless until gear souls are added.
- Soul gem ingredient for use in recipes. This allows vanilla shaped/shapeless recipes and token enchanter recipes to require a soul gem of a specific entity. See the wiki for details if you want to take advantage of this ~~(politely yell at me on Discord if I have forgotten to write the wiki page)~~.
- You can now create spawn eggs in the token enchanter. Requires eggs, soul gems, and enriched chaos crystals (you can view the recipes in REI)
- `sg_chaos` command can now target the world
### Fixed
- Ornate gold and silver rods crashing the game
- `sg_chaos get` command now works correctly

## [3.0.5] - 2019-03-09
### Added
- New chaos system. This is no longer an energy system, but more like a "pollution" system. Certain blocks and items (token enchanter, supercharger) will generate chaos. Chaos will dissipate naturally over time. Currently, there is no way to increase the dissipation rate. If chaos gets too high, you may see random lightning strikes or discover patches of corrupted stone or dirt underground. More effects are planned.
- Token enchanter. This is the new way of creating enchantment tokens. It supports loading recipes from data packs (place the files in `data/*/silentgems/token_recipes`, where `*` is your data pack's namespace). These recipes are not limited to enchantment tokens, you could use it to create anything.
- Partial REI support for token enchanter and supercharger. Speed crafting (shift-click to transfer ingredients) is _not_ supported at this time.
- Wild fluffy puff plants to provide a source of seeds. Can be found in most (not all) biomes that get rain.
- All foods have their respective effects again (try the donuts!)
### Fixed
- Gems missing rarity stat (Silent Gear) [#358]
- Foods will return items again (sticks, bowls)

## [3.0.4] - 2019-03-02
### Added
- Supercharger is back, along with full Silent Gear integration
- Fluffy blocks are back
- Advancements are back
- Color handlers are back. Wrapped in a try-catch to (hopefully) prevent crashes.
- Lots of missing recipes
### Changed
- Overhauled the localization file, minimizing duplication. Should make translating the mod easier.
### Fixed
- Soul urns should be fully functional (but cannot be crafted in survival due to lack of soul gem drops)
- Ender crystal block effect was missing
- Chaos coal blocks not working as fuel
- Broken translations

## [3.0.3] - 2019-02-24
### Added
- Null check for BlockColors and ItemColors. For some reason, these turn up null at random. This should prevent the crash when it happens but certain blocks or items may be missing their colors.

## [3.0.2] - 2019-02-23
### Added
- Glowroses can now be planted in vanilla flower pots. This amplifies their light level to 15.
- Chaos and Ender ore generation
### Fixed
- Nether and End gems not spawning. Currently, both will spawn multi-gem ore. The overworld still has biome-dependent spawns.
- Glowrose hit box and collision fixed
- Silent Gear part files corrected. These should function correctly whenever Gear is ready to release.

## [3.0.1] - 2019-02-21
### Fixed
- Crash caused by a build error [#354]

## [3.0.0] - 2019-02-21
Ported to 1.13.2. This version will bring some major changes:
- The Flattening has changed almost every block and item ID. Old worlds are not compatible.
- Tools and armor have been removed.
    - For a similar system, check out Silent Gear.
    - I am considering a mod which will add simple, non-upgradeable gem tools.
- The chaos system is gone, for now. I am working on an alternative. Chaos was never intended to be an energy system and I hope to make it into something more interesting.
