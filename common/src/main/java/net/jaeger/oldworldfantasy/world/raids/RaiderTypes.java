package net.jaeger.oldworldfantasy.world.raids;

import net.jaeger.oldworldfantasy.entity.ModEntities;

import java.util.Map;

public class RaiderTypes {

    public static final RaiderType UNGOR = RaiderType.create("ungor", ModEntities.UNGOR.get(), new int[]{1, 0, 2, 0, 1, 2, 2, 3});
    public static final RaiderType GOR = RaiderType.create("gor", ModEntities.GOR.get(), new int[]{0, 1, 0, 0, 0, 1, 1, 2});
    public static final RaiderType BESTIGOR = RaiderType.create("bestigor", ModEntities.BESTIGOR.get(), new int[]{0, 0, 0, 0, 2, 2, 2, 2});
    public static final RaiderType WARGOR = RaiderType.create("wargor", ModEntities.WARGOR.get(), new int[]{0, 1, 0, 1, 0, 1, 0, 2});

    public static final RaiderType GOBLIN = RaiderType.create("goblin", ModEntities.GOBLIN.get(), new int[]{1, 0, 2, 0, 1, 2, 2, 3});
    public static final RaiderType ORC = RaiderType.create("orc", ModEntities.ORC.get(), new int[]{0, 1, 0, 0, 0, 1, 1, 2});
    public static final RaiderType BIGUNS = RaiderType.create("biguns", ModEntities.BIGUNS.get(), new int[]{0, 0, 0, 0, 2, 2, 2, 2});
    public static final RaiderType ORCWARBOSS = RaiderType.create("orcwarboss", ModEntities.ORCWARBOSS.get(), new int[]{0, 1, 0, 1, 0, 1, 0, 2});

    public static final Map<String, RaiderType> BEASTMEN = Map.ofEntries(
            Map.entry("ungor", UNGOR),
            Map.entry("gor", GOR),
            Map.entry("bestigor", BESTIGOR),
            Map.entry("wargor", WARGOR)
    );

    public static final Map<String, RaiderType> GREENSKINS = Map.ofEntries(
            Map.entry("goblin", GOBLIN),
            Map.entry("orc", ORC),
            Map.entry("biguns", BIGUNS),
            Map.entry("orcwarboss", ORCWARBOSS)
    );
}
