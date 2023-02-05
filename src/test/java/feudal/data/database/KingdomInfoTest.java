package feudal.data.database;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterType;
import com.mongodb.internal.connection.SingleServerCluster;
import com.mongodb.selector.LatencyMinimizingServerSelector;
import net.minecraft.server.v1_12_R1.*;
import org.bson.internal.ProvidersCodecRegistry;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.joptsimple.OptionSet;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.generator.NetherChunkGenerator;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.Proxy;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class KingdomInfoTest {
    /**
     * Method under test: {@link KingdomInfo#KingdomInfo(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name");

    }

    /**
     * Method under test: {@link KingdomInfo#KingdomInfo(String, String, String)}
     */
    @Test
    void testConstructor2() {
        MongoClient mongoClient = (new KingdomInfo("foo", "foo", "foo")).getMongoClient();
        assertTrue(mongoClient instanceof MongoClientImpl);
        assertTrue(((MongoClientImpl) mongoClient).getCodecRegistry() instanceof ProvidersCodecRegistry);
        assertTrue(((MongoClientImpl) mongoClient).getCluster() instanceof SingleServerCluster);
        ClusterDescription clusterDescription = mongoClient.getClusterDescription();
        assertNull(clusterDescription.findServerIncompatiblyNewerThanDriver());
        assertEquals(ClusterType.UNKNOWN, clusterDescription.getType());
        assertNull(clusterDescription.getSrvResolutionException());
        assertEquals(1, clusterDescription.getServerDescriptions().size());
        assertEquals(ClusterConnectionMode.SINGLE, clusterDescription.getConnectionMode());
        assertEquals(1, clusterDescription.getAll().size());
        ClusterSettings clusterSettings = clusterDescription.getClusterSettings();
        assertEquals("{hosts=[foo:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms',"
                + " maxWaitQueueSize=500}", clusterSettings.getShortDescription());
        assertTrue(clusterSettings.getServerSelector() instanceof LatencyMinimizingServerSelector);
        assertNull(clusterSettings.getRequiredReplicaSetName());
        assertEquals(ClusterType.UNKNOWN, clusterSettings.getRequiredClusterType());
        assertEquals(ClusterConnectionMode.SINGLE, clusterSettings.getMode());
        assertEquals(500, clusterSettings.getMaxWaitQueueSize());
        assertEquals(1, clusterSettings.getHosts().size());
        assertNull(clusterSettings.getDescription());
        assertTrue(clusterDescription.getServerSettings().getServerMonitorListeners().isEmpty());
        assertNull(clusterSettings.getSrvHost());
    }

    /**
     * Method under test: {@link KingdomInfo#KingdomInfo(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: The connection string contains an invalid host 'mongodb:'. The port '' is not a valid, it must be an integer between 0 and 65535
        //       at com.mongodb.ConnectionString.validatePort(ConnectionString.java:1020)
        //       at com.mongodb.ConnectionString.parseHosts(ConnectionString.java:1000)
        //       at com.mongodb.ConnectionString.<init>(ConnectionString.java:344)
        //       at com.mongodb.client.MongoClients.create(MongoClients.java:61)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        new KingdomInfo("mongodb://", "Database Name", "Collection Name");

    }

    /**
     * Method under test: {@link KingdomInfo#KingdomInfo(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: The connection string contains an empty host '[]'.
        //       at com.mongodb.ConnectionString.parseHosts(ConnectionString.java:981)
        //       at com.mongodb.ConnectionString.<init>(ConnectionString.java:344)
        //       at com.mongodb.client.MongoClients.create(MongoClients.java:61)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        new KingdomInfo("", "Database Name", "Collection Name");

    }

    /**
     * Method under test: {@link KingdomInfo#KingdomInfo(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName is not empty
        //       at com.mongodb.assertions.Assertions.isTrueArgument(Assertions.java:101)
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:59)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        new KingdomInfo("Dr Jane Doe", "", "Collection Name");

    }

    /**
     * Method under test: {@link KingdomInfo#KingdomInfo(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: collectionName is not empty
        //       at com.mongodb.assertions.Assertions.isTrueArgument(Assertions.java:101)
        //       at com.mongodb.MongoNamespace.checkCollectionNameValidity(MongoNamespace.java:78)
        //       at com.mongodb.MongoNamespace.<init>(MongoNamespace.java:109)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.getCollection(MongoDatabaseImpl.java:142)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.getCollection(MongoDatabaseImpl.java:137)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:37)
        //   See https://diff.blue/R013 to resolve this issue.

        new KingdomInfo("foo", "foo", "");

    }

    /**
     * Method under test: {@link KingdomInfo#createNewKingdom(String, Player, List, List, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewKingdom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name");
        OptionSet options = mock(OptionSet.class);
        DataConverterManager dataconvertermanager = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(
                new Proxy(Proxy.Type.DIRECT, null), "foo");

        MinecraftSessionService minecraftsessionservice = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository = mock(GameProfileRepository.class);
        DedicatedServer console = new DedicatedServer(options, dataconvertermanager, yggdrasilauthenticationservice,
                minecraftsessionservice, gameprofilerepository, new UserCache(mock(GameProfileRepository.class),
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options1 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager1 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice1 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice1 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository1 = mock(GameProfileRepository.class);
        CraftServer server = new CraftServer(console,
                new DedicatedPlayerList(new DedicatedServer(options1, dataconvertermanager1, yggdrasilauthenticationservice1,
                        minecraftsessionservice1, gameprofilerepository1, new UserCache(mock(GameProfileRepository.class),
                        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))));

        OptionSet options2 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager2 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice2 = new YggdrasilAuthenticationService(
                new Proxy(Proxy.Type.DIRECT, null), "foo");

        MinecraftSessionService minecraftsessionservice2 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository2 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver = new DedicatedServer(options2, dataconvertermanager2,
                yggdrasilauthenticationservice2, minecraftsessionservice2, gameprofilerepository2, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options3 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager3 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice3 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice3 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository3 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver1 = new DedicatedServer(options3, dataconvertermanager3,
                yggdrasilauthenticationservice3, minecraftsessionservice3, gameprofilerepository3, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager idatamanager = new ServerNBTManager(toFileResult, "foo", true, DataConverterRegistry.a());

        WorldData worlddata = new WorldData(new NBTTagCompound());
        MethodProfiler methodprofiler = new MethodProfiler();
        MinecraftServer minecraftServer = mock(MinecraftServer.class);
        IDataManager iDataManager = mock(IDataManager.class);
        WorldData worldData = mock(WorldData.class);
        WorldServer worldserver = new WorldServer(minecraftserver1, idatamanager, worlddata, 1, methodprofiler,
                World.Environment.NORMAL, new NetherChunkGenerator(
                new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler()), 42L));

        GameProfile gameprofile = new GameProfile(UUID.randomUUID(), "foo");

        OptionSet options4 = mock(OptionSet.class);
        DedicatedServer dedicatedServer = new DedicatedServer(options4, DataConverterRegistry.a(),
                mock(YggdrasilAuthenticationService.class), mock(MinecraftSessionService.class),
                mock(GameProfileRepository.class), mock(UserCache.class));

        File toFileResult1 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager serverNBTManager = new ServerNBTManager(toFileResult1, "foo", true, DataConverterRegistry.a());

        WorldData worldData1 = new WorldData(new NBTTagCompound());
        CraftPlayer king = new CraftPlayer(server,
                new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler()))));

        ArrayList<String> members = new ArrayList<>();
        ArrayList<Chunk> territory = new ArrayList<>();
        kingdomInfo.createNewKingdom("Kingdom Name", king, members, territory, new ArrayList<>());
    }

    /**
     * Method under test: {@link KingdomInfo#createNewKingdom(String, Player, List, List, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewKingdom2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name");
        OptionSet options = mock(OptionSet.class);
        DataConverterManager dataconvertermanager = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository = mock(GameProfileRepository.class);
        DedicatedServer console = new DedicatedServer(options, dataconvertermanager, yggdrasilauthenticationservice,
                minecraftsessionservice, gameprofilerepository, new UserCache(mock(GameProfileRepository.class),
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options1 = mock(OptionSet.class);
        CraftServer server = new CraftServer(console,
                new DedicatedPlayerList(
                        new DedicatedServer(options1, DataConverterRegistry.a(), mock(YggdrasilAuthenticationService.class),
                                mock(MinecraftSessionService.class), mock(GameProfileRepository.class), mock(UserCache.class))));

        OptionSet options2 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager1 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice1 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice1 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository1 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver = new DedicatedServer(options2, dataconvertermanager1,
                yggdrasilauthenticationservice1, minecraftsessionservice1, gameprofilerepository1, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options3 = mock(OptionSet.class);
        DedicatedServer minecraftserver1 = new DedicatedServer(options3, DataConverterRegistry.a(),
                mock(YggdrasilAuthenticationService.class), mock(MinecraftSessionService.class),
                mock(GameProfileRepository.class), mock(UserCache.class));

        File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager idatamanager = new ServerNBTManager(toFileResult, "foo", true, DataConverterRegistry.a());

        WorldData worlddata = new WorldData(new NBTTagCompound());
        MethodProfiler methodprofiler = new MethodProfiler();
        WorldServer worldserver = new WorldServer(minecraftserver1, idatamanager, worlddata, 1, methodprofiler,
                org.bukkit.World.Environment.NORMAL,
                new NetherChunkGenerator(mock(net.minecraft.server.v1_12_R1.World.class), 42L));

        GameProfile gameprofile = new GameProfile(UUID.randomUUID(), "foo");

        MinecraftServer minecraftServer = mock(MinecraftServer.class);
        IDataManager iDataManager = mock(IDataManager.class);
        WorldData worldData = mock(WorldData.class);
        CraftPlayer king = new CraftPlayer(server,
                new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler()))));

        ArrayList<String> members = new ArrayList<>();
        ArrayList<Chunk> territory = new ArrayList<>();
        kingdomInfo.createNewKingdom(null, king, members, territory, new ArrayList<>());
    }

    /**
     * Method under test: {@link KingdomInfo#getField(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetField() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).getField("Kingdom Name", "Field Name");
    }

    /**
     * Method under test: {@link KingdomInfo#getField(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetField2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).getField("foo", "foo");
    }

    /**
     * Method under test: {@link KingdomInfo#setField(String, String, Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetField() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).setField("Kingdom Name", "Field Name",
                "Value");
    }

    /**
     * Method under test: {@link KingdomInfo#setField(String, String, Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetField2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).setField("foo", "foo", "42");
    }

    /**
     * Method under test: {@link KingdomInfo#resetTheKingdom(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetTheKingdom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).resetTheKingdom("Kingdom Name");
    }

    /**
     * Method under test: {@link KingdomInfo#resetTheKingdom(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetTheKingdom2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).resetTheKingdom("foo");
    }

    /**
     * Method under test: {@link KingdomInfo#playerInKingdom(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPlayerInKingdom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name");
        OptionSet options = mock(OptionSet.class);
        DataConverterManager dataconvertermanager = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(
                new Proxy(Proxy.Type.DIRECT, null), "foo");

        MinecraftSessionService minecraftsessionservice = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository = mock(GameProfileRepository.class);
        DedicatedServer console = new DedicatedServer(options, dataconvertermanager, yggdrasilauthenticationservice,
                minecraftsessionservice, gameprofilerepository, new UserCache(mock(GameProfileRepository.class),
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options1 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager1 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice1 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice1 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository1 = mock(GameProfileRepository.class);
        CraftServer server = new CraftServer(console,
                new DedicatedPlayerList(new DedicatedServer(options1, dataconvertermanager1, yggdrasilauthenticationservice1,
                        minecraftsessionservice1, gameprofilerepository1, new UserCache(mock(GameProfileRepository.class),
                        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))));

        OptionSet options2 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager2 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice2 = new YggdrasilAuthenticationService(
                new Proxy(Proxy.Type.DIRECT, null), "foo");

        MinecraftSessionService minecraftsessionservice2 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository2 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver = new DedicatedServer(options2, dataconvertermanager2,
                yggdrasilauthenticationservice2, minecraftsessionservice2, gameprofilerepository2, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options3 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager3 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice3 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice3 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository3 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver1 = new DedicatedServer(options3, dataconvertermanager3,
                yggdrasilauthenticationservice3, minecraftsessionservice3, gameprofilerepository3, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager idatamanager = new ServerNBTManager(toFileResult, "foo", true, DataConverterRegistry.a());

        WorldData worlddata = new WorldData(new NBTTagCompound());
        MethodProfiler methodprofiler = new MethodProfiler();
        MinecraftServer minecraftServer = mock(MinecraftServer.class);
        IDataManager iDataManager = mock(IDataManager.class);
        WorldData worldData = mock(WorldData.class);
        WorldServer worldserver = new WorldServer(minecraftserver1, idatamanager, worlddata, 1, methodprofiler,
                World.Environment.NORMAL, new NetherChunkGenerator(
                new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler()), 42L));

        GameProfile gameprofile = new GameProfile(UUID.randomUUID(), "foo");

        OptionSet options4 = mock(OptionSet.class);
        DedicatedServer dedicatedServer = new DedicatedServer(options4, DataConverterRegistry.a(),
                mock(YggdrasilAuthenticationService.class), mock(MinecraftSessionService.class),
                mock(GameProfileRepository.class), mock(UserCache.class));

        File toFileResult1 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager serverNBTManager = new ServerNBTManager(toFileResult1, "foo", true, DataConverterRegistry.a());

        WorldData worldData1 = new WorldData(new NBTTagCompound());
        kingdomInfo.playerInKingdom(
                new CraftPlayer(server, new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler())))));
    }

    /**
     * Method under test: {@link KingdomInfo#playerInKingdom(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPlayerInKingdom2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).playerInKingdom(null);
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdom(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChunkInKingdom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).chunkInKingdom("Chunk");
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdom(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChunkInKingdom2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).chunkInKingdom(null);
    }

    /**
     * Method under test: {@link KingdomInfo#getPlayerKingdom(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPlayerKingdom() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name");
        OptionSet options = mock(OptionSet.class);
        DataConverterManager dataconvertermanager = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(
                new Proxy(Proxy.Type.DIRECT, null), "foo");

        MinecraftSessionService minecraftsessionservice = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository = mock(GameProfileRepository.class);
        DedicatedServer console = new DedicatedServer(options, dataconvertermanager, yggdrasilauthenticationservice,
                minecraftsessionservice, gameprofilerepository, new UserCache(mock(GameProfileRepository.class),
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options1 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager1 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice1 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice1 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository1 = mock(GameProfileRepository.class);
        CraftServer server = new CraftServer(console,
                new DedicatedPlayerList(new DedicatedServer(options1, dataconvertermanager1, yggdrasilauthenticationservice1,
                        minecraftsessionservice1, gameprofilerepository1, new UserCache(mock(GameProfileRepository.class),
                        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))));

        OptionSet options2 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager2 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice2 = new YggdrasilAuthenticationService(
                new Proxy(Proxy.Type.DIRECT, null), "foo");

        MinecraftSessionService minecraftsessionservice2 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository2 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver = new DedicatedServer(options2, dataconvertermanager2,
                yggdrasilauthenticationservice2, minecraftsessionservice2, gameprofilerepository2, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        OptionSet options3 = mock(OptionSet.class);
        DataConverterManager dataconvertermanager3 = DataConverterRegistry.a();
        YggdrasilAuthenticationService yggdrasilauthenticationservice3 = new YggdrasilAuthenticationService(null, "foo");

        MinecraftSessionService minecraftsessionservice3 = mock(MinecraftSessionService.class);
        GameProfileRepository gameprofilerepository3 = mock(GameProfileRepository.class);
        DedicatedServer minecraftserver1 = new DedicatedServer(options3, dataconvertermanager3,
                yggdrasilauthenticationservice3, minecraftsessionservice3, gameprofilerepository3, new UserCache(
                mock(GameProfileRepository.class), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));

        File toFileResult = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager idatamanager = new ServerNBTManager(toFileResult, "foo", true, DataConverterRegistry.a());

        WorldData worlddata = new WorldData(new NBTTagCompound());
        MethodProfiler methodprofiler = new MethodProfiler();
        MinecraftServer minecraftServer = mock(MinecraftServer.class);
        IDataManager iDataManager = mock(IDataManager.class);
        WorldData worldData = mock(WorldData.class);
        WorldServer worldserver = new WorldServer(minecraftserver1, idatamanager, worlddata, 1, methodprofiler,
                World.Environment.NORMAL, new NetherChunkGenerator(
                new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler()), 42L));

        GameProfile gameprofile = new GameProfile(UUID.randomUUID(), "foo");

        OptionSet options4 = mock(OptionSet.class);
        DedicatedServer dedicatedServer = new DedicatedServer(options4, DataConverterRegistry.a(),
                mock(YggdrasilAuthenticationService.class), mock(MinecraftSessionService.class),
                mock(GameProfileRepository.class), mock(UserCache.class));

        File toFileResult1 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
        ServerNBTManager serverNBTManager = new ServerNBTManager(toFileResult1, "foo", true, DataConverterRegistry.a());

        WorldData worldData1 = new WorldData(new NBTTagCompound());
        kingdomInfo.getPlayerKingdom(
                new CraftPlayer(server, new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler())))));
    }

    /**
     * Method under test: {@link KingdomInfo#getPlayerKingdom(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPlayerKingdom2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).getPlayerKingdom(null);
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChunkInKingdomCache() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).chunkInKingdomCache("Chunk");
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChunkInKingdomCache2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).chunkInKingdomCache(null);
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChunkInKingdomCache3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.chunkInKingdomCache(KingdomInfo.java:259)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).chunkInKingdomCache("Chunk");
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    void testChunkInKingdomCache4() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertFalse(kingdomInfo.chunkInKingdomCache("Chunk"));
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChunkInKingdomCache5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.chunkInKingdomCache(KingdomInfo.java:259)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.addBalance(2);
        kingdomInfo.chunkInKingdomCache("Chunk");
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    void testChunkInKingdomCache6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setTerritory(stringList);
        assertFalse(kingdomInfo.chunkInKingdomCache("Chunk"));
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    void testChunkInKingdomCache7() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> kingdomInfo.chunkInKingdomCache(null));
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    void testChunkInKingdomCache8() {
        KingdomInfo kingdomInfo = new KingdomInfo("notInTheKingdom", "_id", "Collection Name");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> kingdomInfo.chunkInKingdomCache(null));
    }

    /**
     * Method under test: {@link KingdomInfo#chunkInKingdomCache(String)}
     */
    @Test
    void testChunkInKingdomCache9() {
        KingdomInfo kingdomInfo = new KingdomInfo("notInTheKingdom", "_id", "chunkInKingdomCache");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> kingdomInfo.chunkInKingdomCache(null));
    }

    /**
     * Method under test: {@link KingdomInfo#takeTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeTerritory() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).takeTerritory("Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takeTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeTerritory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).takeTerritory("foo");
    }

    /**
     * Method under test: {@link KingdomInfo#takeTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeTerritory3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeTerritory(KingdomInfo.java:286)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).takeTerritory("Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takeTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeTerritory4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeTerritory(KingdomInfo.java:286)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "42", "Collection Name")).takeTerritory("Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takeTerritory(String)}
     */
    @Test
    void testTakeTerritory5() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.takeTerritory("Territory"));
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllTerritory() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).takeAllTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllTerritory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeAllTerritory(KingdomInfo.java:291)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).takeAllTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllTerritory3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeAllTerritory(KingdomInfo.java:291)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "members", "Collection Name")).takeAllTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllTerritory()}
     */
    @Test
    void testTakeAllTerritory4() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.takeAllTerritory());
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllTerritory()}
     */
    @Test
    void testTakeAllTerritory5() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "_id");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.takeAllTerritory());
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllTerritory()}
     */
    @Test
    void testTakeAllTerritory6() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setKingdomName("Kingdom Name");
        kingdomInfo.addBalance(2);
        kingdomInfo.setTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.takeAllTerritory());
    }

    /**
     * Method under test: {@link KingdomInfo#addTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddTerritory() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).addTerritory("Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#addTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddTerritory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).addTerritory("foo");
    }

    /**
     * Method under test: {@link KingdomInfo#addTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddTerritory3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).addTerritory("Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#addTerritory(String)}
     */
    @Test
    void testAddTerritory4() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.addTerritory("Territory"));
    }

    /**
     * Method under test: {@link KingdomInfo#addTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddTerritory5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.takeBalance(1);
        kingdomInfo.addTerritory("Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takePrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakePrivateTerritory() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).takePrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takePrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakePrivateTerritory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).takePrivateTerritory("foo");
    }

    /**
     * Method under test: {@link KingdomInfo#takePrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakePrivateTerritory3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takePrivateTerritory(KingdomInfo.java:305)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).takePrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takePrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakePrivateTerritory4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takePrivateTerritory(KingdomInfo.java:305)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setKingdomName("Kingdom Name");
        kingdomInfo.takePrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#takePrivateTerritory(String)}
     */
    @Test
    void testTakePrivateTerritory5() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setPrivateTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.takePrivateTerritory("Private Territory"));
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllPrivateTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllPrivateTerritory() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).takeAllPrivateTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllPrivateTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllPrivateTerritory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeAllPrivateTerritory(KingdomInfo.java:310)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).takeAllPrivateTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllPrivateTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllPrivateTerritory3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeAllPrivateTerritory(KingdomInfo.java:310)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "members", "Collection Name")).takeAllPrivateTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllPrivateTerritory()}
     */
    @Test
    void testTakeAllPrivateTerritory4() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setPrivateTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.takeAllPrivateTerritory());
    }

    /**
     * Method under test: {@link KingdomInfo#takeAllPrivateTerritory()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTakeAllPrivateTerritory5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.takeAllPrivateTerritory(KingdomInfo.java:310)
        //   See https://diff.blue/R013 to resolve this issue.

        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "members", "Collection Name");
        kingdomInfo.takeReputation(1);
        kingdomInfo.takeAllPrivateTerritory();
    }

    /**
     * Method under test: {@link KingdomInfo#addPrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPrivateTerritory() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).addPrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#addPrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPrivateTerritory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.KingdomInfo.<init>(KingdomInfo.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "Database Name", "Collection Name")).addPrivateTerritory("foo");
    }

    /**
     * Method under test: {@link KingdomInfo#addPrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPrivateTerritory3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.addPrivateTerritory(KingdomInfo.java:315)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "_id", "Collection Name")).addPrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#addPrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPrivateTerritory4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.addPrivateTerritory(KingdomInfo.java:315)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "42", "Collection Name")).addPrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#addPrivateTerritory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPrivateTerritory5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at feudal.data.database.KingdomInfo.addPrivateTerritory(KingdomInfo.java:315)
        //   See https://diff.blue/R013 to resolve this issue.

        (new KingdomInfo("Dr Jane Doe", "members", "Collection Name")).addPrivateTerritory("Private Territory");
    }

    /**
     * Method under test: {@link KingdomInfo#addPrivateTerritory(String)}
     */
    @Test
    void testAddPrivateTerritory6() {
        KingdomInfo kingdomInfo = new KingdomInfo("Dr Jane Doe", "_id", "Collection Name");
        kingdomInfo.setPrivateTerritory(new ArrayList<>());
        assertSame(kingdomInfo, kingdomInfo.addPrivateTerritory("Private Territory"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link KingdomInfo#setBalance(int)}
     *   <li>{@link KingdomInfo#setBarons(List)}
     *   <li>{@link KingdomInfo#setKing(String)}
     *   <li>{@link KingdomInfo#setKingdomName(String)}
     *   <li>{@link KingdomInfo#setMembers(List)}
     *   <li>{@link KingdomInfo#setPrivateTerritory(List)}
     *   <li>{@link KingdomInfo#setReputation(int)}
     *   <li>{@link KingdomInfo#setTerritory(List)}
     *   <li>{@link KingdomInfo#takeBalance(int)}
     *   <li>{@link KingdomInfo#takeReputation(int)}
     *   <li>{@link KingdomInfo#addBalance(int)}
     *   <li>{@link KingdomInfo#addReputation(int)}
     * </ul>
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetBalance() {
        // TODO: Complete this test.
        //   Reason: R081 Exception in arrange section.
        //   Diffblue Cover was unable to construct an instance of the class under test using
        //   feudal.data.database.KingdomInfo.<init>
        //   A step in the arrange section threw an exception:
        //   IllegalArgumentException state should be: databaseName does not contain ' '
        //   More information about the exception is provided in the support log.
        //   See https://diff.blue/R081 for further troubleshooting of this issue.

        // Arrange
        // TODO: Populate arranged inputs
        KingdomInfo kingdomInfo = null;
        int balance = 0;

        // Act
        KingdomInfo actualSetBalanceResult = kingdomInfo.setBalance(balance);
        List<String> barons = null;
        KingdomInfo actualSetBaronsResult = kingdomInfo.setBarons(barons);
        String king = "";
        KingdomInfo actualSetKingResult = kingdomInfo.setKing(king);
        String kingdomName = "";
        KingdomInfo actualSetKingdomNameResult = kingdomInfo.setKingdomName(kingdomName);
        List<String> members = null;
        KingdomInfo actualSetMembersResult = kingdomInfo.setMembers(members);
        List<String> privateTerritory = null;
        KingdomInfo actualSetPrivateTerritoryResult = kingdomInfo.setPrivateTerritory(privateTerritory);
        int reputation = 0;
        KingdomInfo actualSetReputationResult = kingdomInfo.setReputation(reputation);
        List<String> territory = null;
        KingdomInfo actualSetTerritoryResult = kingdomInfo.setTerritory(territory);
        int balance1 = 0;
        KingdomInfo actualTakeBalanceResult = kingdomInfo.takeBalance(balance1);
        int reputation1 = 0;
        KingdomInfo actualTakeReputationResult = kingdomInfo.takeReputation(reputation1);
        int balance2 = 0;
        KingdomInfo actualAddBalanceResult = kingdomInfo.addBalance(balance2);
        int reputation2 = 0;
        KingdomInfo actualAddReputationResult = kingdomInfo.addReputation(reputation2);

        // Assert
        // TODO: Add assertions on result
    }
}

