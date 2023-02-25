package feudal.commands;

import alterr.command.Command;
import alterr.command.paramter.Param;
import com.google.gson.Gson;
import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.utils.enums.professionEnums.ProfessionIDE;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.utils.wrappers.FlagWrapper;
import feudal.visual.ScoreBoardGeneralInfo;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static feudal.data.cache.CacheFeudalKingdoms.*;
import static feudal.data.cache.CacheFeudalPlayers.*;

public class KingdomCommands {

    private boolean confirmDeletion;

    @Command(names = {"f create", "create", "а скуфеу", "скуфеу"}, playerOnly = true, cooldown = 1800000)
    public void createKingdom(@NotNull Player player, @Param(name = "kingdomName") String kingdomName) {

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (!checkKingdomName(kingdomName)) {

            player.sendMessage("Нельзя создать королевство с таким именем!");
            return;

        } else if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы уже состоите в королевстве!");
            return;

        } else if (itemInHand == null || !itemInHand.getType().equals(Material.BANNER)) {

            player.sendMessage("Возьмите желаемый флаг королевства в руку!");
            return;

        }

        List<UUID> membersUUID = new ArrayList<>();
        membersUUID.add(player.getUniqueId());

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

    @Command(names = {"f flag", "flag", "а адфп", "адфп"}, playerOnly = true, cooldown = 10800000)
    public void flag(@NotNull Player player) {

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

    @Command(names = {"f removebaron", "removebaron", "а куьщмуифкщт", "куьщмуифкщт"}, playerOnly = true, cooldown = 30000)
    public void removeBaron(@NotNull Player player, @Param(name = "player") Player target) {

        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (target.getUniqueId().equals(player.getUniqueId())) {

            player.sendMessage("Вы не можете убрать барона с самого себя!");
            return;

        } else if (!hasKing(player)) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        } else if (!hasBaron(target)) {

            player.sendMessage("Игрок не назначен бароном!");
            return;

        }

        feudalKingdom.removeBaron(target);
        player.sendMessage("Игрок " + target.getName() + " снят с поста барона!");

    }

    @Command(names = {"f addbaron", "addbaron", "а фввифкщт", "фввифкщт"}, playerOnly = true, cooldown = 30000)
    public void addBaron(@NotNull Player player, @Param(name = "player") Player target) {

        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (target.getUniqueId().equals(player.getUniqueId())) {

            player.sendMessage("Вы не можете назначить самого себя!");
            return;

        } else if (!hasKing(player)) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getBaronsUUID().size() >= 1) {

            player.sendMessage("В королевстве максимальное количество баронов!");
            return;

        } else if (hasBaron(target)) {

            player.sendMessage("Игрок уже назначен бароном!");
            return;

        }

