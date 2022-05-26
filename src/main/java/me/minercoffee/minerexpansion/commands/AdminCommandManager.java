package me.minercoffee.minerexpansion.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AdminCommandManager implements TabExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public AdminCommandManager(){
        subCommands.add(new elytracmd(MinerExpansion.getPlugin()));
        subCommands.add(new UnFreeze());
        subCommands.add(new Freeze());
        subCommands.add(new deposit());
        subCommands.add(new withdraw());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                for (int i = 0; i < getSubCommands().size(); i++){
                    if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())){
                        getSubCommands().get(i).perform(p, args);
                    }
                }
            }else {
                p.sendMessage("---------------------------------------------------");
                for (int i = 0; i < getSubCommands().size(); i++){
                    p.sendMessage(getSubCommands().get(i).getSyntax() + " - " + getSubCommands().get(i).getDescription());
                }
                p.sendMessage("---------------------------------------------------");
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return subCommands;
    }

    @Override
    public @Nullable
    List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if(args.length == 1){
            ArrayList<String> subcommandsArguements = new ArrayList<>();

            for (int i = 0; i < getSubCommands().size(); i++){
                subcommandsArguements.add(getSubCommands().get(i).getName());
            }
            return subcommandsArguements;
        }else if(args.length >= 2) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    return getSubCommands().get(i).getSubcommandArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}

