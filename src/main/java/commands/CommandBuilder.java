package commands;

import src.CommandDispatch;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuilder {

    private String requestedCommand;
    public List<Class<? extends ICommand>> availableCommands = new ArrayList<>();

    public CommandBuilder() {
        availableCommands.add(Information.class);
        availableCommands.add(JoinVoiceChannel.class);
        availableCommands.add(LeaveVoiceChannel.class);
        availableCommands.add(IniateRoleSelectionChannel.class);
        availableCommands.add(Query.class);
        availableCommands.add(Eval.class);
        availableCommands.add(SummonerStats.class);
        availableCommands.add(Rotation.class);
        availableCommands.add(ChampionTest.class);
        availableCommands.add(Teams.class);
        availableCommands.add(Trash.class);
    }

    public CommandBuilder requestCommand(String request) {
        requestedCommand = request;
        System.out.println("Updated requested command to be build to: {" + requestedCommand + "}");
        return this;
    }

    public ICommand build() {
        ICommand builtCommand = availableCommands.stream()
                .filter(command -> command.getAnnotations().length != 0)
                .filter(this::checkRequestedCommandInAnnotatedAcceptors)
                .map(this::safeInstance)
                .findFirst()
                .orElse(new UnhandledCommand("(~32) __Command Builder__ had an empty stream! Command it was looking for: **" + requestedCommand + "**"));

        System.out.println("Constructed {" + builtCommand.getClass() + "} command!");
        return builtCommand;
    }

    protected boolean checkRequestedCommandInAnnotatedAcceptors(Class<? extends ICommand> someClass) {
        if (someClass.getAnnotations().length == 0)
            return false;

        CommandDescriptor commandDescriptor = (CommandDescriptor) Arrays.asList(someClass.getAnnotations()).get(0);
        return Arrays.asList(commandDescriptor.acceptors()).contains(requestedCommand);
    }

    protected ICommand safeInstance(Class<? extends ICommand> command) {
        ICommand createdCommand;
        try {
            createdCommand =  command.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            createdCommand = new UnhandledCommand("An error occurred during virtual command instantiation. Abstract class or Illegal access?");
        }
        return createdCommand;
    }
}