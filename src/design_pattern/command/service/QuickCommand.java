package design_pattern.command.service;

/**
 * @ Author:kn
 * @ Description:定义一个命令，可以组合命令
 * @ Date:Created in 2019/5/20 14:24
 * @ Modified By:
 */
public class QuickCommand implements Command {
    private Command[] commands;

    public QuickCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commands) {
            command.undo();
        }
    }
}
