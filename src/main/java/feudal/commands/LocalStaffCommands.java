package feudal.commands;

import feudal.data.FeudalKingdom;
import feudal.data.FeudalPlayer;
import feudal.data.cache.CacheFeudalKingdoms;
import feudal.data.cache.CacheFeudalPlayers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class LocalStaffCommands implements CommandExecutor {
    private static boolean isLs(CommandSender sender, String[] args) {
        return !(sender instanceof Player) && !sender.hasPermission("feudal.ls") && !args[0].equals("ls");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (isLs(sender, args)) return false;

        assert sender instanceof Player;
        Player player = (Player) sender;

        switch (args[0].toLowerCase()) {

            case "help":

                player.sendMessage("/ls feudalplayerint [nick] [methodName] [int] \n (methodName: setExperience, setProfessionID, setBalance, setDeaths, setKills, setStrengthLvl, setSurvivabilityLvl, setSpeedLvl, setStaminaLvl, setLuckLvl, setProfessionLvl, setProfessionExperience, addExperience, addBalance, addDeaths, addKills, addStrengthLvl, addSurvivabilityLvl, addSpeedLvl, addStaminaLvl, addLuckLvl, addProfessionLvl, addProfessionExperience, takeExperience, takeBalance, takeDeaths, takeKills, takeStrengthLvl, takeSurvivabilityLvl, takeSpeedLvl, takeStaminaLvl, takeLuckLvl, takeProfessionLvl, takeProfessionExperience, addUpProfession, setUpProfession) \n" +
                        " \n /ls feudalplayerstr [nick] [methodName] [str] \n (methodName: setKingdomName, addInvitations, deleteInvitations, deleteLetter) \n" +
                        " \n /ls feudalplayer [nick] [methodName] \n (methodName: clearInvitations, clearLetters) \n" +
                        " \n /ls feudalkingdomint [kingdomName] [methodName] [int] \n (methodName: setBalance, setReputation, addTerritory, addBalance, addReputation, takeTerritory, takePrivateTerritory, takeBalance, takeReputation, setMaxNumberMembers, addMaxNumberMembers)" +
                        " \n /ls feudalkingdomstr [kingdomName] [methodName] [str] \n (methodName: setKingdomName)" +
                        " \n /ls feudalkingdom [kingdomName] [methodName] \n (methodName: takeAllTerritory, takeAllPrivateTerritory, clearInvitation)");

                break;

            case "feudalplayerint":


                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                } else if (args[3].length() > 10) {

                    player.sendMessage("Слишком большое число!");
                    break;

                }

                feudalPlayerInt(player, Bukkit.getPlayerExact(args[1]), args[2], Integer.parseInt(args[3]));

                break;

            case "feudalplayerstr":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                }

                feudalPlayerString(player, Bukkit.getPlayerExact(args[1]), args[2], args[3]);

                break;


            case "feudalplayer":

                if (args.length < 3) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (Bukkit.getPlayerExact(args[1]) == null) {

                    player.sendMessage("Игрок не найден!");
                    break;

                }

                feudalPlayer(player, Bukkit.getPlayerExact(args[1]), args[2]);

                break;

            case "feudalkingdomint":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

                    player.sendMessage("Королевство не найдено!");
                    break;

                } else if (args[3].length() > 10) {

                    player.sendMessage("Слишком большое число!");
                    break;

                }

                feudalKingdomInt(player, args[1], args[2], Integer.parseInt(args[3]));

                break;

            case "feudalkingdomstr":

                if (args.length < 4) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

                    player.sendMessage("Королевство не найдено!");
                    break;

                }

                feudalKingdomString(player, args[1], args[2], args[3]);

                break;

            case "feudalkingdom":

                if (args.length < 3) {

                    player.sendMessage("Недостаточно аргументов!");
                    break;

                } else if (CacheFeudalKingdoms.getKingdomInfo().get(args[1]) == null) {

                    player.sendMessage("Королевство не найдено!");
                    break;

                }

                feudalKingdom(player, args[1], args[2]);

                break;


            default:
                player.sendMessage("Неизвестная команда!");

        }

        return false;

    }

    private void feudalPlayerInt(@NotNull Player sender, Player player, String methodName, int value) {

        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
            feudalPlayer.getClass().getMethod(methodName, int.class).invoke(feudalPlayer, value);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalPlayerString(@NotNull Player sender, Player player, String methodName, String value) {

        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
            feudalPlayer.getClass().getMethod(methodName, String.class).invoke(feudalPlayer, value);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalPlayer(@NotNull Player sender, Player player, String methodName) {

        try {

            FeudalPlayer feudalPlayer = CacheFeudalPlayers.getFeudalPlayer(player);
            feudalPlayer.getClass().getMethod(methodName).invoke(feudalPlayer);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalKingdomInt(@NotNull Player sender, String kingdomName, String methodName, int value) {

        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);
            feudalKingdom.getClass().getMethod(methodName, int.class).invoke(feudalKingdom, value);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalKingdomString(@NotNull Player sender, String kingdomName, String methodName, String value) {

        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);
            feudalKingdom.getClass().getMethod(methodName, String.class).invoke(feudalKingdom, value);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }

    private void feudalKingdom(@NotNull Player sender, String kingdomName, String methodName) {

        try {

            FeudalKingdom feudalKingdom = CacheFeudalKingdoms.getKingdomInfo().get(kingdomName);
            feudalKingdom.getClass().getMethod(methodName).invoke(feudalKingdom);

            sender.sendMessage("Метод был выполнен!");

        } catch (NoSuchMethodException e) {

            sender.sendMessage("Метод не найден!");

        } catch (InvocationTargetException | IllegalAccessException e) {

            sender.sendMessage("Не верные агрументы!");

        }

    }
}
