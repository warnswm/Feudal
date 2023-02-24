package feudal.commands;

import com.google.gson.Gson;
import feudal.data.Auction;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.RTPUtils;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.FlagWrapper;
import feudal.utils.wrappers.ItemStackWrapper;
import feudal.visual.Menus;
import feudal.visual.ScoreBoardGeneralInfo;
import lombok.SneakyThrows;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;

import static feudal.data.cache.CacheFeudalKingdoms.*;
import static feudal.data.cache.CacheFeudalPlayers.*;

public class PlayerCommands implements CommandExecutor {

    private boolean confirmDeletion;

    private final Map<String, Method> commands = new HashMap<>();

    {

        try {

            commands.put("create", PlayerCommands.class.getDeclaredMethod("createKingdom", CommandSender.class, String[].class));
            commands.put("up", PlayerCommands.class.getDeclaredMethod("up", CommandSender.class, String[].class));
            commands.put("withdraw", PlayerCommands.class.getDeclaredMethod("withdraw", CommandSender.class, String[].class));
            commands.put("replenish", PlayerCommands.class.getDeclaredMethod("replenish", CommandSender.class, String[].class));
            commands.put("invite", PlayerCommands.class.getDeclaredMethod("invite", CommandSender.class, String[].class));
            commands.put("kick", PlayerCommands.class.getDeclaredMethod("kick", CommandSender.class, String[].class));
            commands.put("sell", PlayerCommands.class.getDeclaredMethod("sell", CommandSender.class, String[].class));
            commands.put("reject", PlayerCommands.class.getDeclaredMethod("reject", CommandSender.class, String[].class));
            commands.put("disband", PlayerCommands.class.getDeclaredMethod("disband", CommandSender.class, String[].class));
            commands.put("leave", PlayerCommands.class.getDeclaredMethod("leave", CommandSender.class, String[].class));
            commands.put("ah", PlayerCommands.class.getDeclaredMethod("ah", CommandSender.class, String[].class));
            commands.put("claim", PlayerCommands.class.getDeclaredMethod("claim", CommandSender.class, String[].class));
            commands.put("addbaron", PlayerCommands.class.getDeclaredMethod("addBaron", CommandSender.class, String[].class));
            commands.put("removebaron", PlayerCommands.class.getDeclaredMethod("removeBaron", CommandSender.class, String[].class));
            commands.put("help", PlayerCommands.class.getDeclaredMethod("help", CommandSender.class, String[].class));
            commands.put("prof", PlayerCommands.class.getDeclaredMethod("prof", CommandSender.class, String[].class));
            commands.put("rtp", PlayerCommands.class.getDeclaredMethod("rtp", CommandSender.class, String[].class));
            commands.put("mail", PlayerCommands.class.getDeclaredMethod("mail", CommandSender.class, String[].class));

        } catch (NoSuchMethodException e) {

            throw new RuntimeException(e);

        }

    }


    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (!commands.containsKey(args[0].toLowerCase())) {

            sender.sendMessage("Команда не найдена! /f help - дотсупные команды");
            return false;

        }

        commands.get(args[0].toLowerCase()).invoke(sender, args);

