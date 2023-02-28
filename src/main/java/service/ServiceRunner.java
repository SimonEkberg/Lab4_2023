package service;

public class ServiceRunner {
    private CommandService commandService;

    public void setCommand(CommandService commandService){
        this.commandService = commandService;
    }

    public void executeService(){
        this.commandService.execute();
    }
}
