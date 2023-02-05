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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PlayerInfoTest {
    /**
     * Method under test: {@link PlayerInfo#PlayerInfo(String, String, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");

    }

    /**
     * Method under test: {@link PlayerInfo#PlayerInfo(String, String, String)}
     */
    @Test
    void testConstructor2() {
        MongoClient mongoClient = (new PlayerInfo("foo", "foo", "foo")).getMongoClient();
        assertTrue(mongoClient instanceof MongoClientImpl);
        assertTrue(((MongoClientImpl) mongoClient).getCluster() instanceof SingleServerCluster);
        assertTrue(((MongoClientImpl) mongoClient).getCodecRegistry() instanceof ProvidersCodecRegistry);
        ClusterDescription clusterDescription = mongoClient.getClusterDescription();
        assertEquals(ClusterType.UNKNOWN, clusterDescription.getType());
        assertNull(clusterDescription.getSrvResolutionException());
        assertEquals(1, clusterDescription.getServerDescriptions().size());
        assertNull(clusterDescription.findServerIncompatiblyOlderThanDriver());
        assertEquals(ClusterConnectionMode.SINGLE, clusterDescription.getConnectionMode());
        ClusterSettings clusterSettings = clusterDescription.getClusterSettings();
        assertEquals(ClusterConnectionMode.SINGLE, clusterSettings.getMode());
        assertEquals(500, clusterSettings.getMaxWaitQueueSize());
        assertNull(clusterSettings.getDescription());
        assertNull(clusterSettings.getRequiredReplicaSetName());
        assertTrue(clusterSettings.getServerSelector() instanceof LatencyMinimizingServerSelector);
        assertEquals(ClusterType.UNKNOWN, clusterSettings.getRequiredClusterType());
        assertEquals("{hosts=[foo:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms',"
                + " maxWaitQueueSize=500}", clusterSettings.getShortDescription());
    }

    /**
     * Method under test: {@link PlayerInfo#PlayerInfo(String, String, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        new PlayerInfo("mongodb://", "Database Name", "Collection Name");

    }

    /**
     * Method under test: {@link PlayerInfo#PlayerInfo(String, String, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        new PlayerInfo("", "Database Name", "Collection Name");

    }

    /**
     * Method under test: {@link PlayerInfo#PlayerInfo(String, String, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        new PlayerInfo("Dr Jane Doe", "", "Collection Name");

    }

    /**
     * Method under test: {@link PlayerInfo#PlayerInfo(String, String, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        new PlayerInfo("foo", "foo", "");

    }

    /**
     * Method under test: {@link PlayerInfo#createNewPlayer(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewPlayer() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo.createNewPlayer(
                new CraftPlayer(server, new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler())))));
    }

    /**
     * Method under test: {@link PlayerInfo#createNewPlayer(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewPlayer2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo.createNewPlayer(
                new CraftPlayer(server, new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler())))));
    }

    /**
     * Method under test: {@link PlayerInfo#hasPlayer(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHasPlayer() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo.hasPlayer(
                new CraftPlayer(server, new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler())))));
    }

    /**
     * Method under test: {@link PlayerInfo#hasPlayer(Player)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHasPlayer2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: state should be: databaseName does not contain ' '
        //       at com.mongodb.MongoNamespace.checkDatabaseNameValidity(MongoNamespace.java:62)
        //       at com.mongodb.client.internal.MongoDatabaseImpl.<init>(MongoDatabaseImpl.java:74)
        //       at com.mongodb.client.internal.MongoClientImpl.getDatabase(MongoClientImpl.java:80)
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo.hasPlayer(
                new CraftPlayer(server, new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                        new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler())))));
    }

    /**
     * Method under test: {@link PlayerInfo#getField(Player, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo
                .getField(
                        new CraftPlayer(server,
                                new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler())))),
                        "Field Name");
    }

    /**
     * Method under test: {@link PlayerInfo#getField(Player, String)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo
                .getField(
                        new CraftPlayer(server,
                                new EntityPlayer(minecraftserver, worldserver, gameprofile,
                                        new PlayerInteractManager(
                                                new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler())))),
                        "foo");
    }

    /**
     * Method under test: {@link PlayerInfo#setField(Player, String, Object)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo
                .setField(
                        new CraftPlayer(server,
                                new EntityPlayer(minecraftserver, worldserver, gameprofile, new PlayerInteractManager(
                                        new DemoWorldServer(dedicatedServer, serverNBTManager, worldData1, 1, new MethodProfiler())))),
                        "Field Name", "Value");
    }

    /**
     * Method under test: {@link PlayerInfo#setField(Player, String, Object)}
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
        //       at feudal.data.database.PlayerInfo.<init>(PlayerInfo.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        PlayerInfo playerInfo = new PlayerInfo("Dr Jane Doe", "Database Name", "Collection Name");
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
        playerInfo.setField(
                new CraftPlayer(server,
                        new EntityPlayer(minecraftserver, worldserver, gameprofile,
                                new PlayerInteractManager(
                                        new DemoWorldServer(minecraftServer, iDataManager, worldData, 1, new MethodProfiler())))),
                "foo", "42");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PlayerInfo#setBalance(int)}
     *   <li>{@link PlayerInfo#setDeaths(int)}
     *   <li>{@link PlayerInfo#setExperience(int)}
     *   <li>{@link PlayerInfo#setGameClassExperience(int)}
     *   <li>{@link PlayerInfo#setGameClassLvl(int)}
     *   <li>{@link PlayerInfo#setKills(int)}
     *   <li>{@link PlayerInfo#setKingdomName(String)}
     *   <li>{@link PlayerInfo#setLuckLvl(int)}
     *   <li>{@link PlayerInfo#setPlayer(Player)}
     *   <li>{@link PlayerInfo#setSpeedLvl(int)}
     *   <li>{@link PlayerInfo#setStaminaLvl(int)}
     *   <li>{@link PlayerInfo#setStrengthLvl(int)}
     *   <li>{@link PlayerInfo#setSurvivabilityLvl(int)}
     *   <li>{@link PlayerInfo#setaClassID(int)}
     *   <li>{@link PlayerInfo#takeBalance(int)}
     *   <li>{@link PlayerInfo#addBalance(int)}
     *   <li>{@link PlayerInfo#addDeaths(int)}
     *   <li>{@link PlayerInfo#addExperience(int)}
     *   <li>{@link PlayerInfo#addGameClassExperience(int)}
     *   <li>{@link PlayerInfo#addGameClassLvl(int)}
     *   <li>{@link PlayerInfo#addKills(int)}
     *   <li>{@link PlayerInfo#addLuckLvl(int)}
     *   <li>{@link PlayerInfo#addSpeedLvl(int)}
     *   <li>{@link PlayerInfo#addStaminaLvl(int)}
     *   <li>{@link PlayerInfo#addStrengthLvl(int)}
     *   <li>{@link PlayerInfo#addSurvivabilityLvl(int)}
     * </ul>
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetBalance() {
        // TODO: Complete this test.
        //   Reason: R081 Exception in arrange section.
        //   Diffblue Cover was unable to construct an instance of the class under test using
        //   feudal.data.database.PlayerInfo.<init>
        //   A step in the arrange section threw an exception:
        //   IllegalArgumentException state should be: databaseName does not contain ' '
        //   More information about the exception is provided in the support log.
        //   See https://diff.blue/R081 for further troubleshooting of this issue.

        // Arrange
        // TODO: Populate arranged inputs
        PlayerInfo playerInfo = null;
        int balance = 0;

        // Act
        PlayerInfo actualSetBalanceResult = playerInfo.setBalance(balance);
        int deaths = 0;
        PlayerInfo actualSetDeathsResult = playerInfo.setDeaths(deaths);
        int experience = 0;
        PlayerInfo actualSetExperienceResult = playerInfo.setExperience(experience);
        int gameClassExperience = 0;
        PlayerInfo actualSetGameClassExperienceResult = playerInfo.setGameClassExperience(gameClassExperience);
        int gameClassLvl = 0;
        PlayerInfo actualSetGameClassLvlResult = playerInfo.setGameClassLvl(gameClassLvl);
        int kills = 0;
        PlayerInfo actualSetKillsResult = playerInfo.setKills(kills);
        String kingdomName = "";
        PlayerInfo actualSetKingdomNameResult = playerInfo.setKingdomName(kingdomName);
        int luckLvl = 0;
        PlayerInfo actualSetLuckLvlResult = playerInfo.setLuckLvl(luckLvl);
        Player player = null;
        PlayerInfo actualSetPlayerResult = playerInfo.setPlayer(player);
        int speedLvl = 0;
        PlayerInfo actualSetSpeedLvlResult = playerInfo.setSpeedLvl(speedLvl);
        int staminaLvl = 0;
        PlayerInfo actualSetStaminaLvlResult = playerInfo.setStaminaLvl(staminaLvl);
        int strengthLvl = 0;
        PlayerInfo actualSetStrengthLvlResult = playerInfo.setStrengthLvl(strengthLvl);
        int survivabilityLvl = 0;
        PlayerInfo actualSetSurvivabilityLvlResult = playerInfo.setSurvivabilityLvl(survivabilityLvl);
        int aClassID = 0;
        PlayerInfo actualSetaClassIDResult = playerInfo.setaClassID(aClassID);
        int value = 0;
        playerInfo.takeBalance(value);
        int value1 = 0;
        playerInfo.addBalance(value1);
        int value2 = 0;
        playerInfo.addDeaths(value2);
        int value3 = 0;
        playerInfo.addExperience(value3);
        int value4 = 0;
        playerInfo.addGameClassExperience(value4);
        int value5 = 0;
        playerInfo.addGameClassLvl(value5);
        int value6 = 0;
        playerInfo.addKills(value6);
        int value7 = 0;
        playerInfo.addLuckLvl(value7);
        int value8 = 0;
        playerInfo.addSpeedLvl(value8);
        int value9 = 0;
        playerInfo.addStaminaLvl(value9);
        int value10 = 0;
        playerInfo.addStrengthLvl(value10);
        int value11 = 0;
        playerInfo.addSurvivabilityLvl(value11);

        // Assert
        // TODO: Add assertions on result
    }
}