        feudalKingdom.addBaron(target);
        player.sendMessage("Игрок " + target.getName() + " назначен бароном!");

    }

    @Command(names = {"f claim", "claim", "а сдфшь", "сдфшь"}, playerOnly = true, cooldown = 30000)
    public void claim(Player player) {

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

        if (!checkChunk(player)) return;

        feudalKingdom.addTerritory(player.getLocation().getChunk());
        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(member) != null)
                Bukkit.getPlayer(member).sendMessage("Ваш король захватил новые земли!");

        });

    }

    @Command(names = {"f leave", "leave", "а дуфму", "дуфму"}, playerOnly = true, cooldown = 1800000)
    public void leave(@NotNull Player player) {

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

        ScoreBoardGeneralInfo.updateScoreBoardInfo(player);

    }

    @Command(names = {"f disband", "disband", "а вшыифтв", "вшыифтв"}, playerOnly = true, cooldown = 1800000)
    public void disband(Player player) {

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

            if (Bukkit.getPlayer(member) != null) {

                Bukkit.getPlayer(member).sendMessage("Ваше королевство расформировано!");
                ScoreBoardGeneralInfo.updateScoreBoardInfo(Bukkit.getPlayer(member));

            }

        });

        getKingdomInfo().remove(feudalPlayer.getKingdomName());

    }

    @Command(names = {"f reject", "reject", "а куоусе", "куоусе"}, playerOnly = true)
    public void reject(@NotNull Player player, @Param(name = "kingdomName") String kingdomName) {

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);

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

    @Command(names = {"f kick", "kick", "а лшсл", "лшсл"}, playerOnly = true, cooldown = 10000)
    public void kick(@NotNull Player player, @Param(name = "player") Player target) {

        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();

        FeudalKingdom feudalKingdom = getFeudalKingdom(kingdomName);

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (target.getUniqueId().equals(player.getUniqueId())) {

            player.sendMessage("Вы не можете кикнуть самого себя!");
            return;

        } else if (!hasKing(player)) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        } else if (!feudalKingdom.getMembersUUID().contains(target.getUniqueId())) {

            player.sendMessage("Игрок не состоит в вашем королевстве!");
            return;

        }

        feudalKingdom.removeMember(target);

        feudalKingdom.getMembersUUID().forEach(member -> {

            if (Bukkit.getPlayer(member) != null)
                Bukkit.getPlayer(member).sendMessage("Игрок " + target.getName() + " был кикнут из вашего королевства!");

        });

    }

    @Command(names = {"f invite", "invite", "а штмшеу", "штмшеу"}, playerOnly = true, cooldown = 10000)
    public void invite(@NotNull Player player, @Param(name = "player") Player target) {

        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (kingdomName.equals("") || feudalKingdom == null) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (target.getUniqueId().equals(player.getUniqueId())) {

            player.sendMessage("Вы не можете пригласить самого себя!");
            return;

        } else if (!feudalKingdom.getKingUUID().equals(player.getUniqueId())) {

            player.sendMessage("Вы не лидер королевства!");
            return;

        } else if (feudalKingdom.getMembersUUID().contains(target.getUniqueId())) {

            player.sendMessage("Игрок уже есть в вашем королевстве!");
            return;

        } else if (feudalKingdom.getMaxNumberMembers() <= (feudalKingdom.getMembersUUID().size() + 1)) {

            player.sendMessage("В ваше королевство нельзя пригласить больше участников!");
            return;

        } else if (feudalKingdom.getInvitationUUID().contains(target.getUniqueId())) {

            player.sendMessage("Приглашение уже отправлено этому игроку!");
            return;

        }

        FeudalPlayer feudalInvitedPlayer = getFeudalPlayer(target);

        if (!feudalInvitedPlayer.getKingdomName().equals("")) {

            player.sendMessage("Игрок уже состоит в королевстве!");
            return;

        }

        feudalKingdom.addInvitation(target);
        feudalInvitedPlayer.addInvitations(kingdomName);

        feudalInvitedPlayer.addLetter(player.getName(), "Игрок " +
                player.getName() +
                " приглашает вас вступить в королевство - " +
                kingdomName);

        player.sendMessage("Приглашение отправлено!");

    }

    @Command(names = {"f withdraw", "withdraw", "а цшервкфц", "цшервкфц"}, playerOnly = true, cooldown = 5000)
    public void withdraw(@NotNull Player player, @Param(name = "value") int value) {

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        String kingdomName = feudalPlayer.getKingdomName();

        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);


        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!hasKingdomPosition(player)) {

            player.sendMessage("Недостаточно прав для снятия золота с казны!");
            return;

        } else if (feudalPlayer.getBalance() < value) {

            player.sendMessage("Недостаточно золота!");
            return;

        }

        feudalKingdom.takeBalance(value + 5);
        getFeudalPlayer(player).takeBalance(value - value / 100 * 5);

    }

    @Command(names = {"f replenish", "replenish", "а куздутшыр", "куздутшыр"}, playerOnly = true, cooldown = 5000)
    public void replenish(@NotNull Player player, @Param(name = "value") int value) {

        FeudalPlayer feudalPlayer = getFeudalPlayer(player);
        String kingdomName = feudalPlayer.getKingdomName();

        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        if (exitsKingdom(kingdomName)) {

            player.sendMessage("Вы не состоите в королевстве!");
            return;

        } else if (!hasKingdomPosition(player)) {

            player.sendMessage("Недостаточно прав для пополнения казны!");
            return;

        } else if (feudalPlayer.getBalance() < value) {

            player.sendMessage("Недостаточно золота!");
            return;

        }

        feudalPlayer.takeBalance(value + 5);
        feudalKingdom.addBalance(value - value / 100 * 5);

    }

    private boolean checkChunk(@NotNull Player player) {

        String kingdomName = CacheFeudalPlayers.getFeudalPlayer(player).getKingdomName();
        FeudalKingdom feudalKingdom = getKingdomInfo().get(kingdomName);

        World world = Bukkit.getWorld(player.getWorld().getUID());
        Chunk playerChunk = world.getChunkAt(player.getLocation());

        if (feudalKingdom.getTerritory().contains(new ChunkWrapper(world.getName(), playerChunk.getX(), playerChunk.getZ()).hashCode())) {

            player.sendMessage("Этот чанк уже есть в вашем королевстве!");
            return false;

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
            return false;

        }

        return true;

    }

    private boolean checkKingdomName(@NotNull String kingdomName) {

        kingdomName.replaceAll("[^A-Za-zА-Яа-я0-9]", "");

        return kingdomName.length() <= 16 &&
                kingdomName.length() > 3 &&
                getKingdomInfo().get(kingdomName) == null;

    }

}
