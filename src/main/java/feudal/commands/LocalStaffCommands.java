package feudal.commands;

import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LocalStaffCommands implements CommandExecutor {

    private final Map<String, Method> commands = new HashMap<>();

    {

        try {

            commands.put("help", LocalStaffCommands.class.getDeclaredMethod("help", CommandSender.class, String[].class));
            commands.put("feudalplayerint", LocalStaffCommands.class.getDeclaredMethod("feudalPlayerInt", CommandSender.class, String[].class));
            commands.put("feudalplayerstr", LocalStaffCommands.class.getDeclaredMethod("feudalPlayerString", CommandSender.class, String[].class));
            commands.put("feudalplayer", LocalStaffCommands.class.getDeclaredMethod("feudalPlayer", CommandSender.class, String[].class));
            commands.put("feudalkingdomint", LocalStaffCommands.class.getDeclaredMethod("feudalKingdomInt", CommandSender.class, String[].class));
            commands.put("feudalkingdomstr", LocalStaffCommands.class.getDeclaredMethod("feudalKingdomString", CommandSender.class, String[].class));
            commands.put("feudalkingdom", LocalStaffCommands.class.getDeclaredMethod("feudalKingdom", CommandSender.class, String[].class));

        } catch (NoSuchMethodException e) {

            throw new RuntimeException(e);

        }

    }

    private static boolean isLs(CommandSender sender, String[] args) {
        return !(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls");
    }

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (isLs(sender, args)) return false;

        if (commands.containsKey(args[0].toLowerCase()))
            commands.get(args[0].toLowerCase()).invoke(sender, args);

        return false;

    }

    private void feudalPlayerInt(CommandSender sender, String @NotNull [] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args.length < 4) {

            player.sendMessage("Недостаточно аргументов!");
            return;

        } else if (Bukkit.getPlayerExact(args[1]) == null) {

            player.sendMessage("Игрок не найден!");
            return;

        } else if (args[3].length() > 10) {

            player.sendMessage("Слишком большое число!");
            return;

        }


        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
            feudalPlayer.getClass().getMethod(args[2], int.class).invoke(feudalPlayer, Integer.parseInt(args[3].replaceAll("[^0-9]", "")));

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalPlayerString(CommandSender sender, String @NotNull [] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args.length < 4) {

            player.sendMessage("Недостаточно аргументов!");
            return;

        } else if (Bukkit.getPlayerExact(args[1]) == null) {

            player.sendMessage("Игрок не найден!");
            return;

        }


        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(Bukkit.getPlayerExact(args[1]));
            feudalPlayer.getClass().getMethod(args[2], String.class).invoke(feudalPlayer, args[3]);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalPlayer(CommandSender sender, String @NotNull [] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args.length < 3) {

            player.sendMessage("Недостаточно аргументов!");
            return;

        } else if (Bukkit.getPlayerExact(args[1]) == null) {

            player.sendMessage("Игрок не найден!");
            return;

        }


        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
            feudalPlayer.getClass().getMethod(args[1]).invoke(feudalPlayer);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalKingdomInt(CommandSender sender, String @NotNull [] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args.length < 4) {

            player.sendMessage("Недостаточно аргументов!");
            return;

        } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

            player.sendMessage("Королевство не найдено!");
            return;

        } else if (args[3].length() > 10) {

            player.sendMessage("Слишком большое число!");
            return;

        }


        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
            feudalKingdom.getClass().getMethod(args[2], int.class).invoke(feudalKingdom, Integer.parseInt(args[3].replaceAll("[^0-9]", "")));

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalKingdomString(CommandSender sender, String @NotNull [] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args.length < 4) {

            player.sendMessage("Недостаточно аргументов!");
            return;

        } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

            player.sendMessage("Королевство не найдено!");
            return;

        }


        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
            feudalKingdom.getClass().getMethod(args[2], String.class).invoke(feudalKingdom, args[3]);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    @Contract("null, _, _, _ -> fail")
    private void feudalKingdom(CommandSender sender, String @NotNull [] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        if (args.length < 3) {

            player.sendMessage("Недостаточно аргументов!");
            return;

        } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

            player.sendMessage("Королевство не найдено!");
            return;

        }


        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(args[1]);
            feudalKingdom.getClass().getMethod(args[2]).invoke(feudalKingdom);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void help(CommandSender sender, String[] args) {

        assert sender instanceof Player;
        Player player = (Player) sender;

        player.sendMessage("/ls feudalplayerint [nick] [methodName] [int] \n (methodName: setExperience, setProfessionID, setBalance, setDeaths, setKills, setStrengthLvl, setSurvivabilityLvl, setSpeedLvl, setStaminaLvl, setLuckLvl, setProfessionLvl, setProfessionExperience, addExperience, addBalance, addDeaths, addKills, addStrengthLvl, addSurvivabilityLvl, addSpeedLvl, addStaminaLvl, addLuckLvl, addProfessionLvl, addProfessionExperience, takeExperience, takeBalance, takeDeaths, takeKills, takeStrengthLvl, takeSurvivabilityLvl, takeSpeedLvl, takeStaminaLvl, takeLuckLvl, takeProfessionLvl, takeProfessionExperience, addUpProfession, setUpProfession) \n" +
                " \n /ls feudalplayerstr [nick] [methodName] [str] \n (methodName: setKingdomName, addInvitations, deleteInvitations, deleteLetter) \n" +
                " \n /ls feudalplayer [nick] [methodName] \n (methodName: clearInvitations, clearLetters) \n" +
                " \n /ls feudalkingdomint [kingdomName] [methodName] [int] \n (methodName: setBalance, setReputation, addTerritory, addBalance, addReputation, takeTerritory, takeBalance, takeReputation, setMaxNumberMembers, addMaxNumberMembers)" +
                " \n /ls feudalkingdomstr [kingdomName] [methodName] [str] \n (methodName: setKingdomName)" +
                " \n /ls feudalkingdom [kingdomName] [methodName] \n (methodName: takeAllTerritory, clearInvitation)");
    }
}
