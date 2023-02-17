package feudal.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class TasksQueueUtils {
    private final List<Runnable> actions = new ArrayList<>();
    private int actionIndex = 0;
    private long sleepTicks = 0;
    private @Nullable BukkitTask task = null;

    public TasksQueueUtils action(Runnable runnable) {

        if (isRunning())
            throw new IllegalStateException("Unable to add actions to running " + getClass().getSimpleName());

        actions.add(runnable);

        return this;

    }

    public TasksQueueUtils sleepTicks(long ticks) {

        if (ticks <= 0)
            throw new IllegalArgumentException("Ticks must be > 0");

        return action(() -> sleepTicks = ticks);

    }

    public TasksQueueUtils sleep(long duration, TimeUnit timeUnit) {
        return sleepTicks(TimeUnit.MILLISECONDS.convert(duration, timeUnit) / 50);
    }

    public boolean isRunning() {
        return task != null;
    }

    public void startAsync(Plugin plugin) {
        start(plugin, true);
    }

    public void start(Plugin plugin) {
        start(plugin, false);
    }

    private void start(Plugin plugin, boolean async) {

        if (isRunning())
            throw new IllegalStateException(getClass().getSimpleName() + " started running");

        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        task = async
                ? scheduler.runTaskTimerAsynchronously(plugin, this::run, 1L, 1L)
                : scheduler.runTaskTimer(plugin, this::run, 1L, 1L);
    }

    public void stop() {

        if (!isRunning()) return;

        task.cancel();
        actionIndex = 0;
        sleepTicks = 0;
        task = null;

    }

    private void run() {

        while (sleepTicks == 0) {

            actions.get(actionIndex).run();
            actionIndex++;

            if (actionIndex == actions.size()) {

                stop();
                return;

            }

        }

        sleepTicks--;

    }
}