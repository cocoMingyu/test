package design_pattern.command.service;

import design_pattern.command.entity.Computer;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 13:57
 * @ Modified By:
 */
public class ComputerOff implements Command {
    private Computer computer;

    public ComputerOff(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.off();
    }

    @Override
    public void undo() {
        computer.on();
    }
}