        return false;

    }

    private void createKingdom(CommandSender sender, String @NotNull [] args) {

        Player player = (Player) sender;

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (args.length < 2) {

            player.sendMessage("Пожалуйста укажите желаемое имя королевства!");
            return;

        } else if (!checkKingdomName(args[1])) {

            player.sendMessage("Нельзя создать королевство с таким именем!");
            return;

        } else if (exitsKingdom(args[1])) {

            player.sendMessage("Вы уже состоите в королевстве!");
            return;

        } else if (itemInHand == null || !itemInHand.getType().equals(Material.BANNER)) {

            player.sendMessage("Возьмите желаемый флаг королевства в руку!");
            return;

        }

        List<UUID> membersUUID = new ArrayList<>();
        membersUUID.add(player.getUniqueId());

        String kingdomName = args[1];
        String flagGson = new Gson().toJson(new FlagWrapper("Флаг королевства - " + kingdomName, ((BannerMeta) itemInHand.getItemMeta()).getPatterns()));

        KingdomDBHandler.createNewKingdom(kingdomName, player, membersUUID, new ArrayList<>(), new ArrayList<>(), flagGson);

        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);
        feudalKingdom.setKingdomName(kingdomName)
                .setKing(player.getUniqueId())
                .setBalance(10000)
                .setReputation(1000)
                .setMembers(membersUUID)
                .setMaxNumberMembers(5)
                .setBarons(new ArrayList<>())
                .setTerritory(new ArrayList<>())
                .setFlagGson(flagGson);

        getKingdomInfo().put(kingdomName, feudalKingdom);

        feudalPlayer.setKingdomName(kingdomName);
        player.sendMessage("Вы успешно создали королевство: " + kingdomName);

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);

    }

    private void up(CommandSender sender, String[] args) {

        new Menus((Player) sender).upgradeProfessionMenu();

    }

    private void withdraw(CommandSender sender, String @NotNull [] args) {

        Player player = (Player) sender;

        if (args.length < 2) {

            player.sendMessage("Пожалуйста укажите сумму!");
            return;

        } else if (args[1].length() > 10) {

            player.sendMessage("Слишком большая сумма для пополнения");
            return;

        }

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        String kingdomName = feudalPlayer.getKingdomName();

        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        int colum = Integer.parseInt(args[1].replaceAll("[^0-9]", ""));


        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!hasKingdomPosition(player)) {

            player.sendMessage("Недостаточно прав для снятия золота с казны!");
            return;

        } else if (feudalPlayer.getBalance() < colum) {

            player.sendMessage("Недостаточно золота!");
            return;

        }

        feudalKingdom.takeBalance(colum + 5);
        getFeudalPlayer(player).takeBalance(colum - colum / 100 * 5);

    }

    private void replenish(CommandSender sender, String @NotNull [] args) {

        Player player = (Player) sender;

        if (args.length < 2) {

            player.sendMessage("Пожалуйста укажите сумму!");
            return;

        } else if (args[1].length() > 10) {

            player.sendMessage("Слишком большая сумма для пополнения");
            return;

        }

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        String kingdomName = feudalPlayer.getKingdomName();

        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        int colum = Integer.parseInt(args[1].replaceAll("[^0-9]", ""));

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!hasKingdomPosition(player)) {

            player.sendMessage("Недостаточно прав для пополнения казны!");
            return;

        } else if (feudalPlayer.getBalance() < colum) {

            player.sendMessage("Недостаточно золота!");
            return;

        }

        feudalPlayer.takeBalance(colum + 5);
        feudalKingdom.addBalance(colum - colum / 100 * 5);

    }

    private void invite(CommandSender sender, String @NotNull [] args) {

        Player playerInviting = (Player) sender;
        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(playerInviting).getKingdomName();

        Player invitedPlayer = Bukkit.getPlayerExact(args[1]);
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (invitedPlayer == null || !invitedPlayer.isOnline() || invitedPlayer.getGameMode().equals(GameMode.SPECTATOR)) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (invitedPlayer == playerInviting) {

            playerInviting.sendMessage("Вы не можете пригласить самого себя!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(playerInviting.getUniqueId())) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getMembersUUID().contains(invitedPlayer.getUniqueId())) {

            playerInviting.sendMessage("Игрок уже есть в вашем королевстве!");
            return;

        } else if (feudalKingdom.getMaxNumberMembers() <= (feudalKingdom.getMembersUUID().size() + 1)) {

            playerInviting.sendMessage("В ваше королевство нельзя пригласить больше участников!");
            return;

        } else if (feudalKingdom.getInvitationUUID().contains(invitedPlayer.getUniqueId())) {

            playerInviting.sendMessage("Приглашение уже отправлено этому игроку!");
            return;

        }

        FeudalPlayer feudalInvitedPlayer = getFeudalPlayer(invitedPlayer);

        if (!feudalInvitedPlayer.getKingdomName().equals("")) {

            playerInviting.sendMessage("Игрок уже состоит в королевстве!");
            return;

        }

        feudalKingdom.addInvitation(invitedPlayer);
        feudalInvitedPlayer.addInvitations(kingdomName);

        feudalInvitedPlayer.addLetter(playerInviting.getName(), "Игрок " +
                playerInviting.getName() +
                " приглашает вас вступить в королевство - " +
                kingdomName);

        playerInviting.sendMessage("Приглашение отправлено!");

    }

    private void kick(CommandSender sender, String @NotNull [] args) {

        Player playerInviting = (Player) sender;
        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(playerInviting).getKingdomName();

        Player invitedPlayer = Bukkit.getPlayerExact(args[1]);
        FeudalKingdom feudalKingdom = getFeudalKingdom(kingdomName);

        if (exitsKingdom(kingdomName)) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (invitedPlayer == playerInviting) {

            playerInviting.sendMessage("Вы не можете кикнуть самого себя!");
            return;

        } else if (!hasKing(playerInviting)) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (!feudalKingdom.getMembersUUID().contains(invitedPlayer.getUniqueId())) {

            playerInviting.sendMessage("Игрок не состоит в вашем королевстве!");
            return;

        }

        feudalKingdom.removeMember(playerInviting);

        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(member) != null)
                Bukkit.getPlayer(member).sendMessage("Игрок " + invitedPlayer.getName() + " был кикнут из вашего королевства!");

        });

    }

    private void sell(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (player.getInventory().getItemInMainHand() == null) {

            player.sendMessage("Возьмите предмет в основную руку!");
            return;

        } else if (args.length < 2) {

            player.sendMessage("Укажите сумму!");
            return;

        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Auction.getProducts().add(new ItemStackWrapper(itemStack.getType(), itemStack.getDurability(), itemStack.getAmount(), itemStack.getItemMeta().getDisplayName(), itemStack.getItemMeta().getLore(), itemStack.getEnchantments(), Integer.parseInt(args[1].replaceAll("[^0-9]", ""))));

    }

    private void reject(CommandSender sender, String @NotNull [] args) {

        Player player = (Player) sender;
        FeudalPlayer feudalPlayer = getFeudalPlayer(player);

        String kingdomName = args[1];

        if (!feudalPlayer.getInvitations().contains(kingdomName)) {

            player.sendMessage("Вы не были приглашены этим королевством! Или время приглашения истекло.");
            return;

        }

        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        feudalKingdom.deleteInvitation(player);
        feudalPlayer.deleteInvitations(kingdomName);

        Bukkit.getPlayer(feudalKingdom.getKingUUID()).sendMessage("Игрок " +
                player.getDisplayName() +
                ", отклонил ваше приглашение.");
        player.sendMessage("Вы отклонили приграшение в королевство: " +
                kingdomName);

    }

    private void disband(CommandSender sender, String @NotNull [] args) {

        Player player = (Player) sender;
        FeudalPlayer feudalPlayer = getFeudalPlayer(player);

        if (hasKing(player)) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        }

        confirmDeletion = !confirmDeletion;
        if (confirmDeletion) {

            player.sendMessage("Напишите ещё раз, чтобы подтвердить расформирование королевства");
            return;

        }

        KingdomDBHandler.deleteKingdom(feudalPlayer.getKingdomName());

        getKingdomInfo().get(feudalPlayer.getKingdomName()).getMembersUUID().forEach(member -> {

            FeudalPlayer feudalMember = getFeudalPlayer(Bukkit.getPlayer(member));
            feudalMember.setKingdomName("");

            if (Bukkit.getPlayer(member) != null)
                Bukkit.getPlayer(member).sendMessage("Ваше королевство расформировано!");

        });

        getKingdomInfo().remove(feudalPlayer.getKingdomName());

    }

    private void leave(CommandSender sender, String @NotNull [] args) {

        Player player = (Player) sender;
        FeudalPlayer feudalPlayer = getFeudalPlayer(player);

        String kingdomName = feudalPlayer.getKingdomName();
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        }

        feudalKingdom.getMembersUUID().remove(player.getUniqueId());
        feudalPlayer.setKingdomName("");

        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(member) != null)
                Bukkit.getPlayer(member).sendMessage("Игрок " + player.getName() + " покинул ваше королевство!");

        });
        player.sendMessage("Вы покинули королевство " + kingdomName);

    }

    private void ah(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        if (feudalPlayer.getProfessionID() != ProfessionIDE.TRADER.getId()) {

            player.sendMessage("Вы не торговец!");
            return;

        }

        new Menus(player).auctionMenu(1);

    }

    private void claim(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();

        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!hasKing(player)) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getTerritory().size() >= feudalKingdom.getMembersUUID().stream().map(member ->
                getFeudalPlayer(Bukkit.getPlayer(member))).mapToInt(feudalPlayer ->
                ProfessionIDE.getContainsLandBYID(feudalPlayer.getProfessionID())).sum()) {

            player.sendMessage("Максимальное количество земель в королевстве!");
            return;

        }


        World world = Bukkit.getWorld(player.getWorld().getUID());
        Chunk playerChunk = world.getChunkAt(player.getLocation());

        if (feudalKingdom.getTerritory().contains(new ChunkWrapper(world.getName(), playerChunk.getX(), playerChunk.getZ()).hashCode())) {

            player.sendMessage("Этот чанк уже есть в вашем королевстве!");
            return;

        }
        boolean canCapture = feudalKingdom.getTerritory().isEmpty();

        Location firstLocation = new Location(world, playerChunk.getX() - 1, 64, playerChunk.getZ() - 1);
        Location secondLocation = new Location(world, playerChunk.getX() + 1, 64, playerChunk.getZ() + 1);

        for (int x = firstLocation.getBlockX(); x <= secondLocation.getBlockX(); x++)
            for (int z = firstLocation.getBlockZ(); z <= secondLocation.getBlockZ(); z++) {

                if (feudalKingdom.getTerritory().contains(new ChunkWrapper(world.getName(), x, z).hashCode()))
                    canCapture = true;

            }

        if (!canCapture) {

            player.sendMessage("Этот чанк нельзя захватить!");
            return;

        }

        feudalKingdom.addTerritory(world.getChunkAt(player.getLocation()));
        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(member) != null)
                Bukkit.getPlayer(member).sendMessage("Ваш король захватил новые земли!");

        });

    }

    private void addBaron(CommandSender sender, String @NotNull [] args) {

        Player playerInviting = (Player) sender;
        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(playerInviting).getKingdomName();
        String nick = args[1];

        Player baron = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!exitsPlayer(nick)) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (baron == playerInviting) {

            playerInviting.sendMessage("Вы не можете назначить самого себя!");
            return;

        } else if (!hasKing(playerInviting)) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getBaronsUUID().size() == 1) {

            playerInviting.sendMessage("В королевстве максимальное количество баронов!");
            return;

        } else if (hasBaron(playerInviting)) {

            playerInviting.sendMessage("Игрок уже назначен бароном!");
            return;

        }

        feudalKingdom.addBaron(baron);
        playerInviting.sendMessage("Игрок " + baron.getName() + " назначен бароном!");

    }

    private void removeBaron(CommandSender sender, String @NotNull [] args) {

        Player playerInviting = (Player) sender;
        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(playerInviting).getKingdomName();
        String nick = args[1];

        Player baron = Bukkit.getPlayerExact(nick);
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            playerInviting.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!exitsPlayer(nick)) {

            playerInviting.sendMessage("Игрок не найден на сервере!");
            return;

        } else if (baron == playerInviting) {

            playerInviting.sendMessage("Вы не можете убрать барона с самого себя!");
            return;

        } else if (!hasKing(playerInviting)) {

            playerInviting.sendMessage("Вы не лидер королевства!");
            return;

        } else if (!hasBaron(playerInviting)) {

            playerInviting.sendMessage("Игрок не назначен бароном!");
            return;

        }

        feudalKingdom.removeBaron(baron);
        playerInviting.sendMessage("Игрок " + baron.getName() + " снят с поста барона!");

    }

    private void flag(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (hasKingdomPosition(player)) {

            player.sendMessage("Вы не занимаете должность в королевстве!");
            return;

        }

        player.getInventory().addItem(new Gson().fromJson(getKingdomInfo().get(kingdomName).getFlagGson(), FlagWrapper.class).create());

    }

    private void help(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        player.sendMessage("/f claim - захватить чанк\n"
                + "/f create - создать королевство\n" +
                "/f help - меню команд\n" +
                "/f invite - добавить игрока в королевство\n" +
                "/f kick - удалить игрока из королевства\n" +
                "/f ah - открыть аукцион\n");

    }

    private void prof(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (!hasProfession(player)) {

            player.sendMessage("У вас уже есть профессия!");
            return;

        }

        new Menus(player).professionSelectionMenu();

    }

    private void rtp(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        player.teleport(RTPUtils.rtpCalc(player, -25000, 25000, -25000, 25000));

    }

    private void mail(CommandSender sender, String[] args) {
        new Menus((Player) sender).mailMenu();
    }

    private boolean checkKingdomName(@NotNull String kingdomName) {

        kingdomName.replaceAll("[^A-Za-zА-Яа-я0-9]", "");

        return kingdomName.length() <= 16 &&
                kingdomName.length() > 3 &&
                getKingdomInfo().get(kingdomName) == null;

    }

}
